package klono.mods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import klono.Client;
import klono.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {

	private boolean isEnabled = true;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Client client;

	public Mod() {
		this.mc = Minecraft.getMinecraft();
		this.font = this.mc.fontRendererObj;
		this.client = Client.getInstance();

		setEnabled(isEnabled);
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;

		if(isEnabled) {
			EventManager.register(this);
		}
		else {
			EventManager.unregister(this);
		}
	}

	public boolean isEnabled() {
		return isEnabled;
	}

}
