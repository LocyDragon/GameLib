package com.locydragon.gamelib.api;

import com.locydragon.gamelib.api.event.GameEvent;

public class EventBus {
	/**
	 * 给某个Game对象触发一个事件
	 * @param game 游戏对象
	 * @param evt 事件对象
	 */
	public static void callEvent(CustomGame game, GameEvent evt) {
		game.callEvent(evt.getType(), evt);
	}
}
