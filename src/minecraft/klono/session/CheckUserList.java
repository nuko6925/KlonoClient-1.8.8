package klono.session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import klono.SessionAPI;

public class CheckUserList extends Thread {
	
	public CheckUserList() {
		
	}
	
	public void run() {
		try {
			URL url;
			if (SessionAPI.canConnect.equals("Local")) {
				url = new URL(SessionAPI.API+"status/player_list");
			} else {
				url = new URL(SessionAPI.subAPI+"status/player_list");
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
