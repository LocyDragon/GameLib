package com.locydragon.gamelib.core.commands;

import com.locydragon.gamelib.api.CustomGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandListener implements CommandExecutor {
    public static HashMap<String,CustomGame> game = new HashMap<>();
	public static HashMap<String,CustomGame> quit = new HashMap<>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		for (Map.Entry<String,CustomGame> entrys : game.entrySet()) {
			if (entrys.getKey().split(" ")[0].equalsIgnoreCase(cmd.getName()) && entrys.getKey().split(" ")[1].equalsIgnoreCase(args[0])) {
				entrys.getValue().joinPlayer((Player)sender);
				return false;
			}
		}
		for (Map.Entry<String,CustomGame> entrys : quit.entrySet()) {
			if (entrys.getKey().split(" ")[0].equalsIgnoreCase(cmd.getName())  && entrys.getKey().split(" ")[1].equalsIgnoreCase(args[0])) {
				entrys.getValue().quit((Player)sender);
				return false;
			}
		}
		return false;
	}
}
