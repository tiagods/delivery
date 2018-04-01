package com.tiagods.delivery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Complemento implements AbstractEntity,Serializable{
    private Long id;
    private String nome;
    private BigDecimal valor;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="complemento_categoria",
            joinColumns = { @JoinColumn(name = "complemento_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "categoria_id", referencedColumnName = "id") })
    private Set<ProdutoCategoria> categorias = new HashSet<>();

    @Override
    public Number getId() {
        return id;
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

    public Set<ProdutoCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<ProdutoCategoria> categorias) {
        this.categorias = categorias;
    }
}
