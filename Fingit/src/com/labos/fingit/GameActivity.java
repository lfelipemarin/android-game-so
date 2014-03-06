package com.labos.fingit;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Gameboard(this));
		// Receptor rc = new Receptor();
		// Thread trc = new Thread(rc);
		// trc.start();
		// ClientScore cs = new ClientScore(new Score("opjcoeggevnf",
		// 42993023));
		// Thread nth = new Thread(cs);
		// nth.start();
	}

}
