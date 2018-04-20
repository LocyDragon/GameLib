package com.locydragon.gamelib.api.event.baby;


import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;

public class GameStartEvent extends GameEvent {
	public GameStartEvent() {
		super.type = EventType.ON_GAME_START;
	}
}
