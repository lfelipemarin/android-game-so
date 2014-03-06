package com.labos.fingit;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Receptor implements Runnable {

	private boolean running;
	final int PUERTO = 6668;
	private Rank rank;
	public boolean recibido;

	@Override
	public void run() {
		setRunning(true);
		recibido = false;
		while (running) {
			try {
				ServerSocket ss = new ServerSocket(PUERTO);
				System.out
						.println(Thread.currentThread().getId()
								+ " Puerto abierto android, esperando paquete con el top.");
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				setRank((Rank) ois.readObject());
				if (getRank() != null) {
					recibido = true;
					System.out.println(Thread.currentThread().getId()
							+ " Recibido " + getRank().toString());
					setRunning(false);
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

	public Rank getRank() {
		return rank;
	}

	private void setRank(Rank rank) {
		this.rank = rank;
	}

}
