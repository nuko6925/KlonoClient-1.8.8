package klono.mods;

import java.awt.Color;
import java.util.List;

import com.google.common.collect.Lists;

import klono.gui.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class ModSettings extends ModDraggable {
	
	@Override
	public int getWidth() {
		return 120;
	}

	@Override
	public int getHeight() {
		return 120;
	}

	@Override
	public void render(ScreenPosition pos) {
		
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth()-11, pos.getAbsoluteY() + 11, new Color(255, 255, 255, 190).getRGB());
		Gui.drawRect(pos.getAbsoluteX() + getWidth() - 11, pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + 11, new Color(255, 0, 0, 190).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "HUD Manager", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, -1);
		font.drawString("X", pos.getAbsoluteX() + getWidth() - 8, pos.getAbsoluteY() + 3, -1);
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + 11, pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 190).getRGB());
		
	}

}
