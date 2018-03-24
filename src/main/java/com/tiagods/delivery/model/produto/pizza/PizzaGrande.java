package com.tiagods.delivery.model.produto.pizza;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class PizzaGrande {
	private BigDecimal custoGrande;
	private double margemGrande=0.00;
	private BigDecimal vendaGrande;

	public BigDecimal getCustoGrande() {
		return custoGrande;
	}

	public void setCustoGrande(BigDecimal custoGrande) {
		this.custoGrande = custoGrande;
	}

	public double getMargemGrande() {
		return margemGrande;
	}

	public void setMargemGrande(double margemGrande) {
		this.margemGrande = margemGrande;
	}

	public BigDecimal getVendaGrande() {
		return vendaGrande;
	}

	public void setVendaGrande(BigDecimal vendaGrande) {
		this.vendaGrande = vendaGrande;
	}
}
