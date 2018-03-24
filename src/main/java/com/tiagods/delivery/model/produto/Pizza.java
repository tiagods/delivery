package com.tiagods.delivery.model.produto;

import javax.persistence.Embeddable;

import com.tiagods.delivery.model.produto.pizza.PizzaFatia;
import com.tiagods.delivery.model.produto.pizza.PizzaGrande;
import com.tiagods.delivery.model.produto.pizza.PizzaMedia;
import com.tiagods.delivery.model.produto.pizza.PizzaPequena;

@Embeddable
public class Pizza {
	private PizzaFatia fatia;
	private PizzaPequena pequena;
	private PizzaMedia media;
	private PizzaGrande grande;
	/**
	 * @return the fatia
	 */
	public PizzaFatia getFatia() {
		return fatia;
	}
	/**
	 * @param fatia the fatia to set
	 */
	public void setFatia(PizzaFatia fatia) {
		this.fatia = fatia;
	}
	/**
	 * @return the pequena
	 */
	public PizzaPequena getPequena() {
		return pequena;
	}
	/**
	 * @param pequena the pequena to set
	 */
	public void setPequena(PizzaPequena pequena) {
		this.pequena = pequena;
	}
	/**
	 * @return the media
	 */
	public PizzaMedia getMedia() {
		return media;
	}
	/**
	 * @param media the media to set
	 */
	public void setMedia(PizzaMedia media) {
		this.media = media;
	}
	/**
	 * @return the grande
	 */
	public PizzaGrande getGrande() {
		return grande;
	}
	/**
	 * @param grande the grande to set
	 */
	public void setGrande(PizzaGrande grande) {
		this.grande = grande;
	}
	
}
