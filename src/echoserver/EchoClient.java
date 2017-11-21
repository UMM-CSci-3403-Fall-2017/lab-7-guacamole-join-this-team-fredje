package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoClient{
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();
		int readByte;
		int socketByte;
		//Creates a new thread to send data to the server
		Thread thread1 = new Thread();
		try{
			//sending info to server
			while ((readByte = System.in.read()) != -1) {
				socketOutputStream.write(readByte);
			}
			socketOutputStream.flush();
			socket.shutdownOutput();
		}
		catch(IOException e){
			System.out.println(e);
		}
		//Creates a second thread for reading the data from the server
		thread1.start();
		Thread thread2 = new Thread();
		try{
			//reading info from the server
			while ((socketByte = socketInputStream.read()) != -1) {
			
				System.out.write(socketByte);
			}
			System.out.flush();
			socket.close();
		}catch(IOException e){
			System.out.println(e);
		}
		thread2.start();
	}



}