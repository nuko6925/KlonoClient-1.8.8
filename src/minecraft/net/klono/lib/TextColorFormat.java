package net.klono.lib;

import com.mojang.realmsclient.gui.ChatFormatting;

public class TextColorFormat {
	
	public static String getColored(String text) {
		String msg = text;
		msg = msg.replace("&1", ""+ChatFormatting.DARK_BLUE);
		msg = msg.replace("&2", ""+ChatFormatting.DARK_GREEN);
		msg = msg.replace("&3", ""+ChatFormatting.DARK_AQUA);
		msg = msg.replace("&4", ""+ChatFormatting.DARK_RED);
		msg = msg.replace("&5", ""+ChatFormatting.DARK_PURPLE);
		msg = msg.replace("&6", ""+ChatFormatting.GOLD);
		msg = msg.replace("&7", ""+ChatFormatting.GRAY);
		msg = msg.replace("&8", ""+ChatFormatting.DARK_GRAY);
		msg = msg.replace("&9", ""+ChatFormatting.BLUE);
		msg = msg.replace("&0", ""+ChatFormatting.BLACK);
		msg = msg.replace("&a", ""+ChatFormatting.GREEN);
		msg = msg.replace("&b", ""+ChatFormatting.AQUA);
		msg = msg.replace("&c", ""+ChatFormatting.RED);
		msg = msg.replace("&d", ""+ChatFormatting.LIGHT_PURPLE);
		msg = msg.replace("&e", ""+ChatFormatting.YELLOW);
		msg = msg.replace("&f", ""+ChatFormatting.WHITE);
		msg = msg.replace("&k", ""+ChatFormatting.OBFUSCATED);
		msg = msg.replace("&l", ""+ChatFormatting.BOLD);
		msg = msg.replace("&m", ""+ChatFormatting.STRIKETHROUGH);
		msg = msg.replace("&n", ""+ChatFormatting.UNDERLINE);
		msg = msg.replace("&o", ""+ChatFormatting.ITALIC);
		msg = msg.replace("&r", ""+ChatFormatting.RESET);
		return msg;
	}

}
