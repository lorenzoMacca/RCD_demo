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

			this.testProtocol1();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void testProtocol1() throws IOException{
		System.out.println("#######################################");
		System.out.println("#TEST SOCKET JAVA CLIENT");
		System.out.println("#######################################\n");
		
		String message = "####!!01ab CD23!!####";
		out.println(message);
		
		String recv_data = in.readLine();
		
		if(recv_data.equals(message)){
			System.out.println("[OK] - test protocol 1 ");
		}else{
			System.out.println("[NOK] - test protocol 1");
		}
		
		System.out.println("\n#######################################");
		System.out.println("#END TEST SOCKET JAVA CLIENT");
		System.out.println("#######################################");
	}


	public static void main(String[] args) {
		new TestJavaClient().start();
	}


}
