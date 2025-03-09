package net.klono.lib;

import java.util.Base64;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.mojang.realmsclient.gui.ChatFormatting;

import io.netty.buffer.Unpooled;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChatComponentText;

public class KlonoHash extends Thread {
	private static String hashed;
	public static String ans;
	
	public KlonoHash(String str) {
		hashed = str;
	}
	
	public void run() {
		try {
			String str = hashed.replace("kl-Hash - New Job: ", "");
			String decoded = new String(Base64.getDecoder().decode(str));
			String[] split = decoded.split(" ");
			ScriptEngineManager mgr = new ScriptEngineManager();
	        ScriptEngine engine = mgr.getEngineByName("JavaScript");
	        Double result = (Double)engine.eval("" + split[0] + split[1] + split[2]);
	        Double res = (result + 8235);
	        ans = res.toString();
	        String encoded = new String(Base64.getEncoder().encodeToString(ans.getBytes()));
	        Minecraft.getMinecraft().thePlayer.sendChatMessage("/kl-Hash "+encoded);
		} catch (Exception e) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + e.toString()));
		}
	}

}
