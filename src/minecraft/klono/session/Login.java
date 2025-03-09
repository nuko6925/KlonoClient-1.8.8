package klono.session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import klono.Client;
import klono.ClientInfo;
import klono.SessionAPI;

public class Login extends Thread {
	
	public Login() {
		
	}
	
	public void run() {
		try {
			URL url = new URL(SessionAPI.API+"login/" + SessionAPI.method + "/"+Client.name + "/v/" + ClientInfo.Client_Version);
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
			SessionAPI.Log(builder.toString());
			SessionAPI.response = builder.toString();
			SessionAPI.canConnect = "Local";
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				URL url = new URL(SessionAPI.subAPI+"login/" + SessionAPI.method + "/"+Client.name + "/v/" + ClientInfo.Client_Version);
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
				SessionAPI.Log(builder.toString());
				SessionAPI.response = builder.toString();
				SessionAPI.canConnect = "Global";
				reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
