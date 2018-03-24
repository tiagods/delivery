package com.tiagods.delivery.model.produto;

import javax.persistence.Embeddable;

@Embeddable
public class Estoque {
	private int minimo = 0;
	private int maximo = 0;
	/**
	 * @return the minimo
	 */
	public int getMinimo() {
		return minimo;
	}
	/**
	 * @param minimo the minimo to set
	 */
	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}
	/**
	 * @return the maximo
	 */
	public int getMaximo() {
		return maximo;
	}
	/**
	 * @param maximo the maximo to set
	 */
	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}
	
}
