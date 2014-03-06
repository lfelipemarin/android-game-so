package com.labos.fingit;

import java.io.Serializable;
import java.util.ArrayList;

public class Rank implements Serializable {

	private final ArrayList<Score> topn;

	public Rank() {

        topn = new ArrayList<Score>();
    }

	public ArrayList<Score> getRank() {
		return topn;
	}

	public void add(Score score) {
		this.topn.add(score);
	}

}
