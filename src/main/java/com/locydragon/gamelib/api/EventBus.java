package com.locydragon.gamelib.api;

import com.locydragon.gamelib.api.event.GameEvent;

public class EventBus {
	public static void callEvent(CustomGame game, GameEvent evt) {
		game.callEvent(evt.getType(), evt);
	}
}
