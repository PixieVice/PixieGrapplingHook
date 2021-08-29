package net.pixievice.grapplinghook.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

import net.pixievice.grapplinghook.Arrays;
import net.pixievice.grapplinghook.ChatUtils;
import net.pixievice.grapplinghook.GrapplingHookItem;
import net.pixievice.grapplinghook.Main;
import net.pixievice.grapplinghook.files.Lang;

public class Events implements Listener {
	
	GrapplingHookItem gpi = new GrapplingHookItem();
	
	Main main;
	
	public Events(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void PlayerFishe(PlayerFishEvent e) {
		
		Player player = e.getPlayer();
		double grappleSpeed = main.getConfig().getDouble("GrapplingHook.speed");
		String prefix = Lang.get().getString("Prefix");
		String cooldownInt = main.getConfig().getString("GrapplingHook.cooldown");
		String cooldownmsg = Lang.get().getString("Cooldown");
		cooldownmsg = cooldownmsg.replaceAll("%seconds%", cooldownInt);
		
		if (e.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT)) {
			if (player.getInventory().getItemInHand().equals((gpi.GrapplingHook()))) {
				player.getInventory().getItemInHand().setDurability((short)0);
				if (!(Arrays.cooldown.contains(player))) {
					Location to = e.getHook().getLocation();
				      Location loc = player.getLocation();
				      double x = loc.getX() - to.getX();
				      double y = loc.getY() - to.getY();
				      double z = loc.getZ() - to.getZ();
				      Vector velocity = (new Vector(x, y, z)).normalize().multiply(-grappleSpeed);
				      player.setVelocity(velocity);
				      Arrays.cooldown.add(player);
				      Countdown(player);
				} else {
					if (Arrays.cooldown.contains(player)) {
						player.sendMessage(ChatUtils.chat(prefix + cooldownmsg));
					}
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
		if (player.getInventory().getItemInHand().equals(gpi.GrapplingHook())) {
				e.setCancelled(true);
		}
		}
		}	
	}
		
	}

	  private void Countdown(final Player player) {
		  
		  int cooldownInt = main.getConfig().getInt("GrapplingHook.cooldown");
		  int grappleCooldown = cooldownInt * 20;
		  
		  Bukkit.getScheduler ().runTaskLater (main, () -> Arrays.cooldown.remove(player), grappleCooldown);
		  }

}
