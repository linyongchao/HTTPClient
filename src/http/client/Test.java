package http.client;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		// 测试并发对HttpServer的影响
		for (int i = 0; i < 20; i++) {
			Runnable run = new Runnable() {
				public void run() {
					String command = "/home/yangk/hello_world";
					String id = null;
					try {
						id = RunService.run(command);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.out.println("client id=" + id);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
					String info = null;
					try {
						info = InfoService.getInfo(id);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					System.out.println(info);
					while (!"DONE".equals(info)) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						try {
							info = InfoService.getInfo(id);
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println(info);
					}
				}
			};
			exec.execute(run);
		}
		exec.shutdown();// 关闭线程池
	}
}