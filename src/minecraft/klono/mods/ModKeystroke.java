package klono.mods;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.mojang.realmsclient.gui.ChatFormatting;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ModKeystroke extends ModDraggable {
	private List<Long> left = new ArrayList<Long>();
	private List<Long> right = new ArrayList<Long>();
	private boolean wasleftPressed;
	private boolean wasrightPressed;
	
	public static enum KeystrokesMode {
		
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
		WASD_JUMP(Key.W, Key.A, Key.S, Key.D, new Key(EnumChatFormatting.STRIKETHROUGH + "         ", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 18)),
		WASD_JUMP_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key(EnumChatFormatting.STRIKETHROUGH + "         ", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 18)),
		WASD_MULTI(Key.W, Key.A, Key.S, Key.D, new Key(EnumChatFormatting.STRIKETHROUGH + "         ", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 18, 18), new Key("Sneak", Minecraft.getMinecraft().gameSettings.keyBindSneak, 1, 41, 28, 18), new Key("Sprint", Minecraft.getMinecraft().gameSettings.keyBindSprint, 31, 41, 28, 18)),
		WASD_MULTI_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key(EnumChatFormatting.STRIKETHROUGH + "         ", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 81, 58, 18), new Key("Sneak", Minecraft.getMinecraft().gameSettings.keyBindSneak, 1, 61, 28, 18), new Key("Sprint", Minecraft.getMinecraft().gameSettings.keyBindSprint, 31, 61, 28, 18)),
		;
		
		private final Key[] keys;
		private int width = 0;
		private int height = 0;
		
		private KeystrokesMode(Key... keysIn) {
			this.keys = keysIn;
			
			for(Key key : keys) {
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public Key[] getKeys() {
			return keys;
		}
		
	}
	
	private static class Key {
		private static final Key W = new Key("W", GameSettings.keyBindForward, 21, 1, 18, 18);
		private static final Key A = new Key("A", GameSettings.keyBindLeft, 1, 21, 18, 18);
		private static final Key S = new Key("S", GameSettings.keyBindBack, 21, 21, 18, 18);
		private static final Key D = new Key("D", GameSettings.keyBindRight, 41, 21, 18, 18);
		
		private static final Key LMB = new Key("LMB", GameSettings.keyBindAttack, 1, 41, 28, 18);
		private static final Key RMB = new Key("RMB", GameSettings.keyBindUseItem, 31, 41, 28, 18);
				
		private final String name;
		private final KeyBinding keyBind;
		private final int x;
		private final int y;
		private final int width;
		private final int height;
		
		public Key(String name, KeyBinding keyBind, int x, int y, int width, int height) {
			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			
		}
		
		public boolean isKeyDown() {
			return keyBind.isKeyDown();
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public String getName() {
			return name;
		}
	}
	
	private KeystrokesMode mode = KeystrokesMode.WASD_JUMP_MOUSE;
	
	public void setMode(KeystrokesMode mode) {
		this.mode = mode;
	}

	@Override
	public int getWidth() {
		return mode.getWidth();
	}

	@Override
	public int getHeight() {
		return mode.getHeight();
	}

	@Override
	public void render(ScreenPosition pos) {
		if (GameSettings.CHANGE_HUD_COLOR.isPressed()) {
			if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_CIRCUMFLEX) {
				GameSettings.COLOR.setKeyCode(Keyboard.KEY_HOME);
				mc.thePlayer.addChatMessage(new ChatComponentText("HUD Color changed to Chroma"));
			}
			else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_BACKSLASH) {
				GameSettings.COLOR.setKeyCode(Keyboard.KEY_CIRCUMFLEX);
				mc.thePlayer.addChatMessage(new ChatComponentText("HUD Color changed to "+ChatFormatting.WHITE+"White"));
			}
			else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_GRAVE) {
				GameSettings.COLOR.setKeyCode(Keyboard.KEY_BACKSLASH);
				mc.thePlayer.addChatMessage(new ChatComponentText("HUD Color changed to "+ChatFormatting.AQUA+"Blue"));
			}
			else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_RMETA) {
				GameSettings.COLOR.setKeyCode(Keyboard.KEY_GRAVE);
				mc.thePlayer.addChatMessage(new ChatComponentText("HUD Color changed to "+ChatFormatting.GREEN+"Green"));
			}
			else if (GameSettings.COLOR.getKeyCode() == Keyboard.KEY_HOME) {
				GameSettings.COLOR.setKeyCode(Keyboard.KEY_RMETA);
				mc.thePlayer.addChatMessage(new ChatComponentText("HUD Color changed to "+ChatFormatting.RED+"Red"));
			}
		}
		
		final boolean pressed = Mouse.isButtonDown(0);
		
		if (pressed != this.wasleftPressed) {
			this.wasleftPressed = pressed;
			if (pressed && mc.currentScreen == null) {
				this.left.add(System.currentTimeMillis());
			}
		}
		
		final boolean pressed2 = Mouse.isButtonDown(1);
		
		if (pressed2 != this.wasrightPressed) {
			this.wasrightPressed = pressed2;
			if (pressed2 && mc.currentScreen == null) {
				this.right.add(System.currentTimeMillis());
			}
		}
		if (GameSettings.TOGGLE_KEYSTROKE.getKeyCode() == Keyboard.KEY_HOME) {
		
			GL11.glPushMatrix();
			
			boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);
			
			GL11.glEnable(GL11.GL_BLEND);
			
			
			for(Key key : mode.getKeys()) {
				
				int textWidth = font.getStringWidth(key.getName());
				if (GameSettings.TOGGLE_HUD_BG.getKeyCode() == Keyboard.KEY_HOME) {
				Gui.drawRect(
						pos.getAbsoluteX() + key.getX(),
						pos.getAbsoluteY() + key.getY(),
						pos.getAbsoluteX() + key.getX() + key.getWidth(),
						pos.getAbsoluteY() + key.getY() + key.getHeight(),
						key.isKeyDown() ? Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.3F, 0.8F) : new Color(0, 0, 0, 102).getRGB()
						);
				}
				if (key.getName() == "LMB") {
					font.drawString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 8, key.isKeyDown() ? Color.BLACK.getRGB() : Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
					font.drawString("  " + getCPS() + " C", pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2 - 4, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 1, key.isKeyDown() ? Color.BLACK.getRGB() : Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
				} else if (key.getName() == "RMB") {
					font.drawString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 8, key.isKeyDown() ? Color.BLACK.getRGB() : Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
					font.drawString("  " + getCPS2() + " C", pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2 - 4, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 1, key.isKeyDown() ? Color.BLACK.getRGB() : Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F));
				} else {
					font.drawString(
						key.getName(),
						pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2,
						pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4,
						key.isKeyDown() ? Color.BLACK.getRGB() : Color.HSBtoRGB((float)(System.currentTimeMillis() % 10000L) / 10000L, 0.8F, 0.8F)
					);
				}
						
			}
			
			if(blend) {
				GL11.glDisable(GL11.GL_BLEND);
			}
			
			GL11.glPopMatrix();
		}
		
	}
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() - 10, new Color(255, 255, 255, 102).getRGB());
		font.drawString(EnumChatFormatting.DARK_GRAY + "Keystrokes", pos.getAbsoluteX(), pos.getAbsoluteY() - 8, -1);
	}
	
	private int getCPS() {
		final long time = System.currentTimeMillis();
		this.left.removeIf(aLong -> aLong + 1000 < time);
		return this.left.size();
	}
	
	private int getCPS2() {
		final long time = System.currentTimeMillis();
		this.right.removeIf(aLong -> aLong + 1000 < time);
		return this.right.size();
	}

}
