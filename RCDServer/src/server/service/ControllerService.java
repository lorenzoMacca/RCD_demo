package server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import core.data.RCDData;

public class ControllerService extends Thread {

	private Socket incoming;
	private PrintWriter out;
	private BufferedReader in;

	public ControllerService(Socket incoming) {
		this.setName("ControllerService");
		this.incoming = incoming;
		try {
			this.out = new PrintWriter(incoming.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(this.incoming.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		super.run();
		System.out.println(RCDData.getInstance().getController().toString());
		while (true) {
			String response = null;
			this.out.println(ServiceDispatcher.SERVICE_CODE_CONTROLLER);
			try {
				this.incoming.setSoTimeout(10000);
				response = this.in.readLine();
			} catch (IOException e1) {
				System.out.println("Controller does not reply... fuck :(");
			}
			if (response != null) {
				if (response.equals(ServiceDispatcher.SERVICE_CODE_CONTROLLER)) {
					System.out.println("Controller is still alive");
				} else {
					System.out.println("Controller is still alive, but the response is not correct!");
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
