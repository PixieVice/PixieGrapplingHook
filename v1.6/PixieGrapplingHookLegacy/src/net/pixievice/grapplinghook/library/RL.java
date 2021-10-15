package net.pixievice.grapplinghook.library;

import net.pixievice.grapplinghook.files.Lang;

public class RL {
	
	public static String prefix() {
		return Lang.get().getString("Prefix");
	}
	
	public static String noperm() {
		return Lang.get().getString("NoPermission");
	}
	
	public static String unknown() {
		return Lang.get().getString("UnknownCommand");
	}
	
	public static String unknownplayer() {
		return Lang.get().getString("NoPlayerExists");
	}
	
	public static String gethook() {
		return Lang.get().getString("GetGrapplingHook");
	}
	
	public static String givehook() {
		return Lang.get().getString("GiveGrapplingHook");
	}
	
	public static String givenhook() {
		return Lang.get().getString("GivenGrapplingHook");
	}

}
