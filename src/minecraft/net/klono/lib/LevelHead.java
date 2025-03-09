package net.klono.lib;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.http.HypixelHttpResponse;
import net.hypixel.api.reply.PlayerReply;
import net.hypixel.api.reply.PlayerReply.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import sun.net.www.http.HttpClient;

public class LevelHead {

    public static DecimalFormat format = new DecimalFormat("##.0#");
	
	public static float getLevel(String name) {
		for (EntityPlayer player : Minecraft.getMinecraft().theWorld.playerEntities) {
			if (player.getName() == name) {
				try {
					float health = player.getHealth() + player.getAbsorptionAmount();
					if (health < 0.01 && health != 0) {
						health = 1;
					}
					return Float.parseFloat(format.format(health));
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
				
			}
		}
		return 0;
	}
	
	private static UUID trimUUID(UUID uuid) {
		UUID trim = UUID.fromString(""+uuid);
		return trim;
	}

}
