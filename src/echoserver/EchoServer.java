package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer implements Runnable{
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		ExecutorService pool = Executors.newCachedThreadPool();
		while (true) {
			Socket socket = null;
			pool.execute(new Handler(socket = serverSocket.accept()));
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			int b;
			while ((b = inputStream.read()) != -1) {
				outputStream.write(b);
			}
		}
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}

class Handler implements Runnable {
	private final Socket socket;
	Handler(Socket socket) { this.socket = socket; }
	public void run() {
		// read and service request on socket
	}
}