package net.pixievice.grapplinghook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.pixievice.grapplinghook.commands.MainCommands;
import net.pixievice.grapplinghook.events.Events;
import net.pixievice.grapplinghook.files.FileManagers;

public class Main  extends JavaPlugin {
	
	FileManagers fm = new FileManagers();
	private static Main instance;
	
	public void onEnable() {
		
		versionCheckLegacy();
		
		instance = this;
		
		Bukkit.getLogger().info(ChatUtils.chat("&aPixieGrapplingHook &7(Legacy) Enabled!"));
		Bukkit.getLogger().info(ChatUtils.chat("&dDiscord: https://discord.gg/hAhZ4GqDmE"));
		
		Bukkit.getPluginManager().registerEvents(new Events(this), this);
		getCommand("pgrapplinghook").setExecutor(new MainCommands(this));
		
		this.getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		fm.generateLang();
		
		int ver = getConfig().getInt("config-version");
		if (ver != 2) {
			getLogger().info(ChatUtils.chat("&cYour config is outdated. Please update your config for the latest features."));
		} else {
			getLogger().info(ChatUtils.chat("&aYour config is up to date!"));
		}
		
	}
	
	public void onDisable() {
		
		getLogger().info(ChatUtils.chat("&cPixieGrapplingHook Disabled!"));
		
	}
	
	public void legacyMessage() {
		String outdated = new String("&cYou do not have a legacy server, please use PixieGrapplingHook-Modern.jar");
		Bukkit.getLogger().info(ChatUtils.chat(outdated));
	}
	
	public void versionCheckLegacy() {
		
		String version = Bukkit.getVersion();
		if (version.contains("1.13")) {
			legacyMessage();
		} else if (version.contains("1.14")) {
			legacyMessage();
		} else if (version.contains("1.15")) {
			legacyMessage();
		} else if (version.contains("1.16")) {
			legacyMessage();
		} else if (version.contains("1.17")) {
			legacyMessage();
		}
		
	}
	
	public static Main getInstance() {
	    return instance;
	}
	

}
