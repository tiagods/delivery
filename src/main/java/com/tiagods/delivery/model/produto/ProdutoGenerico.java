package com.tiagods.delivery.model.produto;

import com.tiagods.delivery.model.Produto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value="generico")
public class ProdutoGenerico extends Produto {
    private BigDecimal custo=new BigDecimal(0.00);
    private double margem=0.00;
    private BigDecimal venda=new BigDecimal(0.00);

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public double getMargem() {
        return margem;
    }

    public void setMargem(double margem) {
        this.margem = margem;
    }

    public BigDecimal getVenda() {
        return venda;
    }

    public void setVenda(BigDecimal venda) {
        this.venda = venda;
    }
}
