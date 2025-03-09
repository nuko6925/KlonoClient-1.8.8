package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import klono.Client;
import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.klono.lib.TickRate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ModTPS extends ModDraggable {

	@Override
	public int getWidth() {
		return font.getStringWidth("TPS: " + TickRate.TPS) + 2;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT + 2;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_TPS.getKeyCode() == Keyboard.KEY_HOME) {
			if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
			} else {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, new Color(0, 0, 0, 102).getRGB());
			}
			RainbowWave.drawStringWaveShadow("TPS: " + TickRate.TPS, pos.getAbsoluteX()+2, pos.getAbsoluteY()+2);
		}
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "TPS", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
	}


}
