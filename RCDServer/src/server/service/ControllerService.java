package server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import core.data.ErrorCount;
import core.data.RCDData;

public class ControllerService extends Thread {

	private Socket controllerSocket;
	private PrintWriter out;
	private BufferedReader in;
	private ErrorCount errorCount;
	
	public static final int MAX_CONNECTION_ERRORS_BEFORE_QUIT = 3;

	public ControllerService(Socket incoming) {
		this.setName("ControllerService");
		this.controllerSocket = incoming;
		try {
			this.out = new PrintWriter(incoming.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(this.controllerSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.errorCount = new ErrorCount();
	}

	@Override
	public void run() {
		super.run();
		//System.out.println(RCDData.getInstance().getController().toString());
		while (RCDData.getInstance().getController().isActive()) {
			String response = null;
			this.out.println(ServiceDispatcher.SERVICE_CODE_CONTROLLER);
			try {
				this.controllerSocket.setSoTimeout(5000);
				response = this.in.readLine();
			} catch (IOException e1) {
				this.errorCount.addConnectionError("Controller does not reply");
				System.out.println("Controller does not reply... fuck :(");
			}
			if (response != null) {
				if (response.equals(ServiceDispatcher.SERVICE_CODE_CONTROLLER)) {
					this.errorCount.resetCurrentConnectionErrorCount();
					System.out.println("Controller is still alive");
				} else {
					this.errorCount.addConnectionError("Controller is still alive, but the response is not correct");
					System.out.println("Controller is still alive, but the response is not correct!");
				}
			}
			
			if(this.errorCount.getCurrentConnectionError() >= ControllerService.MAX_CONNECTION_ERRORS_BEFORE_QUIT){
				try {
					this.controllerSocket.close();
					RCDData.getInstance().getController().setActive(false);
					System.out.println(this.errorCount.getErrorHistory().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
