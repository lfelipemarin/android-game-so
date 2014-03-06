package com.labos.fingit;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RankActivity extends Activity implements View.OnClickListener {

	private Button btnOk;
	private TextView txtPuntos;
	public String HASH;
	public int score;
	public final int N = 10;
	public ArrayList<Score> topn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);
		Bundle args = getIntent().getExtras();
		this.topn = new ArrayList<Score>();
		HASH = args.getString("hash");
		score = args.getInt("score");
		btnOk = (Button) findViewById(R.id.btnOk);
		btnOk.setOnClickListener(this);
		txtPuntos = (TextView) findViewById(R.id.txtPuntos);
		txtPuntos.setText(String.valueOf("" + score));
	}

	@Override
	public void onClick(View arg0) {		
		System.out.println(Thread.currentThread().getId() + " [INFO] "+HASH+" "+score);
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
				// try {
				if (receptor.recibido) {
					Rank rank = receptor.getRank();
					System.out.println(Thread.currentThread().getId()
							+ " Leyendo top " + N + "...");

					for (int i = 0; i < rank.getRank().size(); i++) {
						Score s = rank.getRank().get(i);
						System.out.println(Thread.currentThread().getId()
								+ " "+(i+1)+". " + s.getHash() + " " + s.getPuntos());
					}
				}
				// } catch (Exception e) {
				// System.out.println(Thread.currentThread().getId()
				// + " Error leyendo el top. " + e);
				// }
			}
		}

		// Toast msjOk = Toast.makeText(this, "Se consulto y volvio.",
		// Toast.LENGTH_LONG);
		// msjOk.show();
	}
}
