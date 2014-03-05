package com.labos.fingit;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientScore implements Runnable {

	private final int PUERTO = 6667;
	private final String HOST="192.168.1.131";
	private Score score;

	public ClientScore(Score score) {
		this.score = score;
	}

	@Override
	public void run() {
		try {
			InetAddress host = InetAddress.getByName(HOST);
			Socket socket = new Socket(host, PUERTO);
			OutputStream outStream = socket.getOutputStream();
			ObjectOutputStream obOutStream = new ObjectOutputStream(outStream);
			obOutStream.writeObject(score);
			obOutStream.close();
			outStream.close();
			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
