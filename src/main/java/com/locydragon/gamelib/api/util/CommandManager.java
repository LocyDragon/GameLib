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

	/**
	 * 如你想要使用/xxx join 来进入游戏这里就传入String "xxx"
	 * @param cmd 指令名，不包含/和空格
	 */
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

	/**
	 * 绑定进入游戏的指令
	 * 详细请看例子
	 * 不建议这么弄，这样可操作性低，建议自己注册指令。
	 * @param subCmd 如你想要使用/xxx join 来进入游戏这里就传入String "join"
	 * @param game 游戏对象
	 * @return 链式
	 */
	public CommandManager bindJoinCmd(String subCmd, CustomGame game) {
		if (subCmd.contains(" ")) {
			throw new IllegalArgumentException("Cannot contains ' ' ");
		}
		CommandListener.game.put(this.cmdName+" "+subCmd, game);
		return this;
	}
	/**
	 * 绑定退出游戏的指令
	 * 详细请看例子
	 * 不建议这么弄，这样可操作性低，建议自己注册指令。
	 * @param subCmd 如你想要使用/xxx quit 来退出游戏这里就传入String "quit"
	 * @param game 游戏对象
	 * @return 链式
	 */
	public CommandManager bindQuitCmd(String subCmd, CustomGame game) {
		if (subCmd.contains(" ")) {
			throw new IllegalArgumentException("Cannot contains ' ' ");
		}
		CommandListener.quit.put(this.cmdName+" "+subCmd, game);
		return this;
	}

	/**
	 * 服务器版本，调用nms使用
	 */
	public static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

	/**
	 * 强制注册一个指令
	 * @param cmdName 指令名
	 * @return 是否注册成功
	 */
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
