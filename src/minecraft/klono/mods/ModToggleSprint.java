package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ModToggleSprint extends ModDraggable {
	private static boolean sprint = false;
	private static boolean sneak = false;

	@Override
	public int getWidth() {
		return font.getStringWidth("[Sprinting (Toggled)]") + 2;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT + 2;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_SPRINT.getKeyCode() == Keyboard.KEY_HOME) {
			if (mc.gameSettings.keyBindSprint.isPressed() && !mc.gameSettings.keyBindForward.isKeyDown()) {
				if (sprint) {
					sprint = false;
					mc.thePlayer.setSprinting(false);
				} else {
					sprint = true;
					sneak = false;
					mc.thePlayer.setSprinting(true);
					mc.gameSettings.keyBindSneak.pressed = false;
				}
			} else if (mc.gameSettings.keyBindSneak.isPressed() && !mc.gameSettings.keyBindForward.isKeyDown()) {
				if (sneak) {
					sneak = false;
					mc.gameSettings.keyBindSneak.pressed = false;
				} else {
					sneak = true;
					sprint = false;
					mc.gameSettings.keyBindSneak.pressed = true;
					mc.thePlayer.setSprinting(false);
					
				}
			}
			if (sprint && mc.gameSettings.keyBindForward.isKeyDown() && !mc.gameSettings.keyBindBack.isKeyDown() && !mc.thePlayer.isUsingItem()) {
				mc.thePlayer.setSprinting(true);
			}
			if (sprint) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 2, pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
				RainbowWave.drawStringWaveShadow("[Sprinting (Toggled)]", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
			} else if (sneak) {
				mc.gameSettings.keyBindSneak.pressed = true;
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + font.getStringWidth("[Sneaking (Toggled)]") + 2 + 2, pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
				RainbowWave.drawStringWaveShadow("[Sneaking (Toggled)]", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
			} else {
				if (mc.gameSettings.keyBindSprint.isKeyDown() && !mc.gameSettings.keyBindSneak.isKeyDown()) {
					Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + font.getStringWidth("[Sprinting]") + 2 + 2, pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
					RainbowWave.drawStringWaveShadow("[Sprinting]", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
				} else if (mc.gameSettings.keyBindSneak.isKeyDown() && !mc.gameSettings.keyBindSprint.isKeyDown()) {
					Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + font.getStringWidth("[Sneaking]") + 2 + 2, pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
					RainbowWave.drawStringWaveShadow("[Sneaking]", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
				}
			}
		}
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "Toggle Sprint", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight() + 2, new Color(0, 0, 0, 102).getRGB());
		RainbowWave.drawStringWaveShadow("Toggle Sprint/Sneak", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
	}

}
