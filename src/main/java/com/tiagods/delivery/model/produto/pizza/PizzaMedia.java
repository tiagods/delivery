package com.tiagods.delivery.model.produto.pizza;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class PizzaMedia {
	private BigDecimal custoMedia=new BigDecimal(0.00);
	private double margemMedia=0.00;
	private BigDecimal vendaMedia=new BigDecimal(0.00);

	public BigDecimal getCustoMedia() {
		return custoMedia;
	}

	public void setCustoMedia(BigDecimal custoMedia) {
		this.custoMedia = custoMedia;
	}

	public double getMargemMedia() {
		return margemMedia;
	}

	public void setMargemMedia(double margemMedia) {
		this.margemMedia = margemMedia;
	}

	public BigDecimal getVendaMedia() {
		return vendaMedia;
	}

	public void setVendaMedia(BigDecimal vendaMedia) {
		this.vendaMedia = vendaMedia;
	}
}
