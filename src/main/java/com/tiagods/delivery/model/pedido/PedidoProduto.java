package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.AbstractEntity;
import com.tiagods.delivery.model.Pedido;
import com.tiagods.delivery.model.Produto;
import com.tiagods.delivery.model.Usuario;
import com.tiagods.delivery.model.produto.pizza.PizzaTipo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

@MappedSuperclass
@Embeddable
public abstract class PedidoProduto  implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    private Calendar criadoEm;
    @ManyToOne
    @JoinColumn(name = "criado_por_id")
    private Usuario criadoPor;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private String nome;
    @Enumerated(value = EnumType.STRING)
    private PizzaTipo pizzaVendida;
    private BigDecimal valor = new BigDecimal(0.00);
    private BigDecimal valorExtra = new BigDecimal(0.00);
    private int quantidade;
    private BigDecimal total = new BigDecimal(0.00);

    @PrePersist @PreUpdate
    void recalcular(){
        this.total=new BigDecimal((valor.doubleValue()+valorExtra.doubleValue())*quantidade);
    }
    @ManyToOne
    private Pedido pedido;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public PizzaTipo getPizzaVendida() {
        return pizzaVendida;
    }

    public void setPizzaVendida(PizzaTipo pizzaVendida) {
        this.pizzaVendida = pizzaVendida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorExtra() {
        return valorExtra;
    }

    public void setValorExtra(BigDecimal valorExtra) {
        this.valorExtra = valorExtra;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
