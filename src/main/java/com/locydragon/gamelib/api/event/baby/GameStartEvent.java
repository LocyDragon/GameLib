package com.locydragon.gamelib.api.event.baby;


import com.locydragon.gamelib.api.event.GameEvent;
import com.locydragon.gamelib.api.event.type.EventType;
/**
 * 该事件在游戏调用startGame()方法时触发.
 * 事件对应type: ON_GAME_START;
 */
public class GameStartEvent extends GameEvent {
	public GameStartEvent() {
		super.type = EventType.ON_GAME_START;
	}
}
