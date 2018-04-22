package com.locydragon.gamelib;

import com.locydragon.gamelib.core.listeners.GamePlayerChatListener;
import com.locydragon.gamelib.core.listeners.GamePlayerMoveListener;
import com.locydragon.gamelib.core.listeners.GamePlayerQuitListener;
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
		instance = this;
	}
}
