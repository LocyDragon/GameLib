package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerItemBreakEvent;

/**
 * 事件对应type: PLAYING_ITEM_BREAK;
 */
public class GamePlayerItemBreakEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerItemBreakEvent prop = null;
	public GamePlayerItemBreakEvent(PlayingPlayer who, PlayerItemBreakEvent e) {
		super.type = EventType.PLAYING_ITEM_BREAK;
		this.player = who;
		this.prop = e;
	}

	public PlayerItemBreakEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
