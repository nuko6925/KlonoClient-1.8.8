package klono.mods;

import java.util.List;

import javax.swing.event.MenuDragMouseEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import klono.Client;
import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ModCPS extends ModDraggable {
	
	private List<Long> clicks = new ArrayList<Long>();
	private boolean wasPressed;

	@Override
	public int getWidth() {
		return font.getStringWidth("CPS: 000");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT + 2;
	}

	@Override
	public void render(ScreenPosition pos) {
		
		final boolean pressed = Mouse.isButtonDown(0);
		
		if (pressed != this.wasPressed) {
			this.wasPressed = pressed;
			if (pressed && mc.currentScreen == null) {
				this.clicks.add(System.currentTimeMillis());
			}
		}
		if (mc.gameSettings.TOGGLE_CPS.getKeyCode() == Keyboard.KEY_HOME) {
			if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
			}
			RainbowWave.drawStringWaveShadow("CPS: " + getCPS(), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
//			font.drawString("CPS: " + getCPS(), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
		}
		

	}
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "CPS", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		}
		RainbowWave.drawStringWaveShadow("CPS: 99", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
//		font.drawString("CPS: 99", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
	}
	
	private int getCPS() {
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		return this.clicks.size();
	}


}
