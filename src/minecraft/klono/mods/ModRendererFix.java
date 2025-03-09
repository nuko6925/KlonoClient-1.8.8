package klono.mods;

import java.awt.Color;

import klono.gui.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;

public class ModRendererFix extends ModDraggable {

	@Override
	public int getHeight() {
		return 1;
	}

	@Override
	public int getWidth() {
		return 1;
	}

	@Override
	public void render(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, new Color(0, 0, 0, 102).getRGB());
		
	}

}
