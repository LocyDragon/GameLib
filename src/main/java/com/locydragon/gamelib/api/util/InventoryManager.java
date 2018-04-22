package com.locydragon.gamelib.api.util;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import java.util.HashMap;
import java.util.Map;


public class InventoryManager {
	private static HashMap<String,Inventory> save = new HashMap<>();
	public static void saveInventory(Player who) {
		save.put(who.getName(), who.getInventory());
    }
	public static Inventory getInventory(Player who) {
		for (Map.Entry<String,Inventory> entrys : save.entrySet()) {
			if (entrys.getKey().equalsIgnoreCase(who.getName())) {
				return entrys.getValue();
			}
		}
		return null;
	}
    public static void removeSave(Player who) {
		save.remove(who.getName());
	}
	public static void a() {}
}
