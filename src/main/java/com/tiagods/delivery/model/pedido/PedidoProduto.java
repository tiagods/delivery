package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.Pedido;
import com.tiagods.delivery.model.Produto;
import com.tiagods.delivery.model.Usuario;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

@Embeddable
public abstract class PedidoProduto implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    private Calendar criadoEm;
    @ManyToOne
    @JoinColumn(name = "criado_por_id")
    private Usuario criadoPor;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private int quantidade;
    private BigDecimal valor;
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.total = BigDecimal.valueOf(this.quantidade*this.valor.doubleValue());
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
        this.total = BigDecimal.valueOf(this.quantidade*this.valor.doubleValue());
    }

    public BigDecimal getTotal() {return total;}

    public void setTotal(BigDecimal total) {this.total = total;}

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
}
