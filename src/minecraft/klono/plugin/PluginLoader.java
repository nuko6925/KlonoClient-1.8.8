package klono.plugin;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import klono.gui.hud.IRenderer;
import net.klono.lib.FileManager;
import net.klono.lib.Plugin;
import net.klono.lib.User;
import net.minecraft.client.Minecraft;

public class PluginLoader {

	public static String version = "1.0.0";
	public static List<String> plugins_name = new ArrayList<String>();
	public static List<IRenderer> plugins = new ArrayList<IRenderer>();
	private static Minecraft mc;
	
	public static void load() {
		Plugin plugin;
		try {
			File file = getPluginDir();
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				plugins_name.add(""+files[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

    
    private static File getPluginDir() {
    	File folder = new File(FileManager.getModsDirectory(), "Plugins");
		folder.mkdirs();
		return folder;
    }

}
