package net.pixievice.grapplinghook.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.pixievice.grapplinghook.ChatUtils;
import net.pixievice.grapplinghook.Main;

public class SettingsGUI {
	
	public Inventory getInventoy() {
		
		int size = 1 * 9;
		String name = "&8GrapplingHook Settings";
		
		ItemStack cdItem = new ItemStack(Material.COMPASS);
		ItemStack sItem = new ItemStack(Material.FISHING_ROD);
		
		String cdName = "&aCooldown &7" + Main.getInstance().getConfig().getInt("GrapplingHook.cooldown");
		String sName = "&bSpeed &7" + Main.getInstance().getConfig().getInt("GrapplingHook.speed");
		
		List<String> lore = new ArrayList<>();
		lore.add("§7"); lore.add("§7Increase cooldown (Right Click)"); lore.add("§7Decrease cooldown (Left Click)");

		ItemMeta cdim = cdItem.getItemMeta();
		cdim.setDisplayName(ChatUtils.chat(cdName));
		cdim.setLore(lore);
		cdItem.setItemMeta(cdim);
		
		ItemMeta sim = sItem.getItemMeta();
		sim.setDisplayName(ChatUtils.chat(sName));
		sim.setLore(lore);
		sItem.setItemMeta(sim);
		
		Inventory gui = Bukkit.createInventory(null, size, ChatUtils.chat(name));
		
		gui.setItem(3, cdItem);
		gui.setItem(5, sItem);
		
		return gui;
		
	}

}
