package net.pixievice.grapplinghook;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GrapplingHookItem {
	
	private Main main = Main.getInstance();
	
	public ItemStack GrapplingHook() {
		
		String name = main.getConfig().getString("GrapplingHook.name");
			
		  ItemStack Rod = new ItemStack(Material.FISHING_ROD);
		  ItemMeta im = Rod.getItemMeta();
		  im.setDisplayName(ChatUtils.chat(name));
		  ArrayList<String> lore = (ArrayList<String>) main.getConfig().getStringList("GrapplingHook.lore");
		  im.setLore(lore);
		  Rod.setItemMeta(im);
		  return Rod;
	  }
		

}
