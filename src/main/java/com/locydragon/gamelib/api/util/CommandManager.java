package com.locydragon.gamelib.api.util;

import com.locydragon.gamelib.GameLib;
import com.locydragon.gamelib.api.CustomGame;
import com.locydragon.gamelib.core.commands.CommandListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

public class CommandManager {
	private String cmdName;
	public CommandManager(String cmd) {
		if (cmd.startsWith("/")) {
			throw new IllegalArgumentException("Cannot with a cmd name startswith '/' ");
		}
		if (cmd.contains(" ")) {
			throw new IllegalArgumentException("Cannot contains ' ' ");
		}
		if (Bukkit.getPluginCommand(" ") != null) {
			throw new IllegalArgumentException("Command is already register in this server!");
		}
		registerCommand(cmd);
		Bukkit.getPluginCommand(cmd).setExecutor(new CommandListener());
		this.cmdName = cmd;
	}
	public CommandManager bindJoinCmd(String subCmd, CustomGame game) {
		if (subCmd.contains(" ")) {
			throw new IllegalArgumentException("Cannot contains ' ' ");
		}
		CommandListener.game.put(this.cmdName+" "+subCmd, game);
		return this;
	}
	public CommandManager bindQuitCmd(String subCmd, CustomGame game) {
		if (subCmd.contains(" ")) {
			throw new IllegalArgumentException("Cannot contains ' ' ");
		}
		CommandListener.quit.put(this.cmdName+" "+subCmd, game);
		return this;
	}
	public static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

	public static boolean registerCommand(String cmdName) {
		try {
			Object craftServer = Class.forName("org.bukkit.craftbukkit." + version + ".CraftServer").cast(Bukkit.getServer());
			Field mapField = craftServer.getClass().getDeclaredField("commandMap");
			mapField.setAccessible(true);
			Object commandMap = mapField.get(craftServer);
			Constructor pluginCommandCon = PluginCommand.class.getDeclaredConstructor(new Class[]{String.class, Plugin.class});
			pluginCommandCon.setAccessible(true);
			Object newCommand = pluginCommandCon.newInstance(cmdName, (org.bukkit.plugin.Plugin) GameLib.instance);
			//commandMap.getClass().getMethod("register", new Class[]{String.class, Command.class}).invoke(commandMap, new Object[]{"locyitem", newCommand});
			Field mapF = commandMap.getClass().getDeclaredField("knownCommands");
			mapF.setAccessible(true);
			Map<String, Command> map = (Map<String, Command>) mapF.get(commandMap);
			map.put(cmdName.toLowerCase(), (PluginCommand) newCommand);
			mapF.set(commandMap, map);
			mapField.set(craftServer, commandMap);
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
}
