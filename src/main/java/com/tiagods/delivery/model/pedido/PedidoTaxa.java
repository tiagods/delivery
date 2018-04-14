package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/*
    Taxa de entrega por distancia percorrida
 */
@Entity
@Table(name = "pedido_taxa")
public class PedidoTaxa  implements AbstractEntity,Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valor;
    @Override
    public Number getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        Locale locale = new Locale("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return this.nome+" ("+currencyFormatter.format(valor.doubleValue())+")";
    }
}
