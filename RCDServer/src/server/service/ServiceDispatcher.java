package server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import core.test.socket.TestService;

public class ServiceDispatcher extends Thread {

	private Socket incoming;
	private BufferedReader in;
	private PrintWriter out;
	
	private final static String TEST_SERVICE_CODE_TEST = "SC0";

	public ServiceDispatcher(Socket incoming) {
		this.incoming = incoming;
		try {
			this.out = new PrintWriter(incoming.getOutputStream(),true);
			this.in = new BufferedReader(new InputStreamReader(this.incoming.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Thread getService(String serviceCode) {
		if(serviceCode.equals(ServiceDispatcher.TEST_SERVICE_CODE_TEST)){
			return new TestService(this.incoming);
		}
		return null;
	}

	@Override
	public void run() {
		try {
			String serviceCode = in.readLine();
			Thread service = this.getService(serviceCode);
			if(service != null){
				out.println(ServiceDispatcher.TEST_SERVICE_CODE_TEST);
				service.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
