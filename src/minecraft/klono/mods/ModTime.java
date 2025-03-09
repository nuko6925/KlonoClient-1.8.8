package klono.mods;

import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.lwjgl.input.Keyboard;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;

public class ModTime extends ModDraggable {

	@Override
	public int getWidth() {
		return 45;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT + 2;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_TIME.getKeyCode() == Keyboard.KEY_HOME) {
			if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			Date resultDate = new Date(System.currentTimeMillis());
			RainbowWave.drawStringWaveShadow("" + sdf.format(resultDate), pos.getAbsoluteX()+2, pos.getAbsoluteY()+2);
//			font.drawString("" + sdf.format(resultDate), pos.getAbsoluteX()+2, pos.getAbsoluteY()+2, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
		}
	}
	
	public void renderDummy(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date resultDate = new Date(System.currentTimeMillis());
		RainbowWave.drawStringWaveShadow("" + sdf.format(resultDate), pos.getAbsoluteX()+2, pos.getAbsoluteY()+2);
	}


}
