package com.labos.fingit;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RankActivity extends Activity implements View.OnClickListener {

	private Button buttonOk;
	private TextView txvPuntos;
	private TextView txvHash;
	public String HASH;
	public int score;
	public final int N = 10;
	public ArrayList<Score> topn;
	private MediaPlayer mplToprank;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);
		Bundle args = getIntent().getExtras();
		this.topn = new ArrayList<Score>();
		HASH = args.getString("hash");
		score = args.getInt("score");
		buttonOk = (Button) findViewById(R.id.buttonOk);
		buttonOk.setOnClickListener(this);
		txvPuntos = (TextView) findViewById(R.id.txtPuntos);
		txvPuntos.setText("" + score);
		txvHash = (TextView) findViewById(R.id.txtHash);
		txvHash.setText(HASH);
		mplToprank = MediaPlayer.create(this, R.raw.toprank);
	}

	@Override
	public void onClick(View arg0) {
		System.out.println(Thread.currentThread().getId() + " [INFO] " + HASH
				+ " " + score);
		ClientScore enviarScore = new ClientScore(new Score(HASH, score));
		Thread tenviarScore = new Thread(enviarScore);
		tenviarScore.start();
		System.out.println(Thread.currentThread().getId() + " Hijo "
				+ tenviarScore.getId() + " lanzado enviando score.");
		try {
			tenviarScore.join();
			Thread.sleep(1111);
			System.out.println(Thread.currentThread().getId()
					+ " Esperando el hijo " + tenviarScore.getId());
		} catch (Exception e) {
			System.out
					.println(Thread.currentThread().getId()
							+ " Error esperando hijo " + tenviarScore.getId()
							+ " " + e);
		}
		if (enviarScore.enviado) {
			ClientScore pedirTop = new ClientScore(new Score("TOP", 10));
			Thread tpedirTop = new Thread(pedirTop);
			tpedirTop.start();
			System.out.println(Thread.currentThread().getId()
					+ " Hijo lanzado " + tpedirTop.getId() + " pidiendo top.");
			try {
				tpedirTop.join();
				System.out.println(Thread.currentThread().getId()
						+ " Esperando el hijo " + tpedirTop.getId());
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getId()
						+ " Error esperando el hijo " + tpedirTop.getId() + " "
						+ e);
			}

			if (pedirTop.enviado) {
				Receptor receptor = new Receptor();
				Thread treceptor = new Thread(receptor);
				treceptor.start();
				System.out.println(Thread.currentThread().getId() + " Hijo "
						+ treceptor.getId() + " lanzado esperando top.");
				try {
					treceptor.join();
					System.out.println(Thread.currentThread().getId()
							+ " Esperando a hijo " + treceptor.getId());
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getId()
							+ " Error esperando a hijo " + treceptor.getId()
							+ " " + e);
				}
				if (receptor.recibido) {
					Rank rank = receptor.getRank();
					System.out.println(Thread.currentThread().getId()
							+ " Leyendo top " + N + "...");
					String[] values = new String[rank.getRank().size()];
					for (int i = 0; i < rank.getRank().size(); i++) {
						Score s = rank.getRank().get(i);
						values[i] = s.getHash() + " " + s.getPuntos();
						System.out.println(Thread.currentThread().getId() + " "
								+ (i + 1) + ". " + s.getHash() + " "
								+ s.getPuntos());
					}
					if (HASH.equals(rank.getRank().get(0).getHash())) {
						mplToprank.start();
					}
					Intent intentToplist = new Intent(this, TopListActivity.class);
					intentToplist.putExtra("topn", values);
					this.startActivity(intentToplist);
				}
			}
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
