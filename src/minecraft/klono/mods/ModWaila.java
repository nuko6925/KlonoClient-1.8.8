package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldType;

public class ModWaila extends ModDraggable {

	@Override
	public int getWidth() {
		return "Currently Harvestable".length() * 7 + 4;
	}

	@Override
	public int getHeight() {
		return 35;
	}

	@Override
	public void render(ScreenPosition pos) {
		 if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && mc.objectMouseOver.getBlockPos() != null && mc.gameSettings.TOGGLE_WAILA.getKeyCode() == Keyboard.KEY_HOME) {
			 BlockPos blockpos = this.mc.objectMouseOver.getBlockPos();
             IBlockState iblockstate = this.mc.theWorld.getBlockState(blockpos);

             if (this.mc.theWorld.getWorldType() != WorldType.DEBUG_WORLD)
             {
                 iblockstate = iblockstate.getBlock().getActualState(iblockstate, this.mc.theWorld, blockpos);
             }
             String blockName = iblockstate.getBlock().getLocalizedName();
             Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
             RainbowWave.drawStringWaveShadow(blockName, pos.getAbsoluteX()+22, pos.getAbsoluteY()+5);
             String blockId = iblockstate.getBlock().toString().replace("Block{minecraft:", "");
             RainbowWave.drawStringWaveShadow("minecraft:"+blockId.replace("}", ""), pos.getAbsoluteX()+22, pos.getAbsoluteY()+22);
//             font.drawString(blockName, pos.getAbsoluteX()+24, pos.getAbsoluteY()+5, -1);
             renderItemStack(pos, new ItemStack(iblockstate.getBlock()));
		 }
		
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		renderItemStack(pos, new ItemStack(Blocks.emerald_ore));
	}

	private void renderItemStack(ScreenPosition pos, ItemStack is) {

		if(is == null) {
			return;
		}
		GL11.glPushMatrix();

		RenderHelper.enableGUIStandardItemLighting();
		mc.getRenderItem().renderItemAndEffectIntoGUI(is,  pos.getAbsoluteX()+3, pos.getAbsoluteY()+9);

		GL11.glPopMatrix();

	}
	

}
