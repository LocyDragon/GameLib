package com.locydragon.gamelib.core.listeners;

import com.locydragon.gamelib.api.EventBus;
import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.baby.bukkitevents.GamePlayerQuitServerEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GamePlayerQuitListener implements Listener {
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		PlayingPlayer play = PlayingPlayer.search(player);
		if (play.getGame() == null || play.getTeam() == -1) {
			return;
		}
		EventBus.callEvent(play.getGame(), new GamePlayerQuitServerEvent(play, e));
	}
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		Player player = e.getPlayer();
		PlayingPlayer play = PlayingPlayer.search(player);
		if (play.getGame() == null || play.getTeam() == -1) {
			return;
		}
		EventBus.callEvent(play.getGame(), new GamePlayerQuitServerEvent(play, new PlayerQuitEvent(e.getPlayer(), e.getReason())));
	}
}
