package com.labos.fingit;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button btnStart;
	private Button btnRanking;
	private MediaPlayer mplIntro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStart.setOnClickListener(this);
		btnRanking = (Button) findViewById(R.id.btnRanking);
		btnRanking.setOnClickListener(this);
		mplIntro = MediaPlayer.create(this, R.raw.peach);
		mplIntro.start();
	}

	@Override
	public void onClick(View arg0) {
		mplIntro.stop();
		switch (arg0.getId()) {
		case R.id.btnStart:
			Intent intentGame = new Intent(this, GameActivity.class);
			this.startActivity(intentGame);
			break;
		case R.id.btnRanking:
			Intent intentRank = new Intent(this, RankActivity.class);
			this.startActivity(intentRank);
			break;
		default:
			mplIntro.start();
			break;
		}

	}
}
