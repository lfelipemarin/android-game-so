package com.labos.fingit;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

	private Gameboard gb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gb = new Gameboard(this);
		setContentView(gb);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}
}
