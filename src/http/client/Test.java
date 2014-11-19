package http.client;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		String command = "/home/yangk/hello_world";
		String id = RunService.run(command);
		System.out.println("client id=" + id);
		String info = InfoService.getInfo(id);
		System.out.println(info);
	}
}