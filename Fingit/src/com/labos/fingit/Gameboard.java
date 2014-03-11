package com.labos.fingit;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.MediaPlayer;
import android.provider.Settings.Secure;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class Gameboard extends SurfaceView {

	private SurfaceHolder holder;
	private GameLoop gameLoop;
	public List<Sangre> spritesSangre = new ArrayList<Sangre>();
	public List<Sprite> spritesMario = new ArrayList<Sprite>();
	public List<Sprite> spritesBowser = new ArrayList<Sprite>();
	private long lastClick;
	private Bitmap bmpSangre;
	private int MALOS;
	private int BUENOS;
	public final String HASH;
	public int score = 0;
	public long time;
	public Dialog dialog;
	public MediaPlayer mplOpen;
	public MediaPlayer mplNomario;
	public MediaPlayer mplAnymario;
	public MediaPlayer mplPeach;
	public int clock;
	public Reloj reloj;
	public Thread treloj;
	private Paint paint;

	// private MediaPlayer mplSong;
	// private Context context;

	public Gameboard(Context context, int enemigos) {
		super(context);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);
		// paint.setColor(Color.BLACK);
		paint.setTextSize(18);
		this.BUENOS = enemigos * 2;
		this.MALOS = enemigos;
		this.reloj = new Reloj(MALOS * 3);
		this.treloj = new Thread(reloj);
		HASH = Secure.getString(getContext().getContentResolver(),
				Secure.ANDROID_ID);
		mplOpen = MediaPlayer.create(context, R.raw.open);
		mplNomario = MediaPlayer.create(context, R.raw.nomario);
		mplAnymario = MediaPlayer.create(context, R.raw.anymario);
		mplPeach = MediaPlayer.create(context, R.raw.peach);
		// mplSong = MediaPlayer.create(context, R.raw.song);
		gameLoop = new GameLoop(this);
		bmpSangre = BitmapFactory.decodeResource(getResources(),
				R.drawable.sangre);
		holder = getHolder();
		// dialog = new Dialog(context);
		holder.addCallback(new Callback() {

			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
					int arg3) {
			}

			@Override
			public void surfaceCreated(SurfaceHolder arg0) {
				crearSprites();
				mplOpen.start();
				treloj.start();
				gameLoop.setRunning(true);
				gameLoop.start();
				// mplSong.start();
				// mplOpen.release();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				boolean retry = true;
				gameLoop.setRunning(false);
				while (retry) {
					try {
						gameLoop.join();
						treloj.join();
						retry = false;
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getId()
								+ " Error join hijos destroy sfcviw. " + e);
					}
				}
			}

		});
	}

	public void finalSound() {
		if (!spritesMario.isEmpty()) {
			System.out.println(Thread.currentThread().getId() + " Ganaste!!!");
			mplAnymario.start();
		} else {
			System.out.println(Thread.currentThread().getId() + " Perdiste!!!");
			mplNomario.start();
		}
	}

	private Sprite crearSprite(int resource, int tipo) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
		return new Sprite(this, bmp, tipo);
	}

	private void crearSprites() {
		for (int i = 0; i < BUENOS; i++) {
			spritesMario.add(this.crearSprite(R.drawable.mario_moves, 0));
		}
		for (int i = 0; i < MALOS; i++) {
			spritesBowser.add(this.crearSprite(R.drawable.bowser_moves, 1));
		}
	}

	public boolean gameOver() {
		return spritesBowser.isEmpty();
		// if (spritesBowser.isEmpty()) {
		// Toast toast = Toast.makeText(getContext(), "Score: " + score,
		// Toast.LENGTH_SHORT);
		// toast.show();
		// return true;
		// } else {
		// return false;
		// }
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		canvas.drawText("Marios: " + spritesMario.size(), 10, 25, paint);
		canvas.drawText("Bowsers: " + spritesBowser.size(), 10, 40, paint);
		canvas.drawText("Quedan " + reloj.time + " segundo(s)", 10, 55, paint);
		for (int i = this.spritesBowser.size() - 1; i >= 0; i--) {
			this.spritesBowser.get(i).onDraw(canvas);
		}
		for (int i = this.spritesSangre.size() - 1; i >= 0; i--) {
			this.spritesSangre.get(i).onDraw(canvas);
		}
		for (int i = this.spritesMario.size() - 1; i >= 0; i--) {
			this.spritesMario.get(i).onDraw(canvas);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Toast toast = Toast.makeText(getContext(), "Score: " + score,
		// Toast.LENGTH_SHORT);
		// toast.show();
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
						score++;
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
							// score = score - (int) (Math.random() * score);
							score--;
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
