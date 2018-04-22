package com.locydragon.gamelib.core.listeners;

import com.locydragon.gamelib.api.EventBus;
import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.baby.bukkitevents.GamePlayerInteractEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class GamePlayerInteractEntityListener implements Listener {
	@EventHandler
	public void onMove(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		PlayingPlayer play = PlayingPlayer.search(player);
		if (play.getGame() == null || play.getTeam() == -1) {
			return;
		}
		EventBus.callEvent(play.getGame(), new GamePlayerInteractEntityEvent(play, e));
	}
}
