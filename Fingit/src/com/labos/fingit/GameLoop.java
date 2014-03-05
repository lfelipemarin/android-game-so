package com.labos.fingit;



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

}
