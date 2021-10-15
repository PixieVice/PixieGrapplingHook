package net.pixievice.grapplinghook.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pixievice.grapplinghook.ChatUtils;
import net.pixievice.grapplinghook.Main;
import net.pixievice.grapplinghook.files.FileUpdaters;
import net.pixievice.grapplinghook.files.Lang;
import net.pixievice.grapplinghook.gui.SettingsGUI;
import net.pixievice.grapplinghook.items.GrapplingHookItem;
import net.pixievice.grapplinghook.library.RL;

public class MainCommands implements CommandExecutor {
	
	SettingsGUI sgui = new SettingsGUI();
	GrapplingHookItem gpi = new GrapplingHookItem();
	FileUpdaters fu = new FileUpdaters();
	
	private Main main;
	
	public MainCommands(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		
		String boarder = new String(ChatUtils.chat("&a--------------------"));
		
		if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
		if (sender.hasPermission("pixie.gh.reload")) {
			main.reloadConfig();
			Lang.reload();
			sender.sendMessage(ChatUtils.chat(RL.prefix() + "&7config.yml &aand &7 lang.yml &ahave been reloaded!"));
		} else {
			sender.sendMessage(ChatUtils.chat(RL.prefix() + RL.noperm()));
		}
		} else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
		if (sender.hasPermission("pixie.gh.give")) {
			Player target = Bukkit.getPlayer(args[1]);
		if (target != null) {
				target.getInventory().addItem(gpi.GrapplingHook());
		if (sender instanceof Player) {
			Player player = (Player) sender;
		if (target.getDisplayName().equals(player.getDisplayName())) {
			player.sendMessage(ChatUtils.chat(RL.prefix() + RL.gethook()));
		} else {
			sender.sendMessage(ChatUtils.chat(RL.prefix() + RL.givehook().replace("%player%", target.getDisplayName())));
			target.sendMessage(ChatUtils.chat(RL.prefix() + RL.givenhook()));
		}
		} else {
			sender.sendMessage(ChatUtils.chat(RL.prefix() + RL.givehook().replace("%player%", target.getDisplayName())));
			target.sendMessage(ChatUtils.chat(RL.prefix() + RL.givenhook()));
					}
		} else {
			sender.sendMessage(ChatUtils.chat(RL.prefix() + RL.unknownplayer()));
		}
		} else {
			sender.sendMessage(ChatUtils.chat(RL.prefix()  + RL.noperm()));
		}
		} else if (args.length == 2 && args[0].equalsIgnoreCase("reset")) {
		
		if (sender.hasPermission("pixie.gh.resetconfigs")) {
			
		if (args[1].equalsIgnoreCase("lang")) {
				fu.resetLang();
				sender.sendMessage(ChatUtils.chat(RL.prefix()  + "&7lang.yml &ahas been reset!"));
		} else if (args[1].equalsIgnoreCase("config")) {
				fu.resetConfig();
				sender.sendMessage(ChatUtils.chat(RL.prefix()  + "&7config.yml &ahas been reset!"));
		} else if (args[1].equalsIgnoreCase("all")) {
				fu.resetAll();
				sender.sendMessage(ChatUtils.chat(RL.prefix()  + "&aAll configs been reset!"));
		}
			
		} else {
			sender.sendMessage(ChatUtils.chat(RL.prefix() + RL.noperm()));
		}
		} else if (args.length == 1 && args[0].equalsIgnoreCase("settings")) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("pixie.gh.settings")) {
				
				player.openInventory(sgui.getInventoy());
				
			} else {
				player.sendMessage(ChatUtils.chat(RL.prefix() + RL.noperm()));
			}
		}
			 
		} else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(ChatUtils.chat(boarder));
			sender.sendMessage(ChatUtils.chat("&3/PGH Help &7| Displays this menu."));
			sender.sendMessage(ChatUtils.chat("&3/PGH Reload &7| Reloads the configs."));
			sender.sendMessage(ChatUtils.chat("&3/PGH Give <player> &7| Gives a player the grappling hook."));
			sender.sendMessage(ChatUtils.chat("&3/PGH Settings &7| Opens the settings menu."));
			sender.sendMessage(ChatUtils.chat("&3/PGH Reset <lang/config/all> &7| Allows you to reset the configs."));
			sender.sendMessage(ChatUtils.chat(boarder));
		} else {
			sender.sendMessage(ChatUtils.chat(RL.prefix() + RL.unknown()));
		}
		
	return true;	
	}

}
