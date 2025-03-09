package net.klono.lib;

import net.minecraft.client.Minecraft;

public class BlockPos {
	private static Minecraft mc = Minecraft.getMinecraft();
	public static String distanceSq(net.minecraft.util.BlockPos pos) {
		double deltaX = mc.thePlayer.posX - pos.getX();
		double deltaY = mc.thePlayer.posY - pos.getY();
		double deltaZ = mc.thePlayer.posZ - pos.getZ();
		return String.format("%.2f", Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ)));
	}
	
	public static double getDistanceSq(net.minecraft.util.BlockPos pos) {
		double deltaX = mc.thePlayer.posX - pos.getX();
		double deltaY = mc.thePlayer.posY - pos.getY();
		double deltaZ = mc.thePlayer.posZ - pos.getZ();
		return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
	}
	
	public static net.minecraft.util.BlockPos getBlockPos(int x, int y, int z) {
		return new net.minecraft.util.BlockPos(x, y, z);
	}

}
