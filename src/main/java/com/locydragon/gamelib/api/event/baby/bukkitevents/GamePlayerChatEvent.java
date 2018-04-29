package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * 该类对应EventType: PLAYING_PLAYER_CHAT
 */
public class GamePlayerChatEvent extends GameEvent {
	private PlayingPlayer player = null;
	private AsyncPlayerChatEvent prop = null;
	public GamePlayerChatEvent(PlayingPlayer who, AsyncPlayerChatEvent e) {
		super.type = EventType.PLAYING_PLAYER_CHAT;
		this.player = who;
		this.prop = e;
	}

	public AsyncPlayerChatEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
