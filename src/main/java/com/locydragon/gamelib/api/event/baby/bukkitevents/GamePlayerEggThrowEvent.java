package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerEggThrowEvent;

public class GamePlayerEggThrowEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerEggThrowEvent prop = null;
	public GamePlayerEggThrowEvent(PlayingPlayer who, PlayerEggThrowEvent e) {
		super.type = EventType.PLAYING_PLAYER_THROW_EGG;
		this.player = who;
		this.prop = e;
	}

	public PlayerEggThrowEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
