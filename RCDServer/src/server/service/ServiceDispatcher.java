package server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import core.data.RCDData;
import core.test.socket.TestService;

public class ServiceDispatcher extends Thread {

	private Socket incoming;
	private BufferedReader in;
	private PrintWriter out;
	
	private final static String TEST_SERVICE_CODE_TEST  = "SC0";
	public  final static String SERVICE_CODE_CONTROLLER = "SC1";
	public  final static String SERVICE_CODE_AUTO       = "SC2";

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
		}else if(serviceCode.equals(ServiceDispatcher.SERVICE_CODE_CONTROLLER)){
			this.out.println(serviceCode);
			RCDData.getInstance().getController().initializeController(this.incoming.getInetAddress().getHostAddress(), this.incoming.getPort(), true);
			return new ControllerService(incoming);
		}else if(serviceCode.equals(ServiceDispatcher.SERVICE_CODE_AUTO)){
			this.out.println(serviceCode);
		}
		return null;
	}

	@Override
	public void run() {
		try {
			String serviceCode = in.readLine();
			Thread service = this.getService(serviceCode);
			if(service != null){
				System.out.println(serviceCode);
				service.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
