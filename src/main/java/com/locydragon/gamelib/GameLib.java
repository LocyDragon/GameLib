package com.locydragon.gamelib;


import com.locydragon.gamelib.api.util.InventoryManager;
import com.locydragon.gamelib.core.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author LocyDragon
 * 项目地址: https://github.com/LocyDragon/GameLib
 * 例子地址: https://github.com/LocyDragon/GameLib/blob/master/TestGame.java
 */
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
		Bukkit.getPluginManager().registerEvents(new GamePlayerRespawnEvent(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerTeleportListener(), this);
		Bukkit.getPluginManager().registerEvents(new GamePlayerDamageAtEntityListener(), this);
		instance = this;
		saveDefaultConfig();
		InventoryManager.a();
	}
}
