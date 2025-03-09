package klono.session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import klono.Client;
import klono.SessionAPI;

public class CheckMultiplayer extends Thread {
	
	public static String raw_ip = "";
	
	public CheckMultiplayer() {
		
	}
	
	public void run() {
		if (raw_ip != null && !raw_ip.isEmpty()) {
			try {
				URL url;
				if (SessionAPI.canConnect.equals("Local")) {
					url = new URL(SessionAPI.API+"server/" + Client.name + "/" + raw_ip);
				} else {
					url = new URL(SessionAPI.subAPI+"server/" + Client.name + "/" + raw_ip);
				}
				System.out.println(url.toString());
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				InputStream iStream = con.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
				StringBuilder builder = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				SessionAPI.Klono_Users.clear();
				for (String names : builder.toString().split(", ")) {
					SessionAPI.Klono_Users.add(names);
				}
				SessionAPI.Log(builder.toString());
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
