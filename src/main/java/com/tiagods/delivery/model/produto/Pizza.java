package com.tiagods.delivery.model.produto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.tiagods.delivery.model.Produto;
import com.tiagods.delivery.model.produto.pizza.PizzaFatia;
import com.tiagods.delivery.model.produto.pizza.PizzaGrande;
import com.tiagods.delivery.model.produto.pizza.PizzaMedia;
import com.tiagods.delivery.model.produto.pizza.PizzaPequena;

@Entity
@DiscriminatorValue(value = "pizza")
public class Pizza extends Produto {
	private boolean fatiaHabilitada;
	@Embedded
	private PizzaFatia fatia;
	private boolean pequenaHabilitada;
	@Embedded
	private PizzaPequena pequena;
	private boolean mediaHabilitada;
	@Embedded
	private PizzaMedia media;
	private boolean grandeHabilitada;
	@Embedded
	private PizzaGrande grande;

	public boolean isFatiaHabilitada() {
		return fatiaHabilitada;
	}

	public void setFatiaHabilitada(boolean fatiaHabilitada) {
		this.fatiaHabilitada = fatiaHabilitada;
	}

	public PizzaFatia getFatia() {
		return fatia;
	}

	public void setFatia(PizzaFatia fatia) {
		this.fatia = fatia;
	}

	public boolean isPequenaHabilitada() {
		return pequenaHabilitada;
	}

	public void setPequenaHabilitada(boolean pequenaHabilitada) {
		this.pequenaHabilitada = pequenaHabilitada;
	}

	public PizzaPequena getPequena() {
		return pequena;
	}

	public void setPequena(PizzaPequena pequena) {
		this.pequena = pequena;
	}

	public boolean isMediaHabilitada() {
		return mediaHabilitada;
	}

	public void setMediaHabilitada(boolean mediaHabilitada) {
		this.mediaHabilitada = mediaHabilitada;
	}

	public PizzaMedia getMedia() {
		return media;
	}

	public void setMedia(PizzaMedia media) {
		this.media = media;
	}

	public boolean isGrandeHabilitada() {
		return grandeHabilitada;
	}

	public void setGrandeHabilitada(boolean grandeHabilitada) {
		this.grandeHabilitada = grandeHabilitada;
	}

	public PizzaGrande getGrande() {
		return grande;
	}

	public void setGrande(PizzaGrande grande) {
		this.grande = grande;
	}
}
