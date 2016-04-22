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
		this.incoming = incoming;
		try {
			this.out = new PrintWriter(incoming.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(
					this.incoming.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		super.run();
		System.out.println(RCDData.getInstance().getController().toString());
	}
}
