package klono;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import klono.session.CheckMultiplayer;
import klono.session.CheckUserList;
import klono.session.Login;
import klono.session.TestConnection;

public class SessionAPI {
	
	public static final String API = "http://127.0.0.1:33333/";
	public static final String subAPI = "http://casteria.net:33333/";
	public static List<String> Klono_Users = new ArrayList<String>();
	public static String response = "";
	public static String canConnect = "";
	public static String klono_mark = "Åòa\u2714Åòr";
	public static String method = "uuid";
	
	public static void loginSession() {
		Login login = new Login();
		login.start();
	}
	
	public static void testSession() {
		TestConnection con = new TestConnection();
		con.start();
	}
	
	public static void logoutSession() {
		try {
			URL url;
			if (canConnect.equals("Local")) {
				url = new URL(API+"logout/" + method + "/"+Client.name);
			} else {
				url = new URL(subAPI+"logout/" + method + "/"+Client.name);
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
			Log(builder.toString());
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void checkUsers() {
		CheckUserList userList = new CheckUserList();
		userList.start();
	}
	
	public static void checknetwork() {
		CheckMultiplayer multiplayer = new CheckMultiplayer();
		multiplayer.start();
	}
	
	public static void Log(String arg) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("["+timestamp+"] "+arg);
	}

}
