package com.labos.fingit;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class Gameboard extends SurfaceView {

	private SurfaceHolder holder;
	public List<Sangre> spritesSangre = new ArrayList<Sangre>();
	public List<Sprite> spritesMario = new ArrayList<Sprite>();
	public List<Sprite> spritesBowser = new ArrayList<Sprite>();
	private long lastClick;
	private Bitmap bmpSangre;
	static private final int BUENOS = 20;
	static private final int MALOS = 30;
	public int score = 0;
	public long time;
	public Dialog dialog;

	public Gameboard(Context context) {
		super(context);
		bmpSangre = BitmapFactory.decodeResource(getResources(),
				R.drawable.sangre);
		holder = getHolder();
		dialog = new Dialog(context);
		holder.addCallback(new Callback() {

			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
					int arg3) {
			}

			@Override
			public void surfaceCreated(SurfaceHolder arg0) {
				crearSprites();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
			}

		});
	}

	private Sprite crearSprite(int resource, int tipo) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
		return new Sprite(this, bmp, tipo);
	}

	private void crearSprites() {
		for (int i = 0; i < BUENOS; i++) {
			spritesMario.add(this.crearSprite(R.drawable.sangre, 0));
		}
		for (int i = 0; i < MALOS; i++) {
			spritesBowser.add(this.crearSprite(R.drawable.sangre, 1));
		}
	}

	public boolean gameOver() {
		return spritesBowser.isEmpty();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		for (int i = this.spritesSangre.size() - 1; i >= 0; i--) {
			this.spritesSangre.get(i).onDraw(canvas);
		}
		for (Sprite sprite : spritesMario) {
			sprite.onDraw(canvas);
		}
		for (Sprite sprite : spritesBowser) {
			sprite.onDraw(canvas);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean hecho = false;
		if (System.currentTimeMillis() - lastClick > 500) {
			lastClick = System.currentTimeMillis();

			synchronized (getHolder()) {// logica del juego que remueve
										// elementos en pantalla
				float x = event.getX();
				float y = event.getY();
				for (int i = spritesBowser.size() - 1; i >= 0; i--) {
					Sprite sprite = spritesBowser.get(i);
					time = System.currentTimeMillis();
					if (sprite.hayColision(x, y)) {
						sprite.sound();// suena segun su tipo
						score = score + (int) time;
						spritesBowser.remove(sprite);
						spritesSangre.add(new Sangre(this, x, y, bmpSangre));
						hecho = true;
						break;
					}
				}
				if (!hecho) {
					for (int i = spritesMario.size() - 1; i >= 0; i--) {
						Sprite sprite = spritesMario.get(i);
						time = System.currentTimeMillis();
						if (sprite.hayColision(x, y)) {
							sprite.sound();
							score = score - (int) time;
							spritesMario.remove(sprite);
							spritesSangre
									.add(new Sangre(this, x, y, bmpSangre));
							break;
						}
					}
				} else {
					hecho = false;
				}
			}
		}
		return true;
	}

}
