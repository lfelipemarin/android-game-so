package com.labos.fingit;

public class Reloj implements Runnable {

	public int time;
	public boolean finish;

	public Reloj(int time) {
		this.time = time;
		this.finish = false;
	}

	@Override
	public void run() {
		finish = false;
		System.out.println(Thread.currentThread().getId()
				+ " Inciando reloj para contar " + time + " segundo(s).");
		while (time > -1) {
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
		finish = true;
		System.out.println(Thread.currentThread().getId()
				+ " Reoj llego a cero.");
	}

}
