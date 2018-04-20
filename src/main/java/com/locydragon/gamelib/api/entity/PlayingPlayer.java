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
	public Integer getTeam() {
		return this.teamNumber;
	}
	public CustomGame getGame() {
		return this.game;
	}
	public static PlayingPlayer search(Player who) {
		return new PlayingPlayer(who.getName());
	}
	public String getPlayerName() {
		return this.playerName;
	}
}
