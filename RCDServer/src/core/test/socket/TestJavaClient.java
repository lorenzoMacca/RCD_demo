package core.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestJavaClient extends Thread{

	private String ip = "127.0.0.1";
	private int port = 8989;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public TestJavaClient() {
		try {
			this.socket = new Socket(ip, port);
			this.out = new PrintWriter(socket.getOutputStream(),true);
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void run() {
		super.run();
		try {

			out.println("ciao bello");
			System.out.println(in.readLine());


		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		new TestJavaClient().start();
	}


}
