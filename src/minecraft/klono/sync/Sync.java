package klono.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import klono.Client;
import klono.SessionAPI;
import klono.sync.lib.Socket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class Sync {
	
	public static File options;
	public static File optionsof;
	
	public static void loadConfig() {
		if (SessionAPI.response.contains("Success") || SessionAPI.response.contains("Already logged in")) {
			try {
				URL url;
				
				if (SessionAPI.canConnect.equals("Local")) {
					url = new URL(SessionAPI.API+"lnsync/" + SessionAPI.method + "/" + Client.name);
				} else {
					url = new URL(SessionAPI.subAPI+"lnsync/" + SessionAPI.method + "/" + Client.name);
				}
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				InputStream iStream = con.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
				StringBuilder builder = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				if (!builder.toString().equals("Not found")) {
					if (options.exists()) {
						options.delete();
					}
					options.createNewFile();
					FileWriter writer = new FileWriter(options);
					for (String string : builder.toString().split(", ")) {
						writer.write(string + System.getProperty("line.separator"));
					}
					writer.close();
				}
				
				if (SessionAPI.canConnect.equals("Local")) {
					url = new URL(SessionAPI.API+"losync/" + SessionAPI.method + "/" + Client.name);
				} else {
					url = new URL(SessionAPI.subAPI+"losync/" + SessionAPI.method + "/" + Client.name);
				}
				HttpURLConnection con1 = (HttpURLConnection)url.openConnection();
				con1.setRequestMethod("GET");
				InputStream iStream1 = con1.getInputStream();
				BufferedReader reader1 = new BufferedReader(new InputStreamReader(iStream1));
				StringBuilder builder1 = new StringBuilder();
				String line1;
				while ((line1 = reader1.readLine()) != null) {
					builder1.append(line1);
				}
				if (!builder1.toString().equals("Not found")) {
					boolean restart = false;
					if (!optionsof.exists()) {
						restart = true;
					}
					if (optionsof.exists()) {
						optionsof.delete();
					}
					optionsof.createNewFile();
					FileWriter writer1 = new FileWriter(optionsof);
					for (String string1 : builder1.toString().split(", ")) {
						writer1.write(string1 + System.getProperty("line.separator"));
					}
					writer1.close();
					if (restart) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Please restart the game to Sync!");
						SessionAPI.logoutSession();
						System.exit(0);
					}
					Minecraft mc = Minecraft.getMinecraft();
					mc.gameSettings = new GameSettings(mc, mc.mcDataDir);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveConfig() {
		if (SessionAPI.response.contains("Success") || SessionAPI.response.contains("Already logged in")) {
			try {
				URL url;
				String[] option = readAll(options.getAbsolutePath()).split(System.getProperty("line.separator"));
				String[] optionof = readAll(optionsof.getAbsolutePath()).split(System.getProperty("line.separator"));
				
				if (SessionAPI.canConnect.equals("Local")) {
					url = new URL(SessionAPI.API+"delsync/" + SessionAPI.method + "/" + Client.name);
				} else {
					url = new URL(SessionAPI.subAPI+"delsync/" + SessionAPI.method + "/" + Client.name);
				}
				
				HttpURLConnection con1 = (HttpURLConnection)url.openConnection();
				con1.setRequestMethod("GET");
				InputStream iStream1 = con1.getInputStream();
				BufferedReader reader1 = new BufferedReader(new InputStreamReader(iStream1));
				StringBuilder builder1 = new StringBuilder();
				String line1;
				while ((line1 = reader1.readLine()) != null) {
					builder1.append(line1);
				}
				
				for (String string : option) {
					if (SessionAPI.canConnect.equals("Local")) {
						url = new URL(SessionAPI.API+"insync/" + SessionAPI.method + "/" + Client.name + "/" + URLEncoder.encode(string));
					} else {
						url = new URL(SessionAPI.subAPI+"insync/" + SessionAPI.method + "/" + Client.name + "/" + URLEncoder.encode(string));
					}
					
					HttpURLConnection con = (HttpURLConnection)url.openConnection();
					con.setRequestMethod("GET");
					InputStream iStream = con.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
					StringBuilder builder = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				}
				for (String string : optionof) {
					if (SessionAPI.canConnect.equals("Local")) {
						url = new URL(SessionAPI.API+"iosync/" + SessionAPI.method + "/" + Client.name + "/" + URLEncoder.encode(string));
					} else {
						url = new URL(SessionAPI.subAPI+"iosync/" + SessionAPI.method + "/" + Client.name + "/" + URLEncoder.encode(string));
					}
					
					HttpURLConnection con = (HttpURLConnection)url.openConnection();
					con.setRequestMethod("GET");
					InputStream iStream = con.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
					StringBuilder builder = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static String readAll(final String path) throws IOException {
	    return Files.lines(Paths.get(path), Charset.forName("UTF-8"))
	        .collect(Collectors.joining(System.getProperty("line.separator")));
	}

}
