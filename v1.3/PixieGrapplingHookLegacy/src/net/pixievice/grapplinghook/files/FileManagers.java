package net.pixievice.grapplinghook.files;

public class FileManagers {
	
	public void generateLang() {
		
		Lang.setup();
		Lang.get().addDefault("Prefix", "&8[&2PixieGrapplingHook&8] » ");
		Lang.get().addDefault("UnknownCommand", "&cUnknown command. Please use &7/PGH Help &cfor a list of commands.");
		Lang.get().addDefault("NoPermission", "&cYou do not have permission to execute this command.");
		Lang.get().addDefault("NotPlayer", "&cOnly players can execute this command!");
		Lang.get().addDefault("Cooldown", "&cPlease wait %seconds% seconds before using this again!");
		Lang.get().addDefault("NoPlayerExists", "&cThis player either does not exist or is not online!");
		Lang.get().addDefault("GivenGrapplingHook", "&ayou were given a grappling hook!");
		Lang.get().addDefault("GiveGrapplingHook", "&aYou gave %player% a grappling hook!");
		Lang.get().addDefault("GetGrapplingHook", "&aYou gave yourself a grappling hook!");
		Lang.get().options().copyDefaults(true);
		Lang.save();
		
	}

}
