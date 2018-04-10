package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.AbstractEntity;
import com.tiagods.delivery.model.Usuario;

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
    @Temporal(value = TemporalType.TIMESTAMP)

    private Calendar criadoEm;
    @ManyToOne
    @JoinColumn(name = "criado_por_id")
    private Usuario criadoPor;

    private BigDecimal total;
    private BigDecimal desconto;
    private BigDecimal servico;
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
