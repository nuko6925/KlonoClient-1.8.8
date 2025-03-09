package klono.mods;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.input.Keyboard;

import klono.Client;
import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;

public class ModCalc extends ModDraggable {

	@Override
	public int getWidth() {
		return font.getStringWidth("can toggle browser in Game Settings -> Controls");
	}

	@Override
	public int getHeight() {
		return 48;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_CALC.isPressed()) {
			Client.calc.setVisible(true);
		}
	}

	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "Calc Message", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		RainbowWave.drawStringWaveShadow("This is Clac setting, but can't show", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 5);
		RainbowWave.drawStringWaveShadow("can toggle calc in Game Settings -> Controls", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 21);
		RainbowWave.drawStringWaveShadow("Now Key: "+Keyboard.getKeyName(mc.gameSettings.TOGGLE_CALC.getKeyCode()), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 37);
	}

}
