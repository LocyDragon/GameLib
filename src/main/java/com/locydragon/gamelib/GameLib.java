package com.locydragon.gamelib;

import com.locydragon.gamelib.core.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GameLib extends JavaPlugin {
	public static GameLib instance;
	@Override
	public void onEnable() {
		Bukkit.getLogger().info("GameLib插件启动,作者:LocyDragon");
		Bukkit.getPluginManager().registerEvents(new GamePlayerMoveListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerDeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerDropItemEvent(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerThrowEggListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerExpChangeEvent(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerInteractEntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerInteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerItemBreakListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerItemConsumeListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerPickUpItemListener(), this);
		instance = this;
	}
}
