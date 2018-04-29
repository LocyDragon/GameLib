package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerPickupItemEvent;
/**
 * 事件对应type: PLAYING_PLAYER_PICK_UP_ITEM;
 */
public class GamePlayerPickUpItemEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerPickupItemEvent prop = null;
	public GamePlayerPickUpItemEvent(PlayingPlayer who, PlayerPickupItemEvent e) {
		super.type = EventType.PLAYING_PLAYER_PICK_UP_ITEM;
		this.player = who;
		this.prop = e;
	}

	public PlayerPickupItemEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
