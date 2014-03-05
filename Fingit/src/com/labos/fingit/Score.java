package com.labos.fingit;

import java.io.Serializable;

public class Score implements Serializable {

	private final int puntos;
	private final String hash;

	public Score(String hash, int puntos) {
		this.hash = hash;
		this.puntos = puntos;
	}

	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

}
