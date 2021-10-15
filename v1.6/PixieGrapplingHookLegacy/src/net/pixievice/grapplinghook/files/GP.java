package net.pixievice.grapplinghook.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.pixievice.grapplinghook.ChatUtils;

public class GP {

	private static File file;
	private static FileConfiguration configFile;
	
	public static void setup() {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieGrapplingHook").getDataFolder(), "grappinghooks.yml"); 
		
		if (!file.exists()) {
			try {
			file.createNewFile();
			} catch (IOException e) {
				System.out.println(ChatUtils.chat("&cCould not load messages file."));
			}
		}
		
		configFile = YamlConfiguration.loadConfiguration(file);
	}
	
	public static FileConfiguration get() {
		return configFile;
	}
	
	public static void save() {
		try {
			configFile.save(file);
		} catch (IOException e) {
			System.out.println(ChatUtils.chat("&cCould not save messages file."));
		}
		
	}
	
	public static void reload() {
		configFile = YamlConfiguration.loadConfiguration(file);
	}
}
