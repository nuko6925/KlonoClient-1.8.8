package net.minecraft.client.renderer;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ScreenShotUploader extends Thread {
	BufferedImage bufferedImage;
	String filename;
	public ScreenShotUploader(BufferedImage image, String name) {
		bufferedImage = image;
		filename = name;
	}
	public void run() {
		OutputStreamWriter wr = null;
        BufferedReader in = null;
        ByteArrayOutputStream baos = null;
        HttpURLConnection conn = null;
        JsonObject lastUploadData = null;
        GuiIngame ingameGUI = null;
        IChatComponent ichatcomponent = null;
        try {
        	ichatcomponent = new ChatComponentText("Uploading " + filename + " ...");
        	Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(ichatcomponent);
            final URL url = new URL("https://api.imgur.com/3/upload.json");
            baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            final byte[] imageInByte = baos.toByteArray();
            final String encoded = Base64.encodeBase64String(imageInByte);
            String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(encoded, "UTF-8");
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Client-ID 70df5d8909b8da8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            lastUploadData = new JsonParser().parse((Reader)in).getAsJsonObject();
        } catch (IOException e) {
        	LogManager.getLogger().warn("Couldn\'t upload screenshot", (Throwable)e);
        } catch (JsonParseException e2) {
        	LogManager.getLogger().warn("Couldn\'t parse screenshot", (Throwable)e2);
		}
        finally {
        	IOUtils.close(conn);
            IOUtils.closeQuietly(wr);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(baos);
		}
        if (lastUploadData != null) {
        	final JsonObject dataJsonObject = lastUploadData.get("data").getAsJsonObject();
        	String result = ("https://imgur.com/" + dataJsonObject.get("id").getAsString());
        	ichatcomponent = new ChatComponentText("Successfully uploaded.");
        	ichatcomponent.getChatStyle().setColor(EnumChatFormatting.GREEN);
        	ichatcomponent.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, result));
        	IChatComponent icp = new ChatComponentText(EnumChatFormatting.GREEN + "Click to open " + result);
        	ichatcomponent.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, icp));
        	ichatcomponent.getChatStyle().setUnderlined(Boolean.valueOf(true));
        	Toolkit toolkit = Toolkit.getDefaultToolkit();
        	Clipboard cb = toolkit.getSystemClipboard();
        	StringSelection str = new StringSelection(result);
        	cb.setContents(str, null);
        	Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(ichatcomponent);
        }
		
	}

}
