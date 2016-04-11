package core.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestService extends Thread{
	
	private Socket incoming;
	private PrintWriter out;
	private BufferedReader in;
	
	
	public TestService(Socket incoming) {
		this.incoming=incoming;
		try {
			this.out = new PrintWriter(incoming.getOutputStream(),true);
			this.in = new BufferedReader(new InputStreamReader(this.incoming.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		try {
			
			
			out.println(in.readLine());
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	


}
