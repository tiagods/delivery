package com.tiagods.delivery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Observacao implements AbstractEntity,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="observacao_categoria",
            joinColumns = { @JoinColumn(name = "observacao_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "categoria_id", referencedColumnName = "id") })
    private Set<ProdutoCategoria> categorias = new HashSet<>();

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

    public Set<ProdutoCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<ProdutoCategoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Observacao that = (Observacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nome;
    }
}
