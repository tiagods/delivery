package com.tiagods.delivery.model.produto.pizza;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class PizzaGrande {
	private BigDecimal custoGrande=new BigDecimal(0.00);
	private BigDecimal margemGrande=new BigDecimal(0.00);
	private BigDecimal vendaGrande=new BigDecimal(0.00);

	public BigDecimal getCustoGrande() {
		return custoGrande;
	}

	public void setCustoGrande(BigDecimal custoGrande) {
		this.custoGrande = custoGrande;
	}

	public BigDecimal getMargemGrande() {
		return margemGrande;
	}

	public void setMargemGrande(BigDecimal margemGrande) {
		this.margemGrande = margemGrande;
	}

	public BigDecimal getVendaGrande() {
		return vendaGrande;
	}

	public void setVendaGrande(BigDecimal vendaGrande) {
		this.vendaGrande = vendaGrande;
	}
}
