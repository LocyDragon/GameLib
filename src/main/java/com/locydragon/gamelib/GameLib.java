package com.locydragon.gamelib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GameLib extends JavaPlugin {
	public static GameLib instance;
	@Override
	public void onEnable() {
		Bukkit.getLogger().info("GameLib插件启动,作者:LocyDragon");
		instance = this;
	}
}
