package klono.mods;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import klono.event.EventTarget;
import klono.event.impl.KeyEvent;
import klono.gui.hud.ScreenPosition;

public class ModFreeLook extends ModDraggable {

	private boolean returnOnRelease = true; //hold down the key = true
	private boolean freeLookToggled = false;

	private float cameraYaw = 0F;
	private float cameraPitch = 0F;

	private int previousPerspective = 0; //prev F5 state

	@EventTarget
	public void keyboardEvent(KeyEvent e) {

		if(e.getKey() == mc.gameSettings.FREELOOK.getKeyCode()) {

			if(Keyboard.getEventKeyState()) {
				freeLookToggled = !freeLookToggled;

				cameraYaw = mc.thePlayer.rotationYaw;
				cameraPitch = mc.thePlayer.rotationPitch;

				if(freeLookToggled) {
					previousPerspective = mc.gameSettings.thirdPersonView;
					mc.gameSettings.thirdPersonView = 1;
				}
				else {
					mc.gameSettings.thirdPersonView = previousPerspective;
				}
			}
			else if(returnOnRelease) {
			freeLookToggled = false;
			mc.gameSettings.thirdPersonView = previousPerspective;
			}
		}

		if(Keyboard.getEventKey() == mc.gameSettings.keyBindTogglePerspective.getKeyCode()) {
			freeLookToggled = false;
		}
	}

	public float getCameraYaw() {
		return freeLookToggled ? cameraYaw : mc.thePlayer.rotationYaw;
	}

	public float getCameraPitch() {
		return freeLookToggled ? cameraPitch : mc.thePlayer.rotationPitch;
	}

	public boolean overrideMouse() {

		if(mc.inGameHasFocus && Display.isActive()) {

			if(!freeLookToggled) {
				return true;
			}

			mc.mouseHelper.mouseXYChange();
			float f1 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float f2 = (f1 * 3 * 1.0F);
			float f3 = (float) mc.mouseHelper.deltaX * f2;
			float f4 = (float) mc.mouseHelper.deltaY * f2;

			cameraYaw += f3 * 0.15F;
			cameraPitch += f4 * 0.15F;

			if(cameraPitch > 90) {
				cameraPitch = 90;
			}

			if(cameraPitch < -90) {
				cameraPitch = -90;
			}
		}
		return false;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public void render(ScreenPosition pos) {
	}

}