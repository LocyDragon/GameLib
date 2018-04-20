package com.locydragon.gamelib.api.event.baby;

import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.entity.Player;

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
