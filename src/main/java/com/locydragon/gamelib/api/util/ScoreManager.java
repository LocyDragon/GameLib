package com.locydragon.gamelib.api.util;

import com.locydragon.gamelib.api.CustomGame;

import java.util.HashMap;


public class ScoreManager {
	private CustomGame game;
	private HashMap<Integer,Long> score = new HashMap<Integer,Long>();
	public ScoreManager(CustomGame game) {
		this.game = game;
		for (Integer id : game.getTeamIDs()) {
			score.put(id, 0L);
		}
	}

	public CustomGame getGame() {
		return this.game;
	}

	public ScoreManager addOneScore(Integer group) {
		Long num = score.get(group);
		num++;
		score.replace(group, num);
		return this;
	}

	public ScoreManager minusOne(Integer group) {
		Long num = score.get(group);
		num--;
		score.replace(group, num);
		return this;
	}

	public ScoreManager set(Integer group, Long score) {
		this.score.replace(group, score);
		return this;
	}

	public Long getScore(Integer group) {
		return this.score.get(group);
	}
}
