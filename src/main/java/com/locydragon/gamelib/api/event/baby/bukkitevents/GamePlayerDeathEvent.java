package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.entity.PlayerDeathEvent;


public class GamePlayerDeathEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerDeathEvent prop = null;
	public GamePlayerDeathEvent(PlayingPlayer who, PlayerDeathEvent e) {
		super.type = EventType.PLAYING_PLAYER_DEATH;
		this.player = who;
		this.prop = e;
	}

	public PlayerDeathEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
