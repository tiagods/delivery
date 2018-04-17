package com.tiagods.delivery.model;

import com.tiagods.delivery.model.pedido.PedidoPagamento;
import com.tiagods.delivery.model.pedido.PedidoProdutoItem;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pedido_type")
public abstract class Pedido implements AbstractEntity,Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    private Calendar criadoEm;
    @ManyToOne
    @JoinColumn(name = "criado_por_id")
    private Usuario criadoPor;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private BigDecimal subTotal = new BigDecimal(0.00);
    private BigDecimal total = new BigDecimal(0.00);
    private BigDecimal desconto = new BigDecimal(0.00);
    private BigDecimal servico = new BigDecimal(0.00);
    private BigDecimal valorPago = new BigDecimal(0.00);
    private boolean pago=false;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private Set<PedidoProdutoItem> produtos = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="pedido_id")
    private Set<PedidoPagamento> pagamentos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Calendar criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Usuario getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Usuario criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
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

    public BigDecimal getServico() {
        return servico;
    }

    public void setServico(BigDecimal servico) {
        this.servico = servico;
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

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
