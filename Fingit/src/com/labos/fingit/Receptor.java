package com.labos.fingit;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;

public class Receptor implements Runnable {

	private boolean running;
	final int PUERTO = 6668;
	private ResultSet rs;

	@Override
	public void run() {
		setRunning(true);
		while (running) {
			try {
				ServerSocket ss = new ServerSocket(PUERTO);
				System.out.println(Thread.currentThread().getId()
						+ " Puerto abierto android, esperando paquete.");
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				rs = (ResultSet) ois.readObject();
				if (rs != null) {
					System.out.println(Thread.currentThread().getId()
							+ " Recibido " + rs.toString());
				}
				is.close();
				s.close();
				ss.close();
			} catch (IOException e) {
				System.out.println(e);
				setRunning(false);
			} catch (ClassNotFoundException e) {
				System.out.println(e);
				setRunning(false);
			}
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
