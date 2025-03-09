package net.klono.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransApi {
	public static String alg;
	public TransApi() {
		alg = "ENJA";
	}
	
	public TransApi(String str) {
		if (str == "JAEN" || str == "ENJA") {
			alg = str;
		} else {
			alg = "ENJA";
		}
	}
	
	public static String getTranslate(String str) {
		String result = null;
		try {
			URL excite = new URL("http://www.excite.co.jp/world/english/");
			URLConnection conn = excite.openConnection();
			conn.setDoOutput(true);
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			writer.print("before={"+str+"}&wb_lp={"+alg+"}");
			writer.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String regex = "<textarea [^>].*after.*>(.*)";
			Pattern pattern = Pattern.compile(regex);
			while ((line = reader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				if (matcher.matches()) {
					result = matcher.group(1);
				}
			}
			reader.close();
		} catch (Exception e) {
			result = str;
			e.printStackTrace();
		}
		return result;
	}

}
