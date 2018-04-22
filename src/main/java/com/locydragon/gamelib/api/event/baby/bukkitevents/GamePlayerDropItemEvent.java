package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerDropItemEvent;

public class GamePlayerDropItemEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerDropItemEvent prop = null;
	public GamePlayerDropItemEvent(PlayingPlayer who, PlayerDropItemEvent e) {
		super.type = EventType.PLAYING_PLAYER_DROP_ITEM;
		this.player = who;
		this.prop = e;
	}

	public PlayerDropItemEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
