package com.labos.fingit;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button btnStart;
	private Button btnRanking;
	private MediaPlayer mplIntro;
	public String HASH;
	private SeekBar seekBar;
	private TextView txvEnemies;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HASH = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
		setContentView(R.layout.activity_main);
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStart.setOnClickListener(this);
		btnRanking = (Button) findViewById(R.id.btnRanking);
		btnRanking.setOnClickListener(this);
		// mplIntro.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mplIntro = MediaPlayer.create(this, R.raw.peach);
		mplIntro.start();
		seekBar = (SeekBar) findViewById(R.id.seekEnemies);
		seekBar.setProgress(1);
		seekBar.setMax(33);
		txvEnemies = (TextView) findViewById(R.id.txtEnemiesv);
		// Initialize the textview with '0'
		txvEnemies.setText(seekBar.getProgress() + "/" + seekBar.getMax());
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				txvEnemies.setText(seekBar.getProgress() + "/"
						+ seekBar.getMax());
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

		});
	}

	@Override
	public void onClick(View arg0) {
		mplIntro.stop();
		switch (arg0.getId()) {
		case R.id.btnStart:
			Intent intentGame = new Intent(this, GameActivity.class);
			intentGame.putExtra("enemigos", seekBar.getProgress());
			this.startActivity(intentGame);
			break;
		case R.id.btnRanking:
			Intent intentRank = new Intent(this, RankActivity.class);
			intentRank.putExtra("hash", HASH);
			intentRank.putExtra("score", 0);
			this.startActivity(intentRank);
			break;
		default:
			mplIntro.start();
			break;
		}

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
