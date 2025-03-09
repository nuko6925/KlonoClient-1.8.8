package klono.aimassist;

import java.util.List;

import klono.event.Event;
import klono.event.EventTarget;
import klono.event.impl.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class AimAssist {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void onTick() {
		final List<Entity> list = (List<Entity>)mc.theWorld.loadedEntityList;
        Entity target = null;
        for (final Entity player : list) {
            if (player.getName() != mc.thePlayer.getName()) {
                if (target == null) {
                    target = (Entity)player;
                }
                else {
                    if (mc.thePlayer.getDistanceToEntity((Entity)player) >= mc.thePlayer.getDistanceToEntity(target)) {
                        continue;
                    }
                    target = (Entity)player;
                }
            }
        }
        if (target != null) {
            final float f = mc.thePlayer.getDistanceToEntity(target);
            if (f < 3.5f && mc.gameSettings.keyBindAttack.isKeyDown()) {
                faceEntity(target);
            }
        }
    }
	
	public static float[] getRotationNeeded(Entity entity) {
		if (entity == null) {
            return null;
        }
        final double diffX = entity.posX - mc.thePlayer.posX;
        final double diffZ = entity.posZ - mc.thePlayer.posZ;
        double diffY;
        if (entity instanceof EntityPlayer) {
            final EntityPlayer entityLivingBase = (EntityPlayer)entity;
            diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
        }
        else if (entity instanceof EntityMob) {
            diffY = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
        }
        else {
            diffY = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
        }
        final double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / 3.141592653589793)) + 90.0f;
        return new float[] { mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw), mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - mc.thePlayer.rotationPitch) };
    }
	
	public static synchronized void faceEntity(Entity entity) {
		final float[] rotations = getRotationNeeded(entity);
		float x = mc.thePlayer.rotationYaw;
		float y = mc.thePlayer.rotationPitch;
		x = Math.abs(x - rotations[0]);
        y = Math.abs(y - rotations[1]);
        if (rotations != null && x < 14.0f && y < 50.0f) {
            mc.thePlayer.rotationYaw = rotations[0];
        }
    }

}
