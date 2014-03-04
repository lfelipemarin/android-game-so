package com.labos.fingit;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;

public class Sprite {

	private static final int BMP_COLUMNAS = 3;
	private static final int BMP_FILAS = 4;
	private static final int[] MAPA_ANIMACIONES = { 3, 1, 0, 2 };
	public final int MARIO = 0;
	public final int BOWSER = 1;

	private int x = 0;
	private int y = 0;
	private int xSpeed;
	private int ySpeed;
	private Gameboard board;
	private Bitmap bmp;
	private int width;
	private int height;
	private int currentFrame = 0;
	private MediaPlayer mplMario;
	private MediaPlayer mplBowser;
	public int tipo;// MARIO O BOWSER

	public Sprite(Gameboard board, Bitmap bmp, int tipo) {
		this.board = board;
		this.bmp = bmp;
		this.width = bmp.getWidth() / BMP_COLUMNAS;
		this.height = bmp.getHeight() / BMP_FILAS;
		Random rnd = new Random();
		x = rnd.nextInt(board.getWidth() - width);
		y = rnd.nextInt(board.getHeight() - height);
		xSpeed = rnd.nextInt(10) - 5;
		ySpeed = rnd.nextInt(10) - 5;
		mplBowser = MediaPlayer.create(board.getContext(), R.raw.bowser);
		mplMario = MediaPlayer.create(board.getContext(), R.raw.mario);
		this.tipo = tipo;
	}

	private void update() {
		if (x > board.getWidth() - width - xSpeed || x + xSpeed < 0) {
			xSpeed = -xSpeed;
		}
		x = x + xSpeed;
		if (y > board.getHeight() - height - ySpeed || y + ySpeed < 0) {
			ySpeed = -ySpeed;
		}
		y = y + ySpeed;
		currentFrame = ++currentFrame % BMP_COLUMNAS;
	}

	public void onDraw(Canvas canvas) {
		update();
		int srcX = currentFrame * width;
		int srcY = getAnimationRow() * height;
		Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
		Rect dst = new Rect(x, y, x + width, y + height);
		canvas.drawBitmap(bmp, src, dst, null);
	}

	private int getAnimationRow() {
		double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
		int direction = (int) Math.round(dirDouble) % BMP_FILAS;
		return MAPA_ANIMACIONES[direction];
	}

	public boolean hayColision(float x2, float y2) {
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}

	public void sound() {
		switch (tipo) {
		case MARIO:
			mplMario.start();
			break;
		case BOWSER:
			mplBowser.start();
			break;
		default:
			break;
		}
	}
}
