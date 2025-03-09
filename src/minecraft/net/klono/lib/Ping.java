package net.klono.lib;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

public class Ping extends Thread {
	private static Minecraft mc = Minecraft.getMinecraft();
	public void run() {
		if (Minecraft.sended == false && Minecraft.lastsend == 0L && Minecraft.playing.contains("hypixel") && mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime() == 1) {
			if (mc.theWorld != null && mc.thePlayer != null && mc.inGameHasFocus && mc.isIntegratedServerRunning()) {
				mc.thePlayer.sendChatMessage("/");
				Minecraft.lastsend = System.currentTimeMillis();
				Minecraft.sended = true;
				Minecraft.www = 0;
			}
		} else {
			if (mc.theWorld != null && mc.thePlayer != null && mc.inGameHasFocus && mc.isIntegratedServerRunning() && mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime() >= 0) {
				Minecraft.sended = false;
				int ping = mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
				EnumChatFormatting color = null;
				if (ping >= 0) {
					if (ping <= 50) {
						color = EnumChatFormatting.DARK_GREEN;
					} else if (ping <= 100) {
						color = EnumChatFormatting.GREEN;
					} else if (ping <= 150) {
						color = EnumChatFormatting.YELLOW;
					} else if (ping <= 200) {
						color = EnumChatFormatting.GOLD;
					} else {
						color = EnumChatFormatting.RED;
					}
					mc.whatmyping = ""+color+ping;
					
				}
			}
			
		}
	}

}
