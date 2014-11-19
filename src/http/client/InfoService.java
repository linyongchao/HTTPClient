package http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InfoService {
	public static String getInfo(String id) throws IOException {
		URL url = new URL("http://112.64.18.111:8888/info");
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setDoOutput(true);
		urlConn.setDoInput(true);
		urlConn.setRequestMethod("POST");
		OutputStream out = urlConn.getOutputStream();
		out.write(id.getBytes());
		out.flush();
		String info = null;
		if (urlConn.getResponseCode() == 200) {
			InputStream in = urlConn.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			while ((info = reader.readLine()) != null) {
				break;
			}
			reader.close();
			in.close();
			urlConn.disconnect();
		}
		return info;
	}
}