package com.tiagods.delivery.model.produto.pizza;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class PizzaPequena {
	private BigDecimal custoPequeno=new BigDecimal(0.00);
	private BigDecimal margemPequeno=new BigDecimal(0.00);
	private BigDecimal vendaPequeno=new BigDecimal(0.00);

	public BigDecimal getCustoPequeno() {
		return custoPequeno;
	}

	public void setCustoPequeno(BigDecimal custoPequeno) {
		this.custoPequeno = custoPequeno;
	}

	public BigDecimal getMargemPequeno() {
		return margemPequeno;
	}

	public void setMargemPequeno(BigDecimal margemPequeno) {
		this.margemPequeno = margemPequeno;
	}

	public BigDecimal getVendaPequeno() {
		return vendaPequeno;
	}

	public void setVendaPequeno(BigDecimal vendaPequeno) {
		this.vendaPequeno = vendaPequeno;
	}


}
