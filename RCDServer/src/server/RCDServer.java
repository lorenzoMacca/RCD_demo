package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import core.test.socket.TestService;

public class RCDServer extends Thread{
	
	private int portNumber=8989;
	private ServerSocket serverSocket;
	
	public RCDServer() {
		super("ServerHandler");
		try {
			this.serverSocket=new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		super.run();
		
		while(true){
			try {
				
				
				Socket incoming = this.serverSocket.accept();
				
				
				new TestService(incoming).start();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		new RCDServer().start();
	}

}
