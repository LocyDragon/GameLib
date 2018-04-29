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

	/**
	 * 停止游戏
	 * @return
	 */
	public CustomGame stop() {
		this.isStart = false;
		EventBus.callEvent(this, new GameEndEvent());
		return this;
	}

	/**
	 * 在几秒后执行一个runnable
	 * @param runnable 对象runnable
	 * @param second 多少秒之后
	 * @return
	 */
	public CustomGame runTaskLater(Runnable runnable, Long second) {
		Bukkit.getScheduler().runTaskLater(GameLib.instance, runnable, second*20);
		return this;
	}

	/**
	 * 可以用链式增加事件，详细查看github上的例子
	 * @param type 事件类型
	 * @param executor 事件执行类(内部类也可以)
	 * @return
	 */
	public CustomGame addEvent(EventType type, EventExecutor executor) {
		eventBus.put(type, executor);
		return this;
	}
	protected void callEvent(EventType type, GameEvent evt) {
		Set<Map.Entry<EventType,EventExecutor>> bus = this.eventBus.entrySet();
		for (Map.Entry<EventType,EventExecutor> entry : bus) {
			if (entry.getKey().equals(type) || entry.getKey() == type) {
				entry.getValue().onEvent(evt);
			}
		}
	}

	/**
	 * 获取游戏ID，游戏ID是唯一的
	 * @return 游戏ID
	 */
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

	/**
	 * 开始游戏
	 * @return
	 */
	public CustomGame startGame() {
		this.isStart = true;
		EventBus.callEvent(this, new GameStartEvent());
		return this;
	}

	/**
	 * 给所有在游戏的人发送信息
	 * @param message 信息
	 * @return
	 */
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

	/**
	 * 在某个组内公告信息
	 * @param id 组ID
 	 * @param message 信息
	 * @return
	 */
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

	/**
	 * 退出某个玩家
	 * @param who 玩家对象
	 * @return 是否退出成功，退出不成功的情况是玩家不在游戏中.
	 */
	public boolean quit(Player who) {
		PlayingPlayer player = PlayingPlayer.search(who);
		if (player.getTeam() == -1 || player.getGame() == null) {
			return false;
		}
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

	/**
	 * 获取整个队的玩家
	 * @param id 队id
	 * @return 队员
	 */
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

	/**
	 * 把玩家加入指定组id
	 * @param id 组id
	 * @param who 玩家
	 * @return 是否加入成功，如果满人了或游戏未开始已经在游戏里返回false
	 */
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

	/**
	 * 直接把某玩家加入游戏中
	 * @param who 玩家对象
	 * @return 是否加入成功，如果游戏未开始或玩家已经在游戏中或满人加入失败
	 */
	//直接加入
	public boolean joinPlayer(Player who) {
		if (this.isStart == false) {
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
		double r = Math.random()*notFullTeams.size();
		int random = (int)r;
		Map.Entry<Integer,List<String>> choose = null;
		int i = 0;
		for (Map.Entry<Integer,List<String>> entry : teamSet) {
			if (i == random) {
				choose = entry;
			}
			i++;
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

	/**
	 * 获取所有组id
	 * @return 组id
	 */
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
