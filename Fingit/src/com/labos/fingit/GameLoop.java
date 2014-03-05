package com.labos.fingit;

import android.graphics.Canvas;
import android.widget.Toast;

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
				// for (int i = 0; i < gb.spritesMario.size(); i++) {
				// gb.time = System.currentTimeMillis();
				// gb.score = gb.score + (int) gb.time;
				// }
//				Toast toast = Toast.makeText(gb.getContext(), "Score", Toast.LENGTH_SHORT);
//		        toast.show();
//				try {
//					sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}

				 setRunning(false);
			}
		}
	}

}
