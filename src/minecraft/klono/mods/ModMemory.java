package klono.mods;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ModMemory extends ModDraggable {
	private static boolean gc = false;

	@Override
	public int getWidth() {
		DecimalFormat format_mem = new DecimalFormat("#,### MB");
		long max = Runtime.getRuntime().totalMemory() / 1024 / 1024;
		long free = Runtime.getRuntime().freeMemory() / 1024 / 1024;
		long use = max - free;
		return font.getStringWidth(""+format_mem.format(use) + "/ "+format_mem.format(max)) + 2;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT + 2;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		}
		DecimalFormat format_mem = new DecimalFormat("#,### MB");
		DecimalFormat format_ratio = new DecimalFormat("##.#");
		long max = Runtime.getRuntime().totalMemory() / 1024 / 1024;
		long free = Runtime.getRuntime().freeMemory() / 1024 / 1024;
		long use = max - free;
		double ratio = (use * 100 / (double)max);
		RainbowWave.drawStringWaveShadow(""+format_mem.format(use) + "/ "+format_mem.format(max), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
		if (mc.gameSettings.RUN_GC.isPressed()) {
			if (gc == false) {
				gc = true;
				mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW+"Request Full GC."));
				Runtime.getRuntime().gc();
				mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN+"Full GC is Complete!"));
				gc = false;
			} else {
				mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"Full GC is Running!"));
			}
		}
		
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "Memory", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		}
		DecimalFormat format_mem = new DecimalFormat("#,### MB");
		DecimalFormat format_ratio = new DecimalFormat("##.#");
		long max = Runtime.getRuntime().totalMemory() / 1024 / 1024;
		long free = Runtime.getRuntime().freeMemory() / 1024 / 1024;
		long use = max - free;
		double ratio = (use * 100 / (double)max);
		RainbowWave.drawStringWaveShadow(""+format_mem.format(use) + "/ "+format_mem.format(max), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
		if (mc.gameSettings.RUN_GC.getKeyCode() != Keyboard.KEY_NONE) {
			if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + getHeight(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight() + 14, new Color(0, 0, 0, 102).getRGB());
			}
			RainbowWave.drawStringWaveShadow("Now Key: "+Keyboard.getKeyName(mc.gameSettings.RUN_GC.getKeyCode()), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + getHeight() + 2);
		}
		
	}

}
