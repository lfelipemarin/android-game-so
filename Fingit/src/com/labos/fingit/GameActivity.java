package com.labos.fingit;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

	private Gameboard gb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println(Thread.currentThread().getId()
				+ " Creando "+getClass());
		Bundle args = getIntent().getExtras();
		gb = new Gameboard(this, args.getInt("enemigos"));
		setContentView(gb);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println(Thread.currentThread().getId()
				+ " Destruyendo "+getClass());
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println(Thread.currentThread().getId()
				+ " Paunsando  "+getClass());
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println(Thread.currentThread().getId()
				+ " Parando  "+getClass());
	}

	@Override
	protected void onRestart() {
		super.onRestart();		
		System.out.println(Thread.currentThread().getId()
				+ " Reiniciando  "+getClass());
	}
}
