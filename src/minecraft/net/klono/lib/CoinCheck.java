package net.klono.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;

import javax.net.ssl.HttpsURLConnection;

import net.minecraft.client.Minecraft;

public class CoinCheck extends Thread {
	
	public CoinCheck() {
		
	}
	
	public void run() {
		DecimalFormat form = new DecimalFormat("##,###,### JPY");
		String urlString = "https://coincheck.com/api/rate/btc_jpy";
		try {
			URL url = new URL(urlString);
			HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("GET");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "JISAutoDetect"));
			String sources = new String();
			String str;
			while (null != (str = bufferedReader.readLine())) {
				sources = sources + str;
			}
			bufferedReader.close();
			connection.disconnect();
			sources = sources.replace("\"", "");
			sources = sources.replace("{", "");
			sources = sources.replace(":", "");
			sources = sources.replace("rate", "");
			sources = sources.replace("}", "");
			sources = sources.replace(".0", "");
			sources = sources.replace(".5", "");
			Minecraft.BTC = form.format(Double.parseDouble(sources));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
