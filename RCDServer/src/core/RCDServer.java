package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import core.service.TestService;

public class RCDServer extends Thread {
	
	private int portNumber = 8989;
	private ServerSocket serverSocket;
	
	/*
	 * RCDServer constructor
	 * the input is a port number end it is used 
	 * to initialize the Server socket. In case of
	 * failure java will run an IOException.
	 */
	public RCDServer(int portNumber){
		super("Server handler");
		//TODO check the port number before initializing the server socket
		this.portNumber = portNumber;
		try {
			this.serverSocket = new ServerSocket(this.portNumber);
		} catch (IOException e) {
			//TODO manage IOException in case of server socket creation failure
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		while(true){
			try {
				Socket incoming = this.serverSocket.accept();
				new TestService(incoming);
				System.out.println("new income " + incoming.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int port = 8989;
		new RCDServer(port).start();
	}

}
