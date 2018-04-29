package com.locydragon.gamelib.api.entity;

import com.locydragon.gamelib.api.CustomGame;
import com.locydragon.gamelib.core.GameRunTime;
import org.bukkit.entity.Player;


public class PlayingPlayer {
	private CustomGame game = null;
	private Integer teamNumber = -1;
	private String playerName;
	private PlayingPlayer(String name) {
		for (CustomGame game : GameRunTime.gameList) {
			for (int i = 0;i < game.teamList.size();i++) {
				for (String obj : game.teamList.get(i)) {
					if (obj.equalsIgnoreCase(name)) {
						this.game = game;
						this.teamNumber = i;
						break;
					}
				}
			}
		}
		this.playerName = name;
	}

	/**
	 * 当玩家没有任何游戏的话返回-1
	 * @return 玩家所在的组ID
	 */
	public Integer getTeam() {
		return this.teamNumber;
	}

	/**
	 * 当玩家没有任何游戏的话返回null
	 * @return 玩家所在的游戏
	 */
	public CustomGame getGame() {
		return this.game;
	}

	/**
	 * 在本地游戏群中寻找一个PlayingPlayer实例
	 * @param who 你所要查找的玩家
	 * @return PlayingPlayer对象实例
	 */
	public static PlayingPlayer search(Player who) {
		return new PlayingPlayer(who.getName());
	}

	/**
	 *
	 * @return 该玩家名字
	 */
	public String getPlayerName() {
		return this.playerName;
	}
}
