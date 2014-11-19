package http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RunService {
	public static String run(String command) throws IOException {
		URL url = new URL("http://112.64.18.111:6666/run");
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setDoOutput(true);
		urlConn.setDoInput(true);
		urlConn.setRequestMethod("POST");
		OutputStream out = urlConn.getOutputStream();
		out.write(command.getBytes());
		out.flush();
		String id = null;
		if (urlConn.getResponseCode() == 200) {
			InputStream in = urlConn.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			while ((id = reader.readLine()) != null) {
				break;
			}
			reader.close();
			in.close();
			urlConn.disconnect();
		}
		return id;
	}
}