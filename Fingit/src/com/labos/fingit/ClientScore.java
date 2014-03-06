package com.labos.fingit;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientScore implements Runnable {

	private final int PUERTO = 6667;
	private final String HOST="192.168.1.131";
	private Score score;
	public boolean enviado;

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
			System.out.println(Thread.currentThread().getId()
					+ " Paquete enviado.");
			obOutStream.close();
			outStream.close();
			socket.close();
			enviado = true;
		} catch (Exception e) {		
			enviado = false;
			System.out.println(Thread.currentThread().getId()
					+ " Error enviando el score.");
		}
	}
}
