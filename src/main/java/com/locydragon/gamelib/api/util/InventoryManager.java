package com.locydragon.gamelib.api.util;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import java.util.HashMap;
import java.util.Map;


public class InventoryManager {
	private static HashMap<String,Inventory> save = new HashMap<>();

	/**
	 * 存储背包
	 * @param who 存储背包的玩家，当服务器关闭时清理数据
	 */
	public static void saveInventory(Player who) {
		save.put(who.getName(), who.getInventory());
    }

	/**
	 *
	 * @param who 对象玩家
	 * @return 玩家的背包储存，返回null如果玩家没有储存背包
	 */
	public static Inventory getInventory(Player who) {
		for (Map.Entry<String,Inventory> entrys : save.entrySet()) {
			if (entrys.getKey().equalsIgnoreCase(who.getName())) {
				return entrys.getValue();
			}
		}
		return null;
	}

	/**
	 * 清除一个玩家的背包储存数据
	 * @param who 玩家对象
	 */
    public static void removeSave(Player who) {
		save.remove(who.getName());
	}

	/**
	 * Magic value
	 */
	public static void a() {}
}
