package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.player.PlayerExpChangeEvent;
/**
 * 事件对应type: PLAYING_PLAYER_EXP_CHANGE;
 */
public class GamePlayerExpChangeEvent extends GameEvent {
	private PlayingPlayer player = null;
	private PlayerExpChangeEvent prop = null;
	public GamePlayerExpChangeEvent(PlayingPlayer who, PlayerExpChangeEvent e) {
		super.type = EventType.PLAYING_PLAYER_EXP_CHANGE;
		this.player = who;
		this.prop = e;
	}

	public PlayerExpChangeEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
