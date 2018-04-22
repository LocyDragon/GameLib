package com.locydragon.gamelib.api;

import com.locydragon.gamelib.GameLib;
import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.EventExecutor;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.baby.GameEndEvent;
import com.locydragon.gamelib.api.event.baby.GameStartEvent;
import com.locydragon.gamelib.api.event.baby.PlayerGameJoinEvent;
import com.locydragon.gamelib.api.event.baby.PlayerGameQuitEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import com.locydragon.gamelib.api.exception.NoSuchTeamException;
import com.locydragon.gamelib.core.GameIdManager;
import com.locydragon.gamelib.core.GameRunTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class CustomGame {
	private Long gameId;
	public List<List<String>> teamList = new ArrayList<List<String>>();
	private boolean isStart = false;
	private int teamNum;
	private int max;
	private Map<EventType,EventExecutor> eventBus = new HashMap<>();
	protected CustomGame(int teamNum, int max) {
		this.gameId = GameIdManager.newId();
		this.teamNum = teamNum;
		for (int i = 0;i < this.teamNum;i++) {
			this.teamList.add(new ArrayList<String>());
		}
		this.max = max;
		GameRunTime.gameList.add(this);
	}
	public CustomGame stop() {
		this.isStart = false;
		EventBus.callEvent(this, new GameEndEvent());
		return this;
	}
	public CustomGame runTaskLater(Runnable runnable, Long second) {
		Bukkit.getScheduler().runTaskLater(GameLib.instance, runnable, second*20);
		return this;
	}
	public CustomGame addEvent(EventType type, EventExecutor executor) {
		eventBus.put(type, executor);
		return this;
	}
	protected void callEvent(EventType type, GameEvent evt) {
		Set<Map.Entry<EventType,EventExecutor>> bus = this.eventBus.entrySet();
		for (Map.Entry<EventType,EventExecutor> entry : bus) {
			if (entry.getKey().equals(type)) {
				entry.getValue().onEvent(evt);
			}
		}
	}
	public Long getGameId() {
		return this.gameId;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CustomGame)) {
			return false;
		}
		CustomGame game = (CustomGame)obj;
		if (game.getGameId().equals(this.gameId)) {
			return true;
		}
		return false;
	}
	public CustomGame startGame() {
		this.isStart = true;
		EventBus.callEvent(this, new GameStartEvent());
		return this;
	}
	public CustomGame broadcastMsg(String message) {
		for (List<String> obj : this.teamList) {
			for (String player : obj) {
				if (Bukkit.getPlayer(player) == null) {
					continue;
				}
				Bukkit.getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', message));
			}
		}
		return this;
	}
	public CustomGame broadcastMsgInTeam(int id, String message) {
		if (id < 0 || teamList.size() <= id) {
			throw new NoSuchTeamException("Cannot find team "+id+".");
		}
		List<String> team = this.teamList.get(id);
		for (String player : team) {
			if (Bukkit.getPlayer(player) == null) {
				continue;
			}
			Bukkit.getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		}
		return this;
	}
	public boolean quit(Player who) {
		PlayingPlayer player = PlayingPlayer.search(who);
		List<String> playerTeam = this.teamList.get(player.getTeam());
		List<String> newTeam = new ArrayList<>();
		for (String each : playerTeam) {
			if (each.equalsIgnoreCase(who.getName())) {
				continue;
			}
			newTeam.add(each);
		}
		this.teamList.set(player.getTeam(), newTeam);
		EventBus.callEvent(this, new PlayerGameQuitEvent(who));
		return true;
	}
	//正在游戏中的
	public List<Player> getPlayerInGameByTeamId(int id) {
		if (id < 0 || teamList.size() <= id) {
			throw new NoSuchTeamException("Cannot find team "+id+".");
		}
		List<Player> toList = new ArrayList<>();
		List<String> needTo = teamList.get(id);
		for (String obj : needTo) {
			if (Bukkit.getPlayer(obj) == null) {
				continue;
			} else {
				toList.add(Bukkit.getPlayer(obj));
			}
		}
		return toList;
	}
	//如果满人了或游戏未开始已经在游戏里返回false，在指定的teamId中
	public boolean joinPlayerInTeam(int id, Player who) {
		if (this.isStart == false) {
			return false;
		}
		if (this.teamList.get(id).size() >= this.max) {
			return false;
		}
		for (CustomGame game : GameRunTime.gameList) {
			for (List<String> name : game.teamList) {
				for (String obj : name) {
					if (obj.equalsIgnoreCase(who.getName())) {
						return false;
					}
				}
			}
		}
		List<String> list = this.teamList.get(id);
		list.add(who.getName());
		this.teamList.set(id, list);
		EventBus.callEvent(this, new PlayerGameJoinEvent((who)));
		return true;
	}
	//直接加入
	public boolean joinPlayer(Player who) {
		if (this.isStart == false) {
			return false;
		}
		HashMap<Integer,List<String>> notFullTeams = new HashMap<Integer,List<String>>();
		for (int i = 0;i < this.teamList.size();i++) {
			if (this.teamList.get(i).size() >= this.max) {
				continue;
			}
			notFullTeams.put(i, this.teamList.get(i));
		}
		if (notFullTeams.isEmpty()) {
			return false;
		}
		Set<Map.Entry<Integer,List<String>>> teamSet = notFullTeams.entrySet();
		double r = Math.random()*teamSet.size();
		int random = (int)r;
		Map.Entry<Integer,List<String>> choose = null;
		int i = 0;
		for (Map.Entry<Integer,List<String>> entry : teamSet) {
			if (i == random) {
				choose = entry;
			}
		}
		if (choose == null) {
			return false;
		}
		List<String> team = choose.getValue();
		team.add(who.getName());
		this.teamList.set(choose.getKey(), team);
		EventBus.callEvent(this, new PlayerGameJoinEvent((who)));
		return true;
	}
	public List<Integer> getTeamIDs() {
		List<Integer> list = new ArrayList<>();
		int first = 0;
		for (List<String> team : this.teamList) {
			list.add(first);
			first++;
		}
		return list;
	}
}
