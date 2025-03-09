package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.google.gson.Gson;
import com.mojang.realmsclient.gui.ChatFormatting;

import klono.gui.hud.ScreenPosition;
import net.klono.lib.PinForm;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class ModPin extends ModDraggable {
	public static BlockPos position;
	public static String player;
	public static BlockPos position2;
	public static String player2;
	private static Gson gson = new Gson();
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.PIN.isPressed()) {
			MovingObjectPosition mop = mc.thePlayer.rayTrace(150.0D, 1.0F);
			BlockPos pos1 = mop.getBlockPos();
			if (mc.theWorld.getBlockState(pos1).getBlock() != Blocks.air) {
				mc.thePlayer.sendChatMessage("/pc #kl="+mc.thePlayer.getName()+"="+pos1.getX()+"="+pos1.getY()+"="+pos1.getZ());
			}
//			mc.thePlayer.sendChatMessage(""+position.getX()+", "+position.getY()+", "+position.getZ()+", "+String.format("%.1f", net.klono.lib.BlockPos.distanceSq(position))+" blocks");
//			mc.thePlayer.sendChatMessage("{\"player\":\""+mc.thePlayer.getName()+"\",\"x\":"+position.getX()+",\"y\":"+position.getY()+",\"z\":"+position.getZ()+"}");
			/*
			if (mc.objectMouseOver != null && mc.objectMouseOver.getBlockPos() != null) {
				BlockPos position = this.mc.objectMouseOver.getBlockPos();
				IBlockState iblockstate = this.mc.theWorld.getBlockState(position);
				if (iblockstate.getBlock() != Blocks.air) {
					mc.thePlayer.sendChatMessage(""+position.getX()+", "+position.getY()+", "+position.getZ());
				}
			}*/
		}
		if (position != null) {
			try {
				Vec3 vec3 = mc.thePlayer.getPositionVector();
	            double d6 = (double)((float)CommandBase.parseDouble(vec3.xCoord, ""+position.getX(), true));
	            double d0 = (double)((float)CommandBase.parseDouble(vec3.yCoord, ""+position.getY(), true));
	            double d1 = (double)((float)CommandBase.parseDouble(vec3.zCoord, ""+position.getZ(), true));
	            double d2 = (double)((float)CommandBase.parseDouble("0.0"));
	            double d3 = (double)((float)CommandBase.parseDouble("5"));
	            double d4 = (double)((float)CommandBase.parseDouble("0.0"));
//				mc.theWorld.spawnParticle(EnumParticleTypes.CLOUD, d6, d0, d1, d2, d3, d4, new int[0]);
				mc.theWorld.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, true, d6, d0, d1, d2, d3, d4, new int[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (position2 != null) {
			try {
				Vec3 vec3 = mc.thePlayer.getPositionVector();
	            double d6 = (double)((float)CommandBase.parseDouble(vec3.xCoord, ""+position2.getX(), true));
	            double d0 = (double)((float)CommandBase.parseDouble(vec3.yCoord, ""+position2.getY(), true));
	            double d1 = (double)((float)CommandBase.parseDouble(vec3.zCoord, ""+position2.getZ(), true));
	            double d2 = (double)((float)CommandBase.parseDouble("0.0"));
	            double d3 = (double)((float)CommandBase.parseDouble("5"));
	            double d4 = (double)((float)CommandBase.parseDouble("0.0"));
//				mc.theWorld.spawnParticle(EnumParticleTypes.CLOUD, d6, d0, d1, d2, d3, d4, new int[0]);
				mc.theWorld.spawnParticle(EnumParticleTypes.CLOUD, true, d6, d0, d1, d2, d3, d4, new int[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
/*			Entity entity = mc.getRenderViewEntity();
            double d3 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) 1.0D;
            double d4 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) 1.0D;
            double d5 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) 1.0D;
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
			renderWaypoint(tessellator, worldrenderer, entity, d3, d4, d5);
		}
	}
	
	public void renderWaypoint(Tessellator tessellator, WorldRenderer worldrenderer, Entity entity, double d3, double d4, double d5) {
		float offX = (float) position.getX() - (float) d3 + 0.5F;
        float offY = (float) position.getY() - (float) d4 + 1.0F;
        float offZ = (float) position.getZ() - (float) d5 + 0.5F;
        double distance = Math.sqrt((double) (offX * offX + offY * offY + offZ * offZ));
		RenderManager renderManager = mc.getRenderManager();
		double radius = 12.0D;
		if (font != null) {
			float f = 1.6F;
			float f1 = 0.016666668F * f;
			GlStateManager.pushMatrix();
			float textSize = 1.6F;
			String name = player;
			String distanceText = "";
			boolean showDistance = true;
			float zoomer2 = 1.0F;
			if (distance > radius) {
                double maxDistance = (double) mc.gameSettings.renderDistanceChunks * 16.0D;
                float Z;

                if (distance > maxDistance) {
                    zoomer2 = (float) (maxDistance / radius);
                    Z = (float) (maxDistance / distance);
                    offX *= Z;
                    offY *= Z;
                    offY += entity.getEyeHeight() * (1.0F - Z);
                    offZ *= Z;
                } else {
                    zoomer2 = (float) (distance / radius);
                }/*
    			if (distance > 20.0D) {
    				Z = (float) (offZ == 0.0F ? 0.001D : (double) offZ);
                    float angle = (float) Math.toDegrees(Math.atan((double) (-offX / Z)));

                    if (offZ < 0.0F) {
                        if (offX < 0.0F) {
                            angle += 180.0F;
                        } else {
                            angle -= 180.0F;
                        }
                    }

                    float cameraAngle = MathHelper.wrapAngleTo180_float(entity.rotationYaw);
                    float offset = MathHelper.wrapAngleTo180_float(angle - cameraAngle);

                    showDistance = offset > -20.0F && offset < 20.0F;
    			}
    			if (showDistance) {
                    distanceText = String.format("%.2f", distance) + "m";
                }
			}
			GlStateManager.translate(offX, offY, offZ);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(-f1, -f1, f1);
            GlStateManager.scale(zoomer2, zoomer2, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.scale(2.0F, 2.0F, 2.0F);
            drawIconInWorld(worldrenderer, tessellator, font, name, distanceText, textSize, showDistance);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
		}
	}
	
	public void drawIconInWorld(WorldRenderer worldrenderer, Tessellator tessellator, FontRenderer font, String name, String distanceText, float textSize, boolean showDistance) {
		GlStateManager.scale(1.0F, 1.0F, 1.0F);
		float t;
		int showingName = new Color(255, 85, 255, 255).hashCode();
		
		t = (float) (showingName >> 16 & 255) / 255.0F;
		float i1 = (float) (showingName >> 8 & 255) / 255.0F;
        float j1 = (float) (showingName & 255) / 255.0F;
        int s = font.getStringWidth("X") / 2;

        GlStateManager.disableTexture2D();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        GlStateManager.color(t, i1, j1, 133.3F * ((float) 255.0F / 100.0F) / 255.0F);
        worldrenderer.pos(-5.0D, -9.0D, 0.0D).endVertex();
        worldrenderer.pos(-5.0D, 0.0D, 0.0D).endVertex();
        worldrenderer.pos(4.0D, 0.0D, 0.0D).endVertex();
        worldrenderer.pos(4.0D, -9.0D, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        font.drawString("X", -s, -8, 553648127);
        font.drawString("X", -s, -8, -1);
        
        if (mc.isUnicode()) {
        	textSize  *= 1.5F;
        }
        
        boolean showingName2 = name.length() > 0;

        GlStateManager.translate(0.0F, 1.0F, 0.0F);
        GlStateManager.scale(textSize / 2.0F, textSize / 2.0F, 1.0F);
        int t1;

        if (distanceText.length() > 0) {
            t1 = font.getStringWidth(distanceText) / 2;
            GlStateManager.disableTexture2D();
            GlStateManager.color(0.0F, 0.0F, 0.0F, 0.27450982F);
            worldrenderer.begin(7, DefaultVertexFormats.POSITION);
            worldrenderer.pos((double) (-t1) - 1.0D, (double) (showingName2 ? 10 : 0), 0.0D).endVertex();
            worldrenderer.pos((double) (-t1) - 1.0D, 9.0D + (double) (showingName2 ? 10 : 0), 0.0D).endVertex();
            worldrenderer.pos((double) t1, 9.0D + (double) (showingName2 ? 10 : 0), 0.0D).endVertex();
            worldrenderer.pos((double) t1, (double) (showingName2 ? 10 : 0), 0.0D).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
            font.drawString(distanceText, -t1, 1 + (showingName2 ? 10 : 0), 553648127);
            font.drawString(distanceText, -t1, 1 + (showingName2 ? 10 : 0), -1);
        }

        if (showingName2) {
            t1 = font.getStringWidth(name) / 2;
            font.drawString(name, -t1, 1, 553648127);
            font.drawString(name, -t1, 1, -1);
        }
//        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "a"));
	}*/
}
