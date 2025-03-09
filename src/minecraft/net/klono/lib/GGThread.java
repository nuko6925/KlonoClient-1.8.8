package net.klono.lib;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;

public class GGThread extends Thread {
	private Minecraft mc = Minecraft.getMinecraft();
	public GGThread() {
		
	}
	
	public void run() {
		if (mc.gameSettings.TOGGLE_AUTO_GG.getKeyCode() == Keyboard.KEY_HOME) {
			try {
				Thread.sleep(1000L);
				mc.thePlayer.sendChatMessage("/achat gg");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
