package com.labos.fingit;

import android.content.Intent;
import android.graphics.Canvas;

public class GameLoop extends Thread {

	private Gameboard gb;
	private boolean running = false;
	static final long FPS = 32;

	public GameLoop(Gameboard board) {
		this.gb = board;
	}

	public void setRunning(boolean run) {
		running = run;
	}

	@Override
	public void run() {
		// long ticksPS = 1000 / FPS;
		// long tiempoInicio;
		// long tiempoDormir;
		while (running) {
			Canvas c = null;
			// tiempoInicio = System.currentTimeMillis();
			try {
				c = gb.getHolder().lockCanvas();
				synchronized (gb.getHolder()) {
					gb.onDraw(c);
				}
			} finally {
				if (c != null) {
					gb.getHolder().unlockCanvasAndPost(c);
				}
			}
			// tiempoDormir = ticksPS - (System.currentTimeMillis() -
			// tiempoInicio);

			try {
				sleep(10);

			} catch (Exception e) {
				System.out.println(e);
			}
			if (gb.gameOver()) {
				gb.mplOpen.release();
				System.out.println(Thread.currentThread().getId()
						+ " GAME OVER.");
				gb.finalSound();
				System.out.println(Thread.currentThread().getId()
						+ " Lanzando Ranking.");
				Intent intent = new Intent(this.gb.getContext(),
						RankActivity.class);
				gb.score = gb.score + gb.spritesMario.size() * gb.reloj.time;
				intent.putExtra("hash", gb.HASH); // Optional parameters
				intent.putExtra("score", gb.score);
				gb.getContext().startActivity(intent);
				setRunning(false);
				System.out.println(Thread.currentThread().getId()
						+ " Juego detenido.");
				setRunning(false);
			}
			if (gb.reloj.time == 0) {
				gb.mplNomario.start();
				gb.treloj.stop();
				System.out.println(Thread.currentThread().getId()
						+ " Time's up.");
				System.out.println(Thread.currentThread().getId()
						+ " GAME OVER.");
				System.out.println(Thread.currentThread().getId()
						+ " Lanzando Ranking.");
				Intent intent = new Intent(this.gb.getContext(),
						RankActivity.class);
				gb.score = 0;
				intent.putExtra("hash", gb.HASH); // Optional parameters
				intent.putExtra("score", gb.score);
				gb.getContext().startActivity(intent);
				setRunning(false);
			}
		}
	}

}
