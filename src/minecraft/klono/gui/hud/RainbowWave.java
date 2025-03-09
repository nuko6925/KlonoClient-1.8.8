package klono.gui.hud;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.GameSettings;

public class RainbowWave {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	public static FontRenderer font = mc.fontRendererObj;
	
	public static void drawStringWave(String string, int x, int y) {
		Long long1 = 0L;
		for (String str : string.split("")) {
			if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_HOME) {
				font.drawString(str, x, y, Color.HSBtoRGB((float)((System.currentTimeMillis() - long1) % 10000L) / 10000L, 0.8F, 0.8F));
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_RMETA) {
				font.drawString(ChatFormatting.RED+str, x, y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_GRAVE) {
				font.drawString(ChatFormatting.GREEN+str, x, y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_BACKSLASH) {
				font.drawString(ChatFormatting.AQUA+str, x, y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_CIRCUMFLEX) {
				font.drawString(ChatFormatting.WHITE+str, x, y, 0);
			}
			x += font.getStringWidth(str);
			if (str != " ") {
				long1 += 500L;
			}
		}
	}
	
	public static void drawCenteredStringWave(String string, int x, int y) {
		Long long1 = 0L;
		for (String str : string.split("")) {
			if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_HOME) {
				font.drawString(str, (x - font.getStringWidth(string) / 2), y, Color.HSBtoRGB((float)((System.currentTimeMillis() - long1) % 10000L) / 10000L, 0.8F, 0.8F));
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_RMETA) {
				font.drawString(ChatFormatting.RED+str, (x - font.getStringWidth(string) / 2), y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_GRAVE) {
				font.drawString(ChatFormatting.GREEN+str, (x - font.getStringWidth(string) / 2), y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_BACKSLASH) {
				font.drawString(ChatFormatting.AQUA+str, (x - font.getStringWidth(string) / 2), y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_CIRCUMFLEX) {
				font.drawString(ChatFormatting.WHITE+str, (x - font.getStringWidth(string) / 2), y, 0);
			}
			x += font.getStringWidth(str);
			if (str != " ") {
				long1 += 500L;
			}
		}
	}
	
	public static void drawStringWaveShadow(String string, int x, int y) {
		Long long1 = 0L;
		for (String str : string.split("")) {
			if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_HOME) {
				font.drawStringWithShadow(str, x, y, Color.HSBtoRGB((float)((System.currentTimeMillis() - long1) % 10000L) / 10000L, 0.8F, 0.8F));
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_RMETA) {
				font.drawStringWithShadow(ChatFormatting.RED+str, x, y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_GRAVE) {
				font.drawStringWithShadow(ChatFormatting.GREEN+str, x, y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_BACKSLASH) {
				font.drawStringWithShadow(ChatFormatting.AQUA+str, x, y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_CIRCUMFLEX) {
				font.drawStringWithShadow(ChatFormatting.WHITE+str, x, y, 0);
			}
			x += font.getStringWidth(str);
			if (str != " ") {
				long1 += 500L;
			}
		}
	}
	
	public static void drawCenteredStringWaveShadow(String string, int x, int y) {
		Long long1 = 0L;
		for (String str : string.split("")) {
			if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_HOME) {
				font.drawStringWithShadow(str, (x - font.getStringWidth(string) / 2), y, Color.HSBtoRGB((float)((System.currentTimeMillis() - long1) % 10000L) / 10000L, 0.8F, 0.8F));
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_RMETA) {
				font.drawStringWithShadow(ChatFormatting.RED+str, (x - font.getStringWidth(string) / 2), y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_GRAVE) {
				font.drawStringWithShadow(ChatFormatting.GREEN+str, (x - font.getStringWidth(string) / 2), y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_BACKSLASH) {
				font.drawStringWithShadow(ChatFormatting.AQUA+str, (x - font.getStringWidth(string) / 2), y, 0);
			} else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_CIRCUMFLEX) {
				font.drawStringWithShadow(ChatFormatting.WHITE+str, (x - font.getStringWidth(string) / 2), y, 0);
			}
			x += font.getStringWidth(str);
			if (str != " ") {
				long1 += 500L;
			}
		}
	}

}
