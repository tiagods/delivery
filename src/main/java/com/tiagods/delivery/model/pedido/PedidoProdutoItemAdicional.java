package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.AbstractEntity;
import com.tiagods.delivery.model.Complemento;
import com.tiagods.delivery.model.Observacao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ped_prod_item_add")
public class PedidoProdutoItemAdicional extends PedidoProduto implements AbstractEntity,Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ped_pro_item_add_complemento",
            joinColumns = { @JoinColumn(name = "per_prod_item_add_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "complemento_id", referencedColumnName = "id") })
    private Set<Complemento> complementos = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ped_pro_item_add_observacao",
            joinColumns = { @JoinColumn(name = "per_prod_item_add_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "observacao_id", referencedColumnName = "id") })
    private Set<Observacao> observacoes = new HashSet<>();
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Complemento> getComplementos() {
        return complementos;
    }

    public void setComplementos(Set<Complemento> complementos) {
        this.complementos = complementos;
    }

    public Set<Observacao> getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(Set<Observacao> observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoProdutoItemAdicional that = (PedidoProdutoItemAdicional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
