package net.minecraft.client.renderer.debug;

import org.lwjgl.opengl.GL11;

import klono.mods.debug.ModChunk;
import net.klono.lib.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOverlayDebug.IDebugRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;

public class DebugRendererChunkBorder implements IDebugRenderer{
	private final Minecraft minecraft;

    public DebugRendererChunkBorder(Minecraft minecraftIn)
    {
        this.minecraft = minecraftIn;
    }

    public void render()
    {
    	if (ModChunk.b) {
	        EntityPlayer entityplayer = this.minecraft.thePlayer;
	        Tessellator tessellator = Tessellator.getInstance();
	        WorldRenderer bufferbuilder = tessellator.getWorldRenderer();
	        double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)this.minecraft.timer.renderPartialTicks;
	        double d1 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)this.minecraft.timer.renderPartialTicks;
	        double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)this.minecraft.timer.renderPartialTicks;
	        double d3 = 0.0D - d1;
	        double d4 = 256.0D - d1;
	        GL11.glPushMatrix();
	        GlStateManager.disableTexture2D();
	        GlStateManager.disableBlend();
	        double d5 = (double)(entityplayer.chunkCoordX << 4) - d0;
	        double d6 = (double)(entityplayer.chunkCoordZ << 4) - d2;
	        GlStateManager.glLineWidth(1.0F);
	        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
	
	        for (int i = -16; i <= 32; i += 16)
	        {
	            for (int j = -16; j <= 32; j += 16)
	            {
//	            	RenderUtils.drawWorldLine(entityplayer.posX, 0, entityplayer.posZ, entityplayer.posX, 255.0, entityplayer.posZ, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
//	            	RenderUtils.drawWorldLine(entityplayer.posX, 0, entityplayer.posZ, entityplayer.posX, 255.0, entityplayer.posZ, 1.0F, 1.0F, 0.0F, 0.0F, 0.5F);
	                bufferbuilder.pos(d5 + (double)i, d3, d6 + (double)j).color(1.0F, 0.0F, 0.0F, 0.0F).endVertex();
	                bufferbuilder.pos(d5 + (double)i, d3, d6 + (double)j).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
	                bufferbuilder.pos(d5 + (double)i, d4, d6 + (double)j).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
	                bufferbuilder.pos(d5 + (double)i, d4, d6 + (double)j).color(1.0F, 0.0F, 0.0F, 0.0F).endVertex();
	            }
	        }
	        for (int k = 2; k < 16; k += 2)
	        {
	            bufferbuilder.pos(d5 + (double)k, d3, d6).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	            bufferbuilder.pos(d5 + (double)k, d3, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + (double)k, d4, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + (double)k, d4, d6).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	            bufferbuilder.pos(d5 + (double)k, d3, d6 + 16.0D).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	            bufferbuilder.pos(d5 + (double)k, d3, d6 + 16.0D).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + (double)k, d4, d6 + 16.0D).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + (double)k, d4, d6 + 16.0D).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	        }
	
	        for (int l = 2; l < 16; l += 2)
	        {
	            bufferbuilder.pos(d5, d3, d6 + (double)l).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	            bufferbuilder.pos(d5, d3, d6 + (double)l).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5, d4, d6 + (double)l).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5, d4, d6 + (double)l).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	            bufferbuilder.pos(d5 + 16.0D, d3, d6 + (double)l).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	            bufferbuilder.pos(d5 + 16.0D, d3, d6 + (double)l).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + 16.0D, d4, d6 + (double)l).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + 16.0D, d4, d6 + (double)l).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	        }
	
	        for (int i1 = 0; i1 <= 256; i1 += 2)
	        {
	            double d7 = (double)i1 - d1;
	            bufferbuilder.pos(d5, d7, d6).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	            bufferbuilder.pos(d5, d7, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5, d7, d6 + 16.0D).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + 16.0D, d7, d6 + 16.0D).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + 16.0D, d7, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5, d7, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5, d7, d6).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
	        }
	
	        tessellator.draw();
	        GlStateManager.glLineWidth(2.0F);
	        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
	
	        for (int j1 = 0; j1 <= 16; j1 += 16)
	        {
	            for (int l1 = 0; l1 <= 16; l1 += 16)
	            {
	                bufferbuilder.pos(d5 + (double)j1, d3, d6 + (double)l1).color(0.25F, 0.25F, 1.0F, 0.0F).endVertex();
	                bufferbuilder.pos(d5 + (double)j1, d3, d6 + (double)l1).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
	                bufferbuilder.pos(d5 + (double)j1, d4, d6 + (double)l1).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
	                bufferbuilder.pos(d5 + (double)j1, d4, d6 + (double)l1).color(0.25F, 0.25F, 1.0F, 0.0F).endVertex();
	            }
	        }
	
	        for (int k1 = 0; k1 <= 256; k1 += 16)
	        {
	            double d8 = (double)k1 - d1;
	            bufferbuilder.pos(d5, d8, d6).color(0.25F, 0.25F, 1.0F, 0.0F).endVertex();
	            bufferbuilder.pos(d5, d8, d6).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5, d8, d6 + 16.0D).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + 16.0D, d8, d6 + 16.0D).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5 + 16.0D, d8, d6).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5, d8, d6).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
	            bufferbuilder.pos(d5, d8, d6).color(0.25F, 0.25F, 1.0F, 0.0F).endVertex();
	        }
	
	        tessellator.draw();
	        GlStateManager.glLineWidth(1.0F);
	        GlStateManager.enableBlend();
	        GlStateManager.enableTexture2D();
	        GL11.glPopMatrix();
    	}
    }
}
