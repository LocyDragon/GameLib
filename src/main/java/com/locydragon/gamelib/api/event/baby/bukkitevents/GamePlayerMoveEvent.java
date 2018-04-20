package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;

public class GamePlayerMoveEvent extends GameEvent {
	private PlayingPlayer player = null;
	public GamePlayerMoveEvent(PlayingPlayer who) {
		super.type = EventType.PLAYING_PLAYER_MOVE;
		this.player = who;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
