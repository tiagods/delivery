package com.tiagods.delivery.model.produto.pizza;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class PizzaFatia {
	private BigDecimal custoFatia=new BigDecimal(0.00);
	private BigDecimal margemFatia=new BigDecimal(0.00);
	private BigDecimal vendaFatia=new BigDecimal(0.00);

	public BigDecimal getCustoFatia() {
		return custoFatia;
	}

	public void setCustoFatia(BigDecimal custoFatia) {
		this.custoFatia = custoFatia;
	}

	public BigDecimal getMargemFatia() {
		return margemFatia;
	}

	public void setMargemFatia(BigDecimal margemFatia) {
		this.margemFatia = margemFatia;
	}

	public BigDecimal getVendaFatia() {
		return vendaFatia;
	}

	public void setVendaFatia(BigDecimal vendaFatia) {
		this.vendaFatia = vendaFatia;
	}

	@Override
	public String toString() {
		return "Fatia";
	}
}
