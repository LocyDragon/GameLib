package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerInteractEvent;
/**
 * 事件对应type: PLAYING_PLAYER_INTERACT;
 */
public class GamePlayerInteractEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerInteractEvent prop = null;
	public GamePlayerInteractEvent(PlayingPlayer who, PlayerInteractEvent e) {
		super.type = EventType.PLAYING_PLAYER_INTERACT;
		this.player = who;
		this.prop = e;
	}

	public PlayerInteractEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
