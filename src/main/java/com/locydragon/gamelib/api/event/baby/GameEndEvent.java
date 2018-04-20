package com.locydragon.gamelib.api.event.baby;

import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;

public class GameEndEvent extends GameEvent {
	public GameEndEvent() {
		super.type = EventType.ON_GAME_END;
	}
}
