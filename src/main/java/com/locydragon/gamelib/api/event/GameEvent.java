package com.locydragon.gamelib.api.event;

import com.locydragon.gamelib.api.event.type.EventType;

public abstract class GameEvent {
	public EventType type = EventType.EMPTY;

	/**
	 *
	 * @return 事件类型
	 */
	public EventType getType() {
		return this.type;
	}

	/**
	 *
	 * @return 事件触发玩家
	 */
	public Object getPlayer() {
		throw new IllegalArgumentException("You cannot use method getPlayer() here.");
	}

	/**
	 *
	 * @return 事件属性
	 */
	public Object getProp() {
		throw new IllegalArgumentException("You cannot use method getProp() here.");
	}
}
