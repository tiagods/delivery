package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.AbstractEntity;
import com.tiagods.delivery.model.pagamento.CartaoBandeira;
import com.tiagods.delivery.model.pagamento.ModalidadePagamento;
import com.tiagods.delivery.model.pagamento.ValeBandeira;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "pedido_pagamento")
public class PedidoPagamento implements AbstractEntity, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_pagamento")
    private ModalidadePagamento modalidadePagamento;
    @Enumerated(value = EnumType.STRING)
    private CartaoBandeira cartaoBandeira;
    @Enumerated(value = EnumType.STRING)
    private ValeBandeira valeBandeira;
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoPagamento that = (PedidoPagamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public ModalidadePagamento getModalidadePagamento() {
        return modalidadePagamento;
    }

    public void setModalidadePagamento(ModalidadePagamento modalidadePagamento) {
        this.modalidadePagamento = modalidadePagamento;
    }

    public CartaoBandeira getCartaoBandeira() {
        return cartaoBandeira;
    }

    public void setCartaoBandeira(CartaoBandeira cartaoBandeira) {
        this.cartaoBandeira = cartaoBandeira;
    }

    public ValeBandeira getValeBandeira() {
        return valeBandeira;
    }

    public void setValeBandeira(ValeBandeira valeBandeira) {
        this.valeBandeira = valeBandeira;
    }
}
