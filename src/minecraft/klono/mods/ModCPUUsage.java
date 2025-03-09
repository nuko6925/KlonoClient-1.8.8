package klono.mods;

import java.awt.Color;
import java.lang.management.ManagementFactory;

import org.lwjgl.input.Keyboard;

import com.sun.management.OperatingSystemMXBean;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;

public class ModCPUUsage extends ModDraggable {
	private static OperatingSystemMXBean osMx = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

	@Override
	public int getWidth() {
		return font.getStringWidth("CPU: 100.0%") + 2;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT + 2;
	}

	@Override
	public void render(ScreenPosition pos) {
/*		if (mc.gameSettings.CPU_LOAD.getKeyCode() == Keyboard.KEY_HOME) {
			if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
			}
			RainbowWave.drawStringWaveShadow("CPU: " + getCPULoad() + "%", pos.getAbsoluteX()+2, pos.getAbsoluteY()+2);
//			font.drawString("" + Minecraft.playing, pos.getAbsoluteX()+2, pos.getAbsoluteY()+2, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
		}*/
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + font.getStringWidth("CPU Load") + 2, pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "CPU Load", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + font.getStringWidth("CPU: 100.0%") + 2, pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		}
		RainbowWave.drawStringWaveShadow("CPU: " + getCPULoad() + "%", pos.getAbsoluteX()+2, pos.getAbsoluteY()+2);
//		font.drawString("mc.hypixel.net", pos.getAbsoluteX()+2, pos.getAbsoluteY()+2, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
	}
	
	public static String getCPULoad() {
		double load = osMx.getProcessCpuLoad();
		if (load == -1.0) {
			return "0";
		}
		return ""+((int)(load * 1000) / 10.0);
	}


}
