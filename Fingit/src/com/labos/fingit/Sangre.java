package com.labos.fingit;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sangre {

	private float x;
	private float y;
	private Bitmap bmpSangre;
	private int vida = 15;
	private Gameboard gb;

	public Sangre(Gameboard gb, float x, float y, Bitmap bmp) {
		this.bmpSangre = bmp;
		this.gb = gb;
		this.x = Math.min(Math.max(x - bmp.getWidth() / 2, 0), gb.getWidth()
				- bmp.getWidth());
		this.y = Math.min(Math.max(y - bmp.getHeight() / 2, 0), gb.getHeight()
				- bmp.getHeight());
	}

	public void onDraw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmpSangre, x, y, null);
	}

	private void update() {
		if (--vida < 1) {
			this.gb.spritesSangre.remove(this);
		}
	}

}
