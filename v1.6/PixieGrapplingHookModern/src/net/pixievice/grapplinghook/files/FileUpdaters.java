package net.pixievice.grapplinghook.files;

import java.io.File;

import org.bukkit.Bukkit;

import net.pixievice.grapplinghook.Main;

public class FileUpdaters {
	
	FileManagers fm = new FileManagers();
	private Main main = Main.getInstance();
	
	public void resetLang() {
		
		File configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieGrapplingHook").getDataFolder(), "lang.yml"); 
		configFile.delete();
		fm.generateLang();
	}
	
	public void resetConfig() {
		
		File configFile = new File(main.getDataFolder(), "config.yml");
		configFile.delete();
		main.saveDefaultConfig();
		main.reloadConfig();
	}
	
	public void resetAll() {
		resetLang();
		resetConfig();
	}
}