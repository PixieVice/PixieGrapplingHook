package net.pixievice.grapplinghook;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.pixievice.grapplinghook.commands.MainCommands;
import net.pixievice.grapplinghook.events.Events;
import net.pixievice.grapplinghook.files.FileManagers;
import net.pixievice.grapplinghook.recipes.Recipe;

public class Main  extends JavaPlugin {
	
	FileManagers fm = new FileManagers();
	private static ConsoleCommandSender console;
	private static Main instance;
	
	public void onEnable() {
		instance = this;
		console = getServer().getConsoleSender();
		
		console.sendMessage(ChatUtils.chat("&aPixieGrapplingHook &7(Legacy) Enabled!"));
		console.sendMessage(ChatUtils.chat("&dDiscord: https://discord.gg/hAhZ4GqDmE"));
		
		Bukkit.getPluginManager().registerEvents(new Events(this), this);
		getCommand("pgrapplinghook").setExecutor(new MainCommands(this));
		
		this.getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		fm.generateLang();
		new Recipe(this).createRecipe();
		
		int ver = getConfig().getInt("config-version");
		if (ver != 3) {
			console.sendMessage(ChatUtils.chat("&cYour config is outdated. Please update your config for the latest features."));
		} else {
			console.sendMessage(ChatUtils.chat("&aYour config is up to date!"));
		}
		
	}
	
	public void onDisable() {
		
		console.sendMessage(ChatUtils.chat("&cPixieGrapplingHook Disabled!"));
		
	}
	
	public static Main getInstance() {
	    return instance;
	}
	
	public static ConsoleCommandSender getConsole() {
		return console;
	}
	

}
