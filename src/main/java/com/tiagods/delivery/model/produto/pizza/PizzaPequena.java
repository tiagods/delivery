package com.tiagods.delivery.model.produto.pizza;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class PizzaPequena {
	private BigDecimal custoPequeno=new BigDecimal(0.00);
	private double margemPequeno=0.00;
	private BigDecimal vendaPequeno=new BigDecimal(0.00);

	public BigDecimal getCustoPequeno() {
		return custoPequeno;
	}

	public void setCustoPequeno(BigDecimal custoPequeno) {
		this.custoPequeno = custoPequeno;
	}

	public double getMargemPequeno() {
		return margemPequeno;
	}

	public void setMargemPequeno(double margemPequeno) {
		this.margemPequeno = margemPequeno;
	}

	public BigDecimal getVendaPequeno() {
		return vendaPequeno;
	}

	public void setVendaPequeno(BigDecimal vendaPequeno) {
		this.vendaPequeno = vendaPequeno;
	}
}
