package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerItemConsumeEvent;
/**
 * 这个事件是吃东西的事件
 * 事件对应type: PLAYING_PLAYER_ITEM_CONSUME;
 */
public class GamePlayerItemConsumeEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerItemConsumeEvent prop = null;
	public GamePlayerItemConsumeEvent(PlayingPlayer who, PlayerItemConsumeEvent e) {
		super.type = EventType.PLAYING_PLAYER_ITEM_CONSUME;
		this.player = who;
		this.prop = e;
	}

	public PlayerItemConsumeEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
