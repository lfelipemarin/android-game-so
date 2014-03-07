package com.labos.fingit;

public class Reloj implements Runnable {

	public int time;

	public Reloj(int time) {
		this.time = time;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getId()
				+ " Inciando reloj para contar " + time + " segundo(s).");
		while (time > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getId()
						+ " Error durmiendo en el reloj. " + e);
			}
			time--;
			// System.out.println(Thread.currentThread().getId()
			// + " Reloj, queda(n) " + time + " segundo(s).");
		}
		System.out.println(Thread.currentThread().getId()
				+ " Reoj llego a cero.");
	}

}
