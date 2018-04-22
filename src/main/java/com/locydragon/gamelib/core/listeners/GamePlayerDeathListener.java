package com.locydragon.gamelib.core.listeners;

import com.locydragon.gamelib.api.EventBus;
import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.baby.bukkitevents.GamePlayerDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class GamePlayerDeathListener implements Listener {
	@EventHandler
	public void onMove(PlayerDeathEvent e) {
		Player player = e.getEntity();
		PlayingPlayer play = PlayingPlayer.search(player);
		if (play.getGame() == null || play.getTeam() == -1) {
			return;
		}
		EventBus.callEvent(play.getGame(), new GamePlayerDeathEvent(play, e));
	}
}
