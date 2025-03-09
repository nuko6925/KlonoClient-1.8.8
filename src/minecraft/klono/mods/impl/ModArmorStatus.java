package klono.mods.impl;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import klono.gui.hud.ScreenPosition;
import klono.mods.ModDraggable;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ModArmorStatus extends ModDraggable{
	
	private static int arrow;
	private static int tools;
	
	@Override
	public int getWidth() {
		return 64;
	}

	@Override
	public int getHeight() {
		return 96;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_ARMOR.getKeyCode() == Keyboard.KEY_HOME) {
			for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
				ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
				renderItemStack(pos, i, itemStack);
			}
			ItemStack itemStack = mc.thePlayer.getHeldItem();
			tools = 0;
			if (itemStack != null && itemStack.isStackable()) {
				ItemStack hands = mc.thePlayer.inventory.getCurrentItem();
				tools = hands.stackSize;
			}
			renderItemStack(pos, 4, itemStack);
			if (itemStack != null && itemStack.getItem().equals(Items.bow)) {
				this.arrow = 0;
				for (ItemStack slot : mc.thePlayer.inventory.mainInventory) {
					if (slot != null && this.isArrow(slot)) {
						this.arrow += slot.stackSize;
					}
				}
				if (this.arrow != 0) {
					renderItemStack(pos, 5, new ItemStack(Items.arrow));
				}
			}
		}
	}
	
	private boolean isArrow(ItemStack stack) {
		return stack.getItem().equals(Items.arrow);
	}

	public void renderDummy(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		}
		font.drawString(EnumChatFormatting.DARK_GRAY + "Armor Status", pos.getAbsoluteX(), pos.getAbsoluteY() - 8, -1);
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + 16, pos.getAbsoluteX() + getWidth() - 5, pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());

		renderItemStack(pos, 5, new ItemStack(Blocks.barrier, 64));
		renderItemStack(pos, 4, new ItemStack(Items.bow));
		renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
		renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
		renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
		renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));

	}

	private void renderItemStack(ScreenPosition pos, int i, ItemStack is) {

		if(is == null) {
			return;
		}

		GL11.glPushMatrix();
		int yAdd =(-16 * i) + 80;

		if (is.getItem().isDamageable()) {
			double damage = ((is.getMaxDamage() - is.getItemDamage())/ (double) is.getMaxDamage()) * 100;
			EnumChatFormatting color = null;
			if (damage >= 0) {
    			if (damage <= 20) {
    				color = EnumChatFormatting.DARK_RED;
    			} else if (damage > 20 && damage <= 50) {
                    color = EnumChatFormatting.RED;
                } else if (damage > 50 && damage <= 70) {
                    color = EnumChatFormatting.YELLOW;
                } else if (damage > 70 && damage <= 100) {
                    color = EnumChatFormatting.GREEN;
                } else if (damage >= 99) {
                    color = EnumChatFormatting.DARK_GREEN;
                }
			}
			if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd, pos.getAbsoluteX() + getWidth() - 5, pos.getAbsoluteY() + yAdd + 16, new Color(0, 0, 0, 102).getRGB());
			}
			font.drawStringWithShadow(String.format(color + "%.2f%%", damage), pos.getAbsoluteX() + 18, pos.getAbsoluteY() + yAdd + 4, -1);
		} else {
			if (i == 4) {
				if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
					Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd, pos.getAbsoluteX() + 20 + font.getStringWidth(""+this.tools), pos.getAbsoluteY() + yAdd + 16, new Color(0, 0, 0, 102).getRGB());
				}
				if (this.tools != 0) {
					font.drawStringWithShadow(EnumChatFormatting.WHITE + ""+this.tools, pos.getAbsoluteX() + 18, pos.getAbsoluteY() + yAdd + 4, -1);
				}
			}
			if (i == 5) {
				if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
					Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd, pos.getAbsoluteX() + 20 + font.getStringWidth(""+this.arrow), pos.getAbsoluteY() + yAdd + 16, new Color(0, 0, 0, 102).getRGB());
				}
				font.drawStringWithShadow(EnumChatFormatting.WHITE + ""+this.arrow, pos.getAbsoluteX() + 18, pos.getAbsoluteY() + yAdd + 4, -1);
			}
		}

		RenderHelper.enableGUIStandardItemLighting();
		mc.getRenderItem().renderItemAndEffectIntoGUI(is,  pos.getAbsoluteX()+1, pos.getAbsoluteY() + yAdd);

		GL11.glPopMatrix();

	}

}