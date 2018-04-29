package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * 事件对应type: PLAYING_PLAYER_MOVE;
 */
public class GamePlayerMoveEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerMoveEvent prop = null;
	public GamePlayerMoveEvent(PlayingPlayer who, PlayerMoveEvent e) {
		super.type = EventType.PLAYING_PLAYER_MOVE;
		this.player = who;
		this.prop = e;
	}

	public PlayerMoveEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
