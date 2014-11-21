package http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResourcesService {
	public static String getRes(String id) throws IOException {
		URL url = new URL("http://112.64.18.111:8888/res");
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setDoOutput(true);
		urlConn.setDoInput(true);
		urlConn.setRequestMethod("POST");
		OutputStream out = urlConn.getOutputStream();
		out.write(id.getBytes());
		out.flush();
		StringBuffer sb = new StringBuffer();
		if (urlConn.getResponseCode() == 200) {
			InputStream in = urlConn.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			String info = null;
			while ((info = reader.readLine()) != null) {
				sb.append(info);
			}
			reader.close();
			in.close();
			urlConn.disconnect();
		}
		return sb.toString();
	}
}