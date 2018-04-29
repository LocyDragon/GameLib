package com.locydragon.gamelib.api.util;

import com.locydragon.gamelib.api.CustomGame;

import java.util.HashMap;

/**
 * 详细可以在github上查看例子
 */
public class ScoreManager {
	private CustomGame game;
	private HashMap<Integer,Long> score = new HashMap<Integer,Long>();

	/**
	 * 创建一个积分器
	 * @param game 你要为游戏创建的积分器的对象
	 */
	public ScoreManager(CustomGame game) {
		this.game = game;
		for (Integer id : game.getTeamIDs()) {
			score.put(id, 0L);
		}
	}

	/**
	 * 获取积分器管理的游戏
	 * @return
	 */
	public CustomGame getGame() {
		return this.game;
	}

	/**
	 * 给某个组加一分
	 * @param group 对象组
	 * @return
	 */
	public ScoreManager addOneScore(Integer group) {
		Long num = score.get(group);
		num++;
		score.replace(group, num);
		return this;
	}

	/**
	 * 给对象组扣一分
	 * @param group 对象组
	 * @return
	 */
	public ScoreManager minusOne(Integer group) {
		Long num = score.get(group);
		num--;
		score.replace(group, num);
		return this;
	}

	/**
	 * 设置某个组的分数
	 * @param group 对象组
	 * @param score 对象分数
	 * @return
	 */
	public ScoreManager set(Integer group, Long score) {
		this.score.replace(group, score);
		return this;
	}

	/**
	 * 获取某个组的分数
	 * @param group 对象组
	 * @return 对象组分数
	 */
	public Long getScore(Integer group) {
		return this.score.get(group);
	}
}
