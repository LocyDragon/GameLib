package com.locydragon.gamelib.api.event;

import com.locydragon.gamelib.api.event.type.EventType;

public abstract class GameEvent {
	public EventType type = EventType.EMPTY;

	public EventType getType() {
		return this.type;
	}
	public Object getPlayer() {
		throw new IllegalArgumentException("You cannot use method getPlayer() here.");
	}
	public Object getProp() {
		throw new IllegalArgumentException("You cannot use method getProp() here.");
	}
}
