package net.klono.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Session;

public class Wrapper {
	private static Wrapper theWrapper = new Wrapper();
	private Minecraft mc = Minecraft.getMinecraft();
	private Render2D theRender2D = new Render2D();
	
	public static final Wrapper getWrapper(){
		if(theWrapper == null)theWrapper = new Wrapper();
		return theWrapper;
	}
	
	public final Minecraft getMinecraft(){
		return mc;
	}
	
	public final PlayerControllerMP getPlayerController(){
		return mc.playerController;
	}
	
	public final WorldClient getWorld(){
		return mc.theWorld;
	}
	
	public final RenderGlobal getRenderGlobal(){
		return mc.renderGlobal;
	}
	
	public final EntityPlayerSP getPlayer(){
		return mc.thePlayer;
	}
	
	public final EffectRenderer getEffectRenderer(){
		return mc.effectRenderer;
	}
	
	public final FontRenderer getFontRenderer(){
		return mc.fontRendererObj;
	}
	
	public final GuiScreen getCurrentScreen(){
		return mc.currentScreen;
	}
	
	public final EntityRenderer getEntityRenderer(){
		return mc.entityRenderer;
	}
	
	public final GameSettings getGameSettings(){
		return mc.gameSettings;
	}
	
	public final TextureManager getTextureManager(){
		return mc.getTextureManager();
	}
	
	public final Render2D getRender2DUtils(){
		return theRender2D;
	}
	
	public final void setSession(Session s){
		mc.session = s;
	}
}