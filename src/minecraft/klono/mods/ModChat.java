package klono.mods;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import klono.gui.hud.ScreenPosition;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class ModChat extends ModDraggable {
	public static int chatX = 0;
	public static int chatY = 0;
	public static List<String> chatList = new ArrayList<String>();

	@Override
	public int getWidth() {
		int i2 = chatX;
		float f1 = mc.gameSettings.chatScale;
		int i = 320;
        int j = 40;
        int chatWidth = MathHelper.floor_float(mc.gameSettings.chatWidth * (float)(i - j) + (float)j);
        int l = MathHelper.ceiling_float_int((float)chatWidth / f1);
		return ((i2 + l + 4)-i2)/2;
	}

	@Override
	public int getHeight() {
		return 50;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.TOGGLE_CHAT.getKeyCode() != Keyboard.KEY_HOME) {
			chatX = pos.getAbsoluteX();
			chatY = pos.getAbsoluteY();
			int chatLine = -1;
			if (mc.gameSettings.TOGGLE_CHAT_VIEW.getKeyCode() == Keyboard.KEY_HOME) {
				if (mc.gameSettings.TOGGLE_CHAT_BACK.getKeyCode() == Keyboard.KEY_HOME) {
					for (String s : chatList) {
						chatLine++;
						if (chatLine <= 9) {
							GuiNewChat.drawRect(chatX, chatY+getHeight()-(5*chatLine), chatX+getWidth(), chatY+getHeight()-(5*chatLine+5), new Color(56, 56, 56, 108).getRGB());
						}
					}
				}
		        GlStateManager.pushMatrix();
		        GlStateManager.translate(2.0F, 10.0F, 0.0F);
		        GlStateManager.scale(mc.gameSettings.chatScale, mc.gameSettings.chatScale, 1.0F);
				chatLine = -1;
				for (String s : chatList) {
					chatLine++;
					if (chatLine <= 9) {
						GlStateManager.enableBlend();
						font.drawString(s, (int) (chatX+1+(chatX*0.87)+(pos.getAbsoluteX()/10)), (int) (chatY-(10*chatLine)+71+(chatY*0.87)+(pos.getAbsoluteY()/10)), 16777215);
						GlStateManager.disableAlpha();
		                GlStateManager.disableBlend();
						
					}
				}
		        GlStateManager.popMatrix();
			}
		}
	}

}
