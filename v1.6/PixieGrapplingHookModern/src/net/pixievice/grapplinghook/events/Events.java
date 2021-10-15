package net.pixievice.grapplinghook.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

import net.pixievice.grapplinghook.ChatUtils;
import net.pixievice.grapplinghook.Main;
import net.pixievice.grapplinghook.Maps;
import net.pixievice.grapplinghook.files.Lang;
import net.pixievice.grapplinghook.gui.SettingsGUI;
import net.pixievice.grapplinghook.items.GrapplingHookItem;

public class Events implements Listener {
	
	GrapplingHookItem gpi = new GrapplingHookItem();
	SettingsGUI sgui = new SettingsGUI();
	
	Main main;
	
	public Events(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void FishEvent(PlayerFishEvent e) {
		
		Player player = e.getPlayer();
		int cooldowntime = Main.getInstance().getConfig().getInt("GrapplingHook.cooldown");
		double gS = main.getConfig().getDouble("GrapplingHook.speed");
		String prefix = Lang.get().getString("Prefix");
		String cI = main.getConfig().getString("GrapplingHook.cooldown");
		String cMsg = Lang.get().getString("Cooldown");
		cMsg = cMsg.replaceAll("%seconds%", cI);
		
		if (e.getState() == PlayerFishEvent.State.REEL_IN 
				|| e.getState() == PlayerFishEvent.State.IN_GROUND) {
		if (player.getInventory().getItemInMainHand().equals(gpi.GrapplingHook())) {
		if (cooldowntime != 0) {
		if (Maps.cooldown.containsKey(player.getName())) {
		if (Maps.cooldown.get(player.getName()) > System.currentTimeMillis()) {
			
			player.sendMessage(ChatUtils.chat(prefix + cMsg));
			return;
			
		}
			
		}
		
		Maps.cooldown.put(player.getName(), System.currentTimeMillis() + (cooldowntime * 1000L));
		
		Location to = e.getHook().getLocation();
		Location loc = player.getLocation();
		double x = loc.getX() - to.getX();
		double y = loc.getY() - to.getY();
		double z = loc.getZ() - to.getZ();
		Vector velocity = (new Vector(x, y, z)).normalize().multiply(-gS);
		player.setVelocity(velocity);
			
		} else if (cooldowntime == 0) {
			
			Location to = e.getHook().getLocation();
			Location loc = player.getLocation();
			double x = loc.getX() - to.getX();
			double y = loc.getY() - to.getY();
			double z = loc.getZ() - to.getZ();
			Vector velocity = (new Vector(x, y, z)).normalize().multiply(-gS);
			player.setVelocity(velocity);
			
		}
			
		}
			
		}
		
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent e) {
		
		Boolean cancelfall = main.getConfig().getBoolean("DisableFallDamage");
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
		
		if (cancelfall == true) {
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
		if (player.getInventory().getItemInMainHand().equals(gpi.GrapplingHook())) {
				e.setCancelled(true);
		}
		}
		}	
	}
		
	}
	  
	  @EventHandler
	  public void onClick(InventoryClickEvent e) {
		  Player player = (Player) e.getWhoClicked();
		  String playerTitle = e.getView().getTitle();
		  String settingsTitle = "&8GrapplingHook Settings";
		  
		  if (e.getClickedInventory() == player.getOpenInventory().getTopInventory() && ChatUtils.chat(playerTitle).equals(ChatUtils.chat(settingsTitle))) {
			  //integers//
			  int cd = main.getConfig().getInt("GrapplingHook.cooldown");
			  int sp = main.getConfig().getInt("GrapplingHook.speed");
			  
			  int slot = e.getSlot();
			  ClickType click = e.getClick();
		  
		  if (slot == 3) {
			  e.setCancelled(true);
			  
			  if (click == ClickType.LEFT) {
				  main.getConfig().set("GrapplingHook.cooldown", cd + 1);
				  main.saveConfig();
				  oI(player);
			  } else if (click == ClickType.RIGHT) {
				  if (cd != 0) {
				  main.getConfig().set("GrapplingHook.cooldown", cd - 1);
				  main.saveConfig();
				  oI(player);
				  }
			  }
			  
		  } else if (slot == 5) {
			  e.setCancelled(true);
			  
			  if (click == ClickType.LEFT) {
				  main.getConfig().set("GrapplingHook.speed", sp + 1);
				  main.saveConfig();
				  oI(player);
			  } else if (click == ClickType.RIGHT) {
				  if (sp != 0) {
				  main.getConfig().set("GrapplingHook.speed", sp - 1);
				  main.saveConfig();
				  oI(player);
				  }
			  }
			  
		  } else {
			  e.setCancelled(true);
		  }
		  
	  }
	  }
	  
	  private void oI(Player player) {
		  player.openInventory(sgui.getInventoy());
	  }

}
