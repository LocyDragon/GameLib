package com.locydragon.gamelib.api.event;

import com.locydragon.gamelib.api.event.type.EventType;

public class GameEvent {
	public EventType type = EventType.EMPTY;

	public EventType getType() {
		return this.type;
	}
}
