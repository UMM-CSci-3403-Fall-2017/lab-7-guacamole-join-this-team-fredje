package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EchoServer implements Runnable {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		ExecutorService pool = Executors.newCachedThreadPool();
		while (true) {
			Socket socket = serverSocket.accept();
			pool.execute(new Handler(socket));
		}
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}

class Handler extends Thread implements Runnable {
	private final Socket socket;

	Handler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		InputStream inputStream;
		try {
			inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			int b;
			while ((b = inputStream.read()) != -1) {
				outputStream.write(b);
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
}
