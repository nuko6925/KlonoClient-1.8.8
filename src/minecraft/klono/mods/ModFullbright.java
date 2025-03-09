package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ModFullbright extends ModDraggable {
	private static boolean bright = false;

	@Override
	public int getWidth() {
		return font.getStringWidth("can toggle fullbright in Game Settings -> Controls");
	}

	@Override
	public int getHeight() {
		return 48;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_FULLBRIGHT.isPressed()) {
			if (bright) {
				bright = false;
				mc.gameSettings.gammaSetting = 0F;
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "Fullbright turned off."));
			} else {
				bright = true;
				mc.gameSettings.gammaSetting = 120.0F;
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "Fullbright turned on."));
			}
		}
	}

	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "Fullbright Message", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		RainbowWave.drawStringWaveShadow("This is Fullbright setting, but can't show", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 5);
		RainbowWave.drawStringWaveShadow("can toggle fullbright in Game Settings -> Controls", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 21);
		RainbowWave.drawStringWaveShadow("Now Key: "+Keyboard.getKeyName(mc.gameSettings.TOGGLE_FULLBRIGHT.getKeyCode()), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 37);
	}
}