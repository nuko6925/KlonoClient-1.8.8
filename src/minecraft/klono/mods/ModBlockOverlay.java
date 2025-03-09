package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.mojang.realmsclient.gui.ChatFormatting;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.WorldSettings.GameType;

public class ModBlockOverlay extends ModDraggable {
	public static boolean overlay = false;

	@Override
	public int getWidth() {
		return font.getStringWidth("can toggle block overlay in Game Settings -> Controls");
	}

	@Override
	public int getHeight() {
		return 48;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.OVERLAY.getKeyCode() == Keyboard.KEY_HOME) {
			overlay = false;
		}
		if (mc.gameSettings.OVERLAY.getKeyCode() == Keyboard.KEY_AX) {
			overlay = true;
		}
		if (mc.gameSettings.TOGGLE_OVERLAY.isPressed()) {
			if (overlay) {
				overlay = false;
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "Block Overlay turned off."));
				mc.gameSettings.OVERLAY.setKeyCode(Keyboard.KEY_HOME);
			} else {
				overlay = true;
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "Block Overlay turned on."));
				mc.gameSettings.OVERLAY.setKeyCode(Keyboard.KEY_AX);
			}
		}
/*		if (overlay) {
			if (mc.thePlayer != null && mc.theWorld != null && (mc.playerController.getCurrentGameType().equals(GameType.SURVIVAL) || mc.playerController.getCurrentGameType().equals(GameType.CREATIVE))) {
				this.drawOverlay();
			}
		}*/
	}

	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "BlockOverlay Message", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		RainbowWave.drawStringWaveShadow("This is BlockOverlay setting, but can't show", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 5);
		RainbowWave.drawStringWaveShadow("can toggle block overlay in Game Settings -> Controls", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 21);
		RainbowWave.drawStringWaveShadow("Now Key: "+Keyboard.getKeyName(mc.gameSettings.TOGGLE_OVERLAY.getKeyCode()), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 37);
	}
/*
	public void drawOverlay() {
		if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit.equals(MovingObjectType.BLOCK)) {
			MovingObjectPosition position = mc.thePlayer.rayTrace(6.0D, 0.0F);
			if (position != null && position.typeOfHit.equals(MovingObjectType.BLOCK)) {
				Block block = mc.thePlayer.worldObj.getBlockState(position.getBlockPos()).getBlock();
				if (block != null && !block.equals(Blocks.air) && !block.equals(Blocks.barrier) && !block.equals(Blocks.water) && !block.equals(Blocks.flowing_water) && !block.equals(Blocks.lava) && !block.equals(Blocks.flowing_lava)) {
					GlStateManager.pushMatrix();
					GlStateManager.depthMask(false);
					GlStateManager.enableBlend();
					GlStateManager.disableTexture2D();
					GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
					GL11.glLineWidth(5.0F);
					AxisAlignedBB box = block.getSelectedBoundingBox(mc.theWorld, position.getBlockPos()).offset(-mc.getRenderManager().viewerPosX, -mc.getRenderManager().viewerPosY, -mc.getRenderManager().viewerPosZ).expand(0.0010000000474974513D, 0.0010000000474974513D, 0.0010000000474974513D);
					Color color;
					
					color = Color.getHSBColor((float)((System.currentTimeMillis() - 500L) % 10000L) / 10000L, 0.8F, 0.8F);
					GL11.glColor4f((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)color.getAlpha());
					
					RenderGlobal.func_181561_a(box);
					
					GlStateManager.enableTexture2D();
					GlStateManager.disableBlend();
					GlStateManager.depthMask(true);
					GlStateManager.popMatrix();
				}
			}
			
		}
	}*/
}