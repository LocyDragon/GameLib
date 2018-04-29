package com.locydragon.gamelib.api.event.baby.bukkitevents;

import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
/**
 * 该类对应EventType: PLAYING_PLAYER_DAMAGE_AT_ENTITY
 */
public class GamePlayerDamageEntityEvent extends GameEvent {
	private PlayingPlayer player = null;
	private EntityDamageByEntityEvent prop = null;
	public GamePlayerDamageEntityEvent(PlayingPlayer who, EntityDamageByEntityEvent e) {
		super.type = EventType.PLAYING_PLAYER_DAMAGE_AT_ENTITY;
		this.player = who;
		this.prop = e;
	}

	public EntityDamageByEntityEvent getProp() {
		return this.prop;
	}

	public PlayingPlayer getPlayer() {
		return player;
	}
}
