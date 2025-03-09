package klono.mods;


import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import klono.Client;
import klono.event.EventTarget;
import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ModPing extends ModDraggable {
	
	public static boolean DRPC = false;

	@Override
	public int getWidth() {
		return 50;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT + 2;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_PING.getKeyCode() == Keyboard.KEY_HOME) {
			if (mc.gameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
			}
			RainbowWave.drawStringWaveShadow("Ping: " + Minecraft.whatmyping, pos.getAbsoluteX()+2, pos.getAbsoluteY()+2);
//			font.drawString(Minecraft.whatmyping, pos.getAbsoluteX()+26, pos.getAbsoluteY()+2, Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
		}
		if (mc.gameSettings.DISCORD_RPC.isPressed()) {
			if (DRPC) {
				DRPC = false;
				if (Minecraft.playing != "") {
					Client.getInstance().getDiscordRP().update("", "IP: Hide");
				}
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "Discord RPC Hide IP."));
			} else {
				DRPC = true;
				if (Minecraft.playing != "") {
					Client.getInstance().getDiscordRP().update("", "IP: " + mc.playing);
				}
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "Discord RPC Show IP."));
			}
		}
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "Ping", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
	}


}
