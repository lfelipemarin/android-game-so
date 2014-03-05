package com.labos.fingit;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(new Gameboard(this));
//		ClientScore cs = new ClientScore(new Score("opjcoeggevnf", 42993023));
//		Thread nth = new Thread(cs);
//		nth.start();
	}
}
