package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerTeleportEvent;
/**
 * 事件对应type: PLAYING_PLAYER_TELEPORT;
 */
public class GamePlayerTeleportEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerTeleportEvent prop = null;
	public GamePlayerTeleportEvent(PlayingPlayer who, PlayerTeleportEvent e) {
		super.type = EventType.PLAYING_PLAYER_TELEPORT;
		this.player = who;
		this.prop = e;
	}

	public PlayerTeleportEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
