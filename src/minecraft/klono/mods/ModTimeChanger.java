package klono.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import klono.mods.impl.TimeChangerHandler;
import net.klono.lib.TimeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.INetHandler;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ModTimeChanger extends ModDraggable {
	public static TimeType time = TimeType.VANILLA;
	private static String mes = "Vanilla";

	@Override
	public int getWidth() {
		return font.getStringWidth("can change time in Game Settings -> Controls");
	}

	@Override
	public int getHeight() {
		return 48;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (Minecraft.getMinecraft().gameSettings.TIME_TYPE.getKeyCode() == Keyboard.KEY_HOME) {
			time = TimeType.VANILLA;
			mes = "Vanilla";
		} else if (Minecraft.getMinecraft().gameSettings.TIME_TYPE.getKeyCode() == Keyboard.KEY_UNDERLINE) {
			time = TimeType.SUNSET;
			mes = "Sunset";
		} else if (Minecraft.getMinecraft().gameSettings.TIME_TYPE.getKeyCode() == Keyboard.KEY_RMETA) {
			time = TimeType.NIGHT;
			mes = "Night";
		} else if (Minecraft.getMinecraft().gameSettings.TIME_TYPE.getKeyCode() == Keyboard.KEY_AX) {
			time = TimeType.DAY;
			mes = "Day";
		} else {
			Minecraft.getMinecraft().gameSettings.TIME_TYPE.setKeyCode(Keyboard.KEY_HOME);
			time = TimeType.VANILLA;
			mes = "Vanilla";
		}
		if (Minecraft.getMinecraft().theWorld != null) {
			final INetHandler parent = Minecraft.getMinecraft().thePlayer.sendQueue.getNetworkManager().getNetHandler();
			if (!(parent instanceof TimeChangerHandler)) {
				Minecraft.getMinecraft().thePlayer.sendQueue.getNetworkManager().setNetHandler((INetHandler)new TimeChangerHandler((NetHandlerPlayClient)parent));
			}
			if (mc.gameSettings.CHANGE_TIME.isPressed()) {
				if (time == TimeType.VANILLA) {
					time = TimeType.DAY;
					mes = "Day";
					Minecraft.getMinecraft().gameSettings.TIME_TYPE.setKeyCode(Keyboard.KEY_AX);
				} else if (time == TimeType.DAY) {
					time = TimeType.SUNSET;
					mes = "Sunset";
					Minecraft.getMinecraft().gameSettings.TIME_TYPE.setKeyCode(Keyboard.KEY_UNDERLINE);
				} else if (time == TimeType.SUNSET) {
					time = TimeType.NIGHT;
					mes = "Night";
					Minecraft.getMinecraft().gameSettings.TIME_TYPE.setKeyCode(Keyboard.KEY_RMETA);
				} else if (time == TimeType.NIGHT) {
					time = TimeType.VANILLA;
					mes = "Vanilla";
					Minecraft.getMinecraft().gameSettings.TIME_TYPE.setKeyCode(Keyboard.KEY_HOME);
				}
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "Time set to "+mes+"."));
			}
		}
	}

	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "TimeChanger Message", pos.getAbsoluteX() + 2, pos.getAbsoluteY() - 8, -1);
		RainbowWave.drawStringWaveShadow("This is Timechanger setting, but can't show", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 5);
		RainbowWave.drawStringWaveShadow("can change time in Game Settings -> Controls", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 21);
		RainbowWave.drawStringWaveShadow("Now Key: "+Keyboard.getKeyName(mc.gameSettings.CHANGE_TIME.getKeyCode()), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 37);
	}

}
