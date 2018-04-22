package com.locydragon.gamelib.core.listeners;

import com.locydragon.gamelib.GameLib;
import com.locydragon.gamelib.api.EventBus;
import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.baby.bukkitevents.GamePlayerTeleportEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class GamePlayerTeleportListener implements Listener {
	@EventHandler
	public void onPlayerQuit(PlayerTeleportEvent e) {
		Bukkit.getScheduler().runTaskLater(GameLib.instance, new Runnable() {
			@Override
			public void run() {
				Player player = e.getPlayer();
				PlayingPlayer play = PlayingPlayer.search(player);
				if (play.getGame() == null || play.getTeam() == -1) {
					return;
				}
				EventBus.callEvent(play.getGame(), new GamePlayerTeleportEvent(play, e));
			}
		}, 1);
	}
}
