package com.locydragon.gamelib.api;

public class GameLibrary {
	private int teamNum = 2;
	private int max = 16;
	protected GameLibrary() {}
	public GameLibrary teamNumber(int number) {
		this.teamNum = number;
		return this;
	}
	public GameLibrary setMaxPlayerInOneTeam(int number) {
		this.max = number;
		return this;
	}
	public CustomGame build() {
		return new CustomGame(this.teamNum, this.max);
	}
	public static GameLibrary getLibrary() {
		return new GameLibrary();
	}
}
