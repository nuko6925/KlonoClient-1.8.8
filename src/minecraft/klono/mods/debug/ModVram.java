package klono.mods.debug;

import java.awt.Color;

import org.lwjgl.opengl.ATIMeminfo;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.NVXGpuMemoryInfo;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import klono.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ModVram extends ModDraggable {

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public void render(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		RainbowWave.drawStringWaveShadow(""+getVideoMemory() + " GB", pos.getAbsoluteX()+2, pos.getAbsoluteY()+2);
	}
	
	public static int getVideoMemory() {
		int nvx = GL11.glGetInteger(NVXGpuMemoryInfo.GL_GPU_MEMORY_INFO_TOTAL_AVAILABLE_MEMORY_NVX);
		int amd = GL11.glGetInteger(ATIMeminfo.GL_VBO_FREE_MEMORY_ATI);
		int total = Math.max(nvx, amd);
		int gb = total / 1024 / 1024;
		return gb;
	}

}
