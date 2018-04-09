package com.tiagods.delivery.model;

import com.tiagods.delivery.model.pedido.PedidoPagamento;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.model.pedido.PedidoStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido implements AbstractEntity,Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar criadoEm;
    @ManyToOne
    @JoinColumn(name = "criado_por_id")
    private Usuario criadoPor;
    @ManyToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;
    private BigDecimal taxa;
    private BigDecimal total;
    private BigDecimal desconto;
    @Enumerated(value = EnumType.STRING)
    private PedidoStatus status;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Set<PedidoProdutoItem> produtos = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="pedido_id")
    private Set<PedidoPagamento> pagamentos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public Set<PedidoProdutoItem> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<PedidoProdutoItem> produtos) {
        this.produtos = produtos;
    }

    public Set<PedidoPagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(Set<PedidoPagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
