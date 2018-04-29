package com.locydragon.gamelib.api.event.baby;

import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.entity.Player;
/**
 * 该事件在玩家退出游戏时触发.
 * 事件对应type: ON_GAME_QUIT;
 */
public class PlayerGameQuitEvent extends GameEvent {
	private Player who;
	public PlayerGameQuitEvent(Player who) {
		super.type = EventType.ON_GAME_QUIT;
		this.who = who;
	}
	public Player getPlayer() {
		return this.who;
	}
}
