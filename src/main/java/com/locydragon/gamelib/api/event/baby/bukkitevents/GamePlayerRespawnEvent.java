package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerRespawnEvent;
/**
 * 事件对应type: PLAYING_PLAYER_RESPAWN;
 */
public class GamePlayerRespawnEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerRespawnEvent prop = null;
	public GamePlayerRespawnEvent(PlayingPlayer who, PlayerRespawnEvent e) {
		super.type = EventType.PLAYING_PLAYER_RESPAWN;
		this.player = who;
		this.prop = e;
	}

	public PlayerRespawnEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
