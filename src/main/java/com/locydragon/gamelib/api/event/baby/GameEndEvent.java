package com.locydragon.gamelib.api.event.baby;

import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
/**
 * 该事件在游戏调用stop()方法时触发.
 * 事件对应type: ON_GAME_END;
 */
public class GameEndEvent extends GameEvent {
	public GameEndEvent() {
		super.type = EventType.ON_GAME_END;
	}
}
