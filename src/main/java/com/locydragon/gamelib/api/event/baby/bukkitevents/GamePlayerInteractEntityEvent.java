package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
/**
 * 事件对应type: PLAYING_PLAYER_INTERACT_ENTITY;
 */
public class GamePlayerInteractEntityEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerInteractEntityEvent prop = null;
	public GamePlayerInteractEntityEvent(PlayingPlayer who, PlayerInteractEntityEvent e) {
		super.type = EventType.PLAYING_PLAYER_INTERACT_ENTITY;
		this.player = who;
		this.prop = e;
	}

	public PlayerInteractEntityEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
