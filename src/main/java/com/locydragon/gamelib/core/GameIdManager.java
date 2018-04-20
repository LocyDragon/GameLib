package com.locydragon.gamelib.core;

public class GameIdManager {
	private static Long id = 0L;
	public static Long newId() {
		id++;
		return id;
	}
}
