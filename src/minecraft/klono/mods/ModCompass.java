package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

public class ModCompass extends ModDraggable {
	
	private static String[] format = {
			"E-----*-----S-----*-----W",
			"-----*-----S-----*-----W-",
			"----*-----S-----*-----W--",
			"---*-----S-----*-----W---",
			"--*-----S-----*-----W----",
			"-*-----S-----*-----W-----",
			"*-----S-----*-----W-----*",
			"-----S-----*-----W-----*-",
			"----S-----*-----W-----*--",
			"---S-----*-----W-----*---",
			"--S-----*-----W-----*----",
			"-S-----*-----W-----*-----",
			"S-----*-----W-----*-----N",
			"-----*-----W-----*-----N-",
			"----*-----W-----*-----N--",
			"---*-----W-----*-----N---",
			"--*-----W-----*-----N----",
			"-*-----W-----*-----N-----",
			"*-----W-----*-----N-----*",
			"-----W-----*-----N-----*-",
			"----W-----*-----N-----*--",
			"---W-----*-----N-----*---",
			"--W-----*-----N-----*----",
			"-W-----*-----N-----*-----",
			"W-----*-----N-----*-----E",
			"-----*-----N-----*-----E-",
			"----*-----N-----*-----E--",
			"---*-----N-----*-----E---",
			"--*-----N-----*-----E----",
			"-*-----N-----*-----E-----",
			"*-----N-----*-----E-----*",
			"-----N-----*-----E-----*-",
			"----N-----*-----E-----*--",
			"---N-----*-----E-----*---",
			"--N-----*-----E-----*----",
			"-N-----*-----E-----*-----",
			"N-----*-----E-----*-----S",
			"-----*-----E-----*-----S-",
			"----*-----E-----*-----S--",
			"---*-----E-----*-----S---",
			"--*-----E-----*-----S----",
			"-*-----E-----*-----S-----",
			"*-----E-----*-----S-----*",
			"-----E-----*-----S-----*-",
			"----E-----*-----S-----*--",
			"---E-----*-----S-----*---",
			"--E-----*-----S-----*----",
			"-E-----*-----S-----*-----",
			};
	private static Minecraft mc = Minecraft.getMinecraft();
	public int twidth = "S-----*-----W-----*-----N".length() / 2;
	private static String bef = "";

	@Override
	public int getWidth() {
		return font.getStringWidth("S-----*-----W-----*-----N");
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_COMPASS.getKeyCode() == Keyboard.KEY_HOME) {
			renderCompass(pos);
		}
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + font.getStringWidth("Compass") + 2, pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "Compass", pos.getAbsoluteX()+2, pos.getAbsoluteY() - 8, -1);
		renderCompass(pos);
	}
		
	private void renderCompass(ScreenPosition pos) {
		int direction = normalize((int)mc.thePlayer.rotationYaw);
//		int round = "S-----*-----W-----*-----N-----*-----E-----*-----".length();
		double block = (double)360 / (double)48;
		int now = (int) (direction / block);
		if (now == 48) {
			now = 0;
		}
//		System.out.println("now "+now);
		float rawyaw = Float.valueOf(MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw));
		int dir = (int)rawyaw;
//		System.out.println("off "+offsetAll);
//		System.out.println("off2 "+offset);
//		System.out.println("base "+base);
//		System.out.println("format "+cwidth);
//		String compass = "S-----*-----W-----*-----N-----*-----E-----*-----S-----*-----W-----*-----N-----*-----E-----*-----S-----*-----W-----*-----N-----*-----E-----*-----S-----*-----W-----*-----N-----*-----E-----*-----S-----*-----W-----*-----N-----*-----E-----*-----S-----*-----W-----*-----N-----*-----E-----*-----S-----*-----W-----*-----N-----*-----E-----*-----S-----*-----W-----*-----N-----*-----E-----*-----";
//		System.out.println(compass.substring(base-twidth, base+twidth+1));
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 2, pos.getAbsoluteY() + getHeight() + 2, new Color(0, 0, 0, 102).getRGB());
		}
		RainbowWave.drawStringWaveShadow(format[now], pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
		RainbowWave.drawCenteredStringWaveShadow(""+dir, pos.getAbsoluteX() + (getWidth() / 2), pos.getAbsoluteY() + 12);
//		font.drawString(EnumChatFormatting.WHITE + format[now], pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, -1);
//		font.drawString(EnumChatFormatting.WHITE + ""+direction, pos.getAbsoluteX() + (getWidth() / 2), pos.getAbsoluteY() + 12, -1);
	}
	
	private int normalize(int direction) {
		if (direction > 360) {
			direction %= 360;
		}
		while(direction < 0) {
			direction += 360;
		}
		return direction;
	}


}
