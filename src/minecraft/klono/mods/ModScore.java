package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

public class ModScore extends ModDraggable{
	public static String[] scores;
	private int i = 0;
	
	@Override
	public int getWidth() {
		return 32;
	}

	@Override
	public int getHeight() {
		return 9;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (scores != null) {
			int lo = 0;
			int i2 = 0;
			for (String str : scores) {
				if (font.getStringWidth(str) > i2) {
					i2 = font.getStringWidth(str) + 19;
				}
				++lo;
			}
			i = 0;
			for (String string : scores) {
				if (string != null) {
					++i;
					if (i == 1) {
						Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + (i * 9 - 9), pos.getAbsoluteX() + i2 + 2, pos.getAbsoluteY() + (i * 9), new Color(0, 0, 0, 142).getRGB());
						int i3 = (i2 + 3) / 2;
						int l = font.getStringWidth(string) / 2;
						int ir = i3 - l;
//						font.drawString(string, pos.getAbsoluteX() + ir, pos.getAbsoluteY() + 1 + (i * 9 - 9), Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
						RainbowWave.drawStringWave(string, pos.getAbsoluteX() + ir - 2, pos.getAbsoluteY() + 1 + (i * 9 - 9));
					} else {
						Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + (i * 9 - 9), pos.getAbsoluteX() + i2 + 2, pos.getAbsoluteY() + (i * 9), new Color(0, 0, 0, 102).getRGB());
						RainbowWave.drawStringWave(string, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 1 + (i * 9 - 9));
//						font.drawString(string, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 1 + (i * 9 - 9), Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
						if ((lo - i + 1) >= 10) {
							font.drawString(EnumChatFormatting.RED + "" + (lo - i + 1), pos.getAbsoluteX() + i2 - 10, pos.getAbsoluteY() + 1 + (i * 9 - 9), -1);
						} else {
							font.drawString(EnumChatFormatting.RED + "" + (lo - i + 1), pos.getAbsoluteX() + i2 - 4, pos.getAbsoluteY() + 1 + (i * 9 - 9), -1);
						}
					}
				}
			}
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + (i * 9), pos.getAbsoluteX() + i2 + 2, pos.getAbsoluteY() + (i * 9 + 1), new Color(0, 0, 0, 102).getRGB());
		}
	}

	public void renderDummy(ScreenPosition pos) {
		if (scores != null) {
			int lo = 0;
			int i2 = 0;
			for (String str : scores) {
				if (font.getStringWidth(str) > i2) {
					i2 = font.getStringWidth(str) + 19;
				}
				++lo;
			}
			i = 0;
			for (String string : scores) {
				if (string != null) {
					++i;
					if (i == 1) {
						Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + (i * 9 - 9), pos.getAbsoluteX() + i2 + 2, pos.getAbsoluteY() + (i * 9), new Color(0, 0, 0, 142).getRGB());
						int i3 = (i2 + 3) / 2;
						int l = font.getStringWidth(string) / 2;
						int ir = i3 - l;
//						font.drawString(string, pos.getAbsoluteX() + ir, pos.getAbsoluteY() + 1 + (i * 9 - 9), Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
						RainbowWave.drawStringWave(string, pos.getAbsoluteX() + ir - 2, pos.getAbsoluteY() + 1 + (i * 9 - 9));
					} else {
						Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + (i * 9 - 9), pos.getAbsoluteX() + i2 + 2, pos.getAbsoluteY() + (i * 9), new Color(0, 0, 0, 102).getRGB());
						RainbowWave.drawStringWave(string, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 1 + (i * 9 - 9));
//						font.drawString(string, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 1 + (i * 9 - 9), Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
						if ((lo - i + 1) >= 10) {
							font.drawString(EnumChatFormatting.RED + "" + (lo - i + 1), pos.getAbsoluteX() + i2 - 10, pos.getAbsoluteY() + 1 + (i * 9 - 9), -1);
						} else {
							font.drawString(EnumChatFormatting.RED + "" + (lo - i + 1), pos.getAbsoluteX() + i2 - 4, pos.getAbsoluteY() + 1 + (i * 9 - 9), -1);
						}
					}
				}
			}
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + (i * 9), pos.getAbsoluteX() + i2 + 2, pos.getAbsoluteY() + (i * 9 + 1), new Color(0, 0, 0, 102).getRGB());
		}
	}

}