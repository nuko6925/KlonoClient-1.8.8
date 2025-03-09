package klono.mods;

import java.awt.Color;
import java.text.DecimalFormat;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;

public class ModReachDisplay extends ModDraggable {
	private Minecraft mc = Minecraft.getMinecraft();
	private long lastattack;
	private boolean wasPressed;
	
	private String reach = "-.-- blocks";

	@Override
	public int getWidth() {
		return font.getStringWidth("-3.00 blocks") + 2;
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
			if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectType.ENTITY && mc.currentScreen == null && pressed) {
				final Vec3 vec3 = this.mc.getRenderViewEntity().getPositionEyes(1.0F);
				reach = (new DecimalFormat("#.##")).format(this.mc.objectMouseOver.hitVec.distanceTo(vec3)) + " blocks";
				this.lastattack = System.currentTimeMillis();
			}
		}
			if (mc.gameSettings.TOGGLE_REACH.getKeyCode() == Keyboard.KEY_HOME) {
				if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
					Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
				}
				RainbowWave.drawStringWaveShadow(reach, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
//			font.drawString(reach, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
			}
		if (System.currentTimeMillis() - this.lastattack > 2000L) {
			this.reach = "-.-- blocks";
		}
		
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "ReachDisplay", pos.getAbsoluteX(), pos.getAbsoluteY() - 8, -1);
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		}
		RainbowWave.drawStringWaveShadow("99.99 blocks", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
//		font.drawString("99.99 blocks", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
		
	}
}
