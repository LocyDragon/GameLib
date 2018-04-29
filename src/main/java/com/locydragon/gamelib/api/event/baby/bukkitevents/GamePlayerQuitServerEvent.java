package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerQuitEvent;
/**
 * 事件对应type: PLAYING_PLAYER_QUIT_SERVER;
 */
public class GamePlayerQuitServerEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerQuitEvent prop = null;
	public GamePlayerQuitServerEvent(PlayingPlayer who, PlayerQuitEvent e) {
		super.type = EventType.PLAYING_PLAYER_QUIT_SERVER;
		this.player = who;
		this.prop = e;
	}

	public PlayerQuitEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
