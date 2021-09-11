package net.pixievice.grapplinghook.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pixievice.grapplinghook.ChatUtils;
import net.pixievice.grapplinghook.GrapplingHookItem;
import net.pixievice.grapplinghook.Main;
import net.pixievice.grapplinghook.files.FileUpdaters;
import net.pixievice.grapplinghook.files.Lang;

public class MainCommands implements CommandExecutor {
	
	GrapplingHookItem gpi = new GrapplingHookItem();
	FileUpdaters fu = new FileUpdaters();
	
	private Main main;
	
	public MainCommands(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		
		String boarder = new String(ChatUtils.chat("&a--------------------"));
		String prefix = Lang.get().getString("Prefix");
		String noperm = Lang.get().getString("NoPermission");
		String unknown = Lang.get().getString("UnknownCommand");
		String noplayerexist = Lang.get().getString("NoPlayerExists");
		String getrod = Lang.get().getString("GetGrapplingHook");
		String giverod = Lang.get().getString("GiveGrapplingHook");
		String givenrod = Lang.get().getString("GivenGrapplingHook");
		
		if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
			if (sender.hasPermission("pixie.gh.reload")) {
			main.reloadConfig();
			Lang.reload();
			sender.sendMessage(ChatUtils.chat(prefix + "&7config.yml &aand &7 lang.yml &ahave been reloaded!"));
			} else {
				sender.sendMessage(ChatUtils.chat(prefix + noperm));
			}
		} else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
			if (sender.hasPermission("pixie.gh.give")) {
			Player target = Bukkit.getPlayer(args[1]);
			if (target != null) {
				target.getInventory().addItem(gpi.GrapplingHook());
				if (sender instanceof Player) {
					Player player = (Player) sender;
					if (target.getDisplayName().equals(player.getDisplayName())) {
						player.sendMessage(ChatUtils.chat(prefix + getrod));
					} else {
						giverod = giverod.replaceAll("%player%", target.getDisplayName());
						sender.sendMessage(ChatUtils.chat(prefix + giverod));
						target.sendMessage(ChatUtils.chat(prefix + givenrod));
					}
				} else {
					giverod = giverod.replaceAll("%player%", target.getDisplayName());
					sender.sendMessage(ChatUtils.chat(prefix + giverod));
					target.sendMessage(ChatUtils.chat(prefix + givenrod));
					}
				} else {
					sender.sendMessage(ChatUtils.chat(prefix + noplayerexist));
				}
			} else {
				sender.sendMessage(ChatUtils.chat(prefix + noperm));
			}
		} else if (args.length == 2 && args[0].equalsIgnoreCase("reset")) {
			
			if (args[1].equalsIgnoreCase("lang")) {
				fu.resetLang();
				sender.sendMessage(ChatUtils.chat(prefix + "&7lang.yml &ahas been reset!"));
			} else if (args[1].equalsIgnoreCase("config")) {
				fu.resetConfig();
				sender.sendMessage(ChatUtils.chat(prefix + "&7config.yml &ahas been reset!"));
			} else if (args[1].equalsIgnoreCase("all")) {
				fu.resetAll();
				sender.sendMessage(ChatUtils.chat(prefix + "&aAll configs been reset!"));
			}
			
		} else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(ChatUtils.chat(boarder));
			sender.sendMessage(ChatUtils.chat("&3/PGH Help &7| displays this menu."));
			sender.sendMessage(ChatUtils.chat("&3/PGH Reload &7| reloads the configs."));
			sender.sendMessage(ChatUtils.chat("&3/PGH Give <player> &7| gives a player the grappling hook."));
			sender.sendMessage(ChatUtils.chat("&3/PGH Reset <lang/config/all> &7| allows you to reset the configs."));
			sender.sendMessage(ChatUtils.chat(boarder));
		} else {
			sender.sendMessage(ChatUtils.chat(prefix + unknown));
		}
		
	return true;	
	}

}
