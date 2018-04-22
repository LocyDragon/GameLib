package com.locydragon.gamelib.core.listeners;

import com.locydragon.gamelib.api.EventBus;
import com.locydragon.gamelib.api.entity.PlayingPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class GamePlayerExpChangeEvent implements Listener {
	@EventHandler
	public void onMove(PlayerExpChangeEvent e) {
		Player player = e.getPlayer();
		PlayingPlayer play = PlayingPlayer.search(player);
		if (play.getGame() == null || play.getTeam() == -1) {
			return;
		}
		EventBus.callEvent(play.getGame(), new com.locydragon.gamelib.api.event.baby.bukkitevents.GamePlayerExpChangeEvent(play, e));
	}
}
