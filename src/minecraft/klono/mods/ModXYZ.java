package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

public class ModXYZ extends ModDraggable {
	@Override
	public int getWidth() {
		return 75;
	}

	@Override
	public int getHeight() {
		return 40;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_XYZ.getKeyCode() == Keyboard.KEY_HOME) {
			if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
			}
			RainbowWave.drawStringWaveShadow("X: " + (int)mc.thePlayer.posX, pos.getAbsoluteX()+3, pos.getAbsoluteY()+5);
			RainbowWave.drawStringWaveShadow("Y: " + (int)mc.thePlayer.posY, pos.getAbsoluteX()+3, pos.getAbsoluteY()+17);
			RainbowWave.drawStringWaveShadow("Z: " + (int)mc.thePlayer.posZ, pos.getAbsoluteX()+3, pos.getAbsoluteY()+29);
//			font.drawString("X: " + (int)mc.thePlayer.posX, pos.getAbsoluteX()+3, pos.getAbsoluteY()+5, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
//			font.drawString("Y: " + (int)mc.thePlayer.posY, pos.getAbsoluteX()+3, pos.getAbsoluteY()+17, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
//			font.drawString("Z: " + (int)mc.thePlayer.posZ, pos.getAbsoluteX()+3, pos.getAbsoluteY()+29, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
			
			String Face = "" + mc.thePlayer.getHorizontalFacing();
			if (Face.equals("east")) {
				Face = "E";
			} else if (Face.equals("west")) {
				Face = "W";
			} else if (Face.equals("north")) {
				Face = "N";
			} else if (Face.equals("south")) {
				Face = "S";
			}
			RainbowWave.drawStringWaveShadow(Face, pos.getAbsoluteX()+55, pos.getAbsoluteY()+12);
//			font.drawStringWithShadow(Face, pos.getAbsoluteX()+55, pos.getAbsoluteY()+12, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
			float rawyaw = Float.valueOf(MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw));
			String yaw = ""+(int)rawyaw;
			int offX = font.getStringWidth("-");
			if (yaw.length() <= 3) {
				RainbowWave.drawStringWaveShadow(yaw, pos.getAbsoluteX() + 45 + offX, pos.getAbsoluteY()+22);
//				font.drawString(yaw, pos.getAbsoluteX() + 45 + offX, pos.getAbsoluteY()+22, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
			} else {
				RainbowWave.drawStringWaveShadow(yaw, pos.getAbsoluteX() + 45, pos.getAbsoluteY()+22);
//				font.drawString(yaw, pos.getAbsoluteX() + 45, pos.getAbsoluteY()+22, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
			}
		}

	}
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "Coordinates", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		if (mc.gameSettings.TOGGLE_XYZ.getKeyCode() == Keyboard.KEY_HOME) {
			if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
			}
			RainbowWave.drawStringWaveShadow("X: " + (int)mc.thePlayer.posX, pos.getAbsoluteX()+3, pos.getAbsoluteY()+5);
			RainbowWave.drawStringWaveShadow("Y: " + (int)mc.thePlayer.posY, pos.getAbsoluteX()+3, pos.getAbsoluteY()+17);
			RainbowWave.drawStringWaveShadow("Z: " + (int)mc.thePlayer.posZ, pos.getAbsoluteX()+3, pos.getAbsoluteY()+29);
//			font.drawString("X: " + (int)mc.thePlayer.posX, pos.getAbsoluteX()+3, pos.getAbsoluteY()+5, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
//			font.drawString("Y: " + (int)mc.thePlayer.posY, pos.getAbsoluteX()+3, pos.getAbsoluteY()+17, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
//			font.drawString("Z: " + (int)mc.thePlayer.posZ, pos.getAbsoluteX()+3, pos.getAbsoluteY()+29, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
			
			String Face = "" + mc.thePlayer.getHorizontalFacing();
			if (Face.equals("east")) {
				Face = "E";
			} else if (Face.equals("west")) {
				Face = "W";
			} else if (Face.equals("north")) {
				Face = "N";
			} else if (Face.equals("south")) {
				Face = "S";
			}
			font.drawStringWithShadow(Face, pos.getAbsoluteX()+55, pos.getAbsoluteY()+12, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
			float rawyaw = Float.valueOf(MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw));
			String yaw = ""+(int)rawyaw;
			int offX = font.getStringWidth("-");
			if (yaw.length() <= 3) {
				RainbowWave.drawStringWaveShadow(yaw, pos.getAbsoluteX() + 45 + offX, pos.getAbsoluteY()+22);
//				font.drawString(yaw, pos.getAbsoluteX() + 45 + offX, pos.getAbsoluteY()+22, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
			} else {
				RainbowWave.drawStringWaveShadow(yaw, pos.getAbsoluteX() + 45, pos.getAbsoluteY()+22);
//				font.drawString(yaw, pos.getAbsoluteX() + 45, pos.getAbsoluteY()+22, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
			}
		}
	}


}
