package net.pixievice.grapplinghook.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.pixievice.grapplinghook.ChatUtils;
import net.pixievice.grapplinghook.Main;

public class GrapplingHookItem {
	
	Main main = Main.getInstance();
	
	public ItemStack GrapplingHook() {
		
		String name = main.getConfig().getString("GrapplingHook.name");
			
		  ItemStack Rod = new ItemStack(Material.FISHING_ROD);
		  ItemMeta im = Rod.getItemMeta();
		  im.spigot().setUnbreakable(true);
		  im.setDisplayName(ChatUtils.chat(name));
		  ArrayList<String> lore = (ArrayList<String>) main.getConfig().getStringList("GrapplingHook.lore");
		  im.setLore(lore);
		  im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		  Rod.setItemMeta(im);
		  return Rod;
	  }
		

}
