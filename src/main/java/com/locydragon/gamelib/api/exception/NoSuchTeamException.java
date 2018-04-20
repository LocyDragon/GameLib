package com.locydragon.gamelib.api.exception;

public class NoSuchTeamException extends IllegalArgumentException {
	public NoSuchTeamException(String reason) {
		super(reason);
	}
}
