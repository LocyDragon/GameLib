package com.locydragon.gamelib.api.event;

public interface EventExecutor {
	/**
	 * 事件被调用时触发
	 * 详细请看例子
	 * @param evt 实现这个接口，事件调用时会传入一个GameEvent对象，可以强转成指定子类
	 */
	void onEvent(GameEvent evt);
}
