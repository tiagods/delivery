package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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
}
