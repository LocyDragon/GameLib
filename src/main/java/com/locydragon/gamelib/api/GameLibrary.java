package com.locydragon.gamelib.api;

public class GameLibrary {
	private int teamNum = 2;
	private int max = 16;
	protected GameLibrary() {}

	/**
	 * 设置组数量
	 * @param number 组数量
	 * @return
	 */
	public GameLibrary teamNumber(int number) {
		this.teamNum = number;
		return this;
	}

	/**
	 * 设置每个组最多多少人
	 * @param number 组人数数量
	 * @return
	 */
	public GameLibrary setMaxPlayerInOneTeam(int number) {
		this.max = number;
		return this;
	}

	/**
	 * 生成Game对象
	 * @return 游戏对象
	 */
	public CustomGame build() {
		return new CustomGame(this.teamNum, this.max);
	}

	/**
	 * 获取本类实例
	 * @return 本类实例
	 */
	public static GameLibrary getLibrary() {
		return new GameLibrary();
	}
}
