package klono.mods;

import klono.gui.hud.HUDManager;
import klono.mods.debug.ModChunk;
import klono.mods.debug.ModVram;
import klono.mods.impl.ModArmorStatus;

public class ModInstances {

	private static ModArmorStatus modArmorStatus;

	private static ModFPS modFPS;
	
	private static ModPing modPing;
	
	private static ModKeystroke modKeystroke;
	
	private static ModIP modIP;
	
	private static ModFreeLook modFreeLook;
	
	private static ModTime modTime;
	
	private static ModXYZ modXYZ;
	
	private static ModCPS modCPS;
	
	private static ModEffect modEffect;
	
	private static ModReachDisplay modReachDisplay;
	
	private static ModScore modScore;
	
	private static ModChat modChat;
	
//	private static ModWaila modWaila;
	
	private static ModFullbright modFullbright;
	
	private static ModTimeChanger modTimeChanger;
	
	private static ModMemory modMemory;
	
	private static ModToggleSprint modToggleSprint;
	
	private static ModBitcoin modBitcoin;
	
	private static ModCalc modCalc;
	
	private static ModNotepad modNotepad;
	
	private static ModBlockOverlay modBlockOverlay;
	
	private static ModPin modPin;
	
	private static ModTPS modTPS;
	
	private static ModCompass modCompass;
	
	private static ModMurder modMurder;
	
//	private static ModChunk modChunk;
	
//	private static ModCPUUsage modCPUUsage;
	
	private static ModVram modVram;

	public static void register(HUDManager api) {
		modArmorStatus = new ModArmorStatus();
		api.register(modArmorStatus);

		modFPS = new ModFPS();
		api.register(modFPS);
		
		modPing = new ModPing();
		api.register(modPing);
		
		modKeystroke = new ModKeystroke();
		api.register(modKeystroke);
		
		modIP = new ModIP();
		api.register(modIP);
		
		modFreeLook = new ModFreeLook();
		api.register(modFreeLook);
		
		modTime = new ModTime();
		api.register(modTime);
		
		modXYZ = new ModXYZ();
		api.register(modXYZ);
		
		modCPS = new ModCPS();
		api.register(modCPS);
		
		modEffect = new ModEffect();
		api.register(modEffect);
		
		modReachDisplay = new ModReachDisplay();
		api.register(modReachDisplay);
		
		modScore = new ModScore();
		api.register(modScore);
		
		modChat = new ModChat();
		api.register(modChat);
		
//		modWaila = new ModWaila();
//		api.register(modWaila);
		
		modFullbright = new ModFullbright();
		api.register(modFullbright);
		
		modTimeChanger = new ModTimeChanger();
		api.register(modTimeChanger);
		
		modMemory = new ModMemory();
		api.register(modMemory);
		
		modToggleSprint = new ModToggleSprint();
		api.register(modToggleSprint);
		
		modBitcoin = new ModBitcoin();
		api.register(modBitcoin);
		
		modCalc = new ModCalc();
		api.register(modCalc);
		
		modNotepad = new ModNotepad();
		api.register(modNotepad);
		
		modBlockOverlay = new ModBlockOverlay();
		api.register(modBlockOverlay);
		
		modPin = new ModPin();
		api.register(modPin);
		
		modTPS = new ModTPS();
		api.register(modTPS);
		
		modCompass = new ModCompass();
		api.register(modCompass);
		
		modMurder = new ModMurder();
		api.register(modMurder);
		/*
		modCPUUsage = new ModCPUUsage();
		api.register(modCPUUsage);
		
		modChunk = new ModChunk();
		api.register(modChunk);*/
		
		modVram = new ModVram();
		api.register(modVram);
		
	}

	public static ModFPS getModFPS() {
		return modFPS;
	}
	
	public static ModPing getModPing() {
		return modPing;
	}
	
	public static ModKeystroke getModKeystroke() {
		return modKeystroke;
	}
	
	public static ModIP getIP() {
		return modIP;
	}
	
	public static ModFreeLook getFreeLook() {
		return modFreeLook;
	}
	
	public static ModTime getModTime() {
		return modTime;
	}
	
	public static ModXYZ getModXYZ() {
		return modXYZ;
	}
	
	public static ModCPS getModCPS() {
		return modCPS;
	}
	
	public static ModEffect getEffect() {
		return modEffect;
	}
	
	public static ModReachDisplay getModReachDisplay() {
		return modReachDisplay;
	}
	
	public static ModScore getModScore() {
		return modScore;
	}
	
	public static ModChat getModChat() {
		return modChat;
	}
	
//	public static ModWaila getModWaila() {
//		return modWaila;
//	}
	
	public static ModFullbright getFullbright() {
		return modFullbright;
	}
	
	public static ModTimeChanger getTimeChanger() {
		return modTimeChanger;
	}
	
	public static ModMemory getModMemory() {
		return modMemory;
	}
	
	public static ModToggleSprint getToggleSprint() {
		return modToggleSprint;
	}
	
	public static ModBitcoin getBitcoin() {
		return modBitcoin;
	}
	
	public static ModCalc getCalc() {
		return modCalc;
	}
	
	public static ModNotepad getNotepad() {
		return modNotepad;
	}
	
	public static ModBlockOverlay getBlockOverlay() {
		return modBlockOverlay;
	}
	
	public static ModPin getPin() {
		return modPin;
	}
	
	public static ModTPS geTps() {
		return modTPS;
	}
	
	public static ModCompass getCompass() {
		return modCompass;
	}
	
	public static ModMurder getMurder() {
		return modMurder;
	}
	/*
	public static ModCPUUsage getCpuUsage() {
		return modCPUUsage;
	}
	
	public static ModChunk getChunk() {
		return modChunk;
	}*/
	
	public static ModVram getVram() {
		return modVram;
	}
}
