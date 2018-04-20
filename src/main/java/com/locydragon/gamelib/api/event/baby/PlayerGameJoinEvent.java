package com.locydragon.gamelib.api.event.baby;

import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.entity.Player;

public class PlayerGameJoinEvent extends GameEvent{
	private Player who;
	public PlayerGameJoinEvent(Player who) {
		super.type = EventType.ON_GAME_JOIN;
		this.who = who;
	}
	public Player getPlayer() {
		return this.who;
	}
}
