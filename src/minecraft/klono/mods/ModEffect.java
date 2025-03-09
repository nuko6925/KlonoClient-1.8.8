package klono.mods;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class ModEffect extends ModDraggable{
	private int i = 0;
	
	@Override
	public int getWidth() {
		return font.getStringWidth("Water Breathing X **:**");
	}

	@Override
	public int getHeight() {
		return 32;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_EFFECT.getKeyCode() == Keyboard.KEY_HOME) {
			for (PotionEffect potionEffect : mc.thePlayer.getActivePotionEffects()) {
				Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
	            String s1 = I18n.format(potion.getName(), new Object[0]);
	            if (potionEffect.getAmplifier() == 1) {
	                s1 = s1 + " " + I18n.format("enchantment.level.2", new Object[0]);
	            } else if (potionEffect.getAmplifier() == 2) {
	                s1 = s1 + " " + I18n.format("enchantment.level.3", new Object[0]);
	            } else if (potionEffect.getAmplifier() == 3) {
	                s1 = s1 + " " + I18n.format("enchantment.level.4", new Object[0]);
	            }
				++i;
				int duration = potionEffect.getDuration() / 20;
				long minutes = (duration % 3600) / 60;
				long seconds = duration % 60;
				
				renderCurrentlyEffect(pos, i, s1 + " " + String.format("%02d:%02d", minutes, seconds));
			}
			i = 0;
		}
	}

	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "Effects", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + font.getStringWidth("Regeneration II **:**") + 4, pos.getAbsoluteY() + 16, new Color(0, 0, 0, 102).getRGB());
		}
		RainbowWave.drawStringWaveShadow("Regeneration II **:**", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 5);
//		font.drawString("Regeneration II **:**", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 5, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + 16, pos.getAbsoluteX() + font.getStringWidth("Strength V 00:05") + 4, pos.getAbsoluteY() + 32, new Color(0, 0, 0, 102).getRGB());
		}
		RainbowWave.drawStringWaveShadow("Strength V 00:05", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 21);
//		font.drawString("Strength V 00:05", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 21, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));

	}
	private void renderCurrentlyEffect(ScreenPosition pos, int i, String string) {
		if (string == null) {
			return;
		}
		int yAdd =(16 * i) - 112;
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd + 96, pos.getAbsoluteX() + font.getStringWidth(string) + 4, pos.getAbsoluteY() + yAdd  + 112, new Color(0, 0, 0, 102).getRGB());
		}
		RainbowWave.drawStringWaveShadow(string, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + yAdd + 100);
//		font.drawString(string, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + yAdd + 100, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
		
	}

}