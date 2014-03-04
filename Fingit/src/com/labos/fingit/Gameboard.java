package com.labos.fingit;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class Gameboard extends SurfaceView {

	private SurfaceHolder holder;
	public List<Sangre> spritesSangre = new ArrayList<Sangre>();
	private long lastClick;
	private Bitmap bmpSangre;
	static private final int BUENOS = 20;
	static private final int MALOS = 30;
	public int score = 0;
	public long time;
	public Dialog dialog;
	private Bitmap bmpBlood;

	public Gameboard(Context context) {
		super(context);
		bmpBlood = BitmapFactory.decodeResource(getResources(),
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
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
			}

		});
	}

}
