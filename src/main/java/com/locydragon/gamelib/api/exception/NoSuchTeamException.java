package com.locydragon.gamelib.api.exception;

/**
 * 这个异常在没有指定的组时触发
 */
public class NoSuchTeamException extends IllegalArgumentException {
	public NoSuchTeamException(String reason) {
		super(reason);
	}
}
