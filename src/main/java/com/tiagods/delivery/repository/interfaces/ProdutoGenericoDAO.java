package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.produto.ProdutoGenerico;

import java.util.List;

public interface ProdutoGenericoDAO {
    ProdutoGenerico save(ProdutoGenerico cliente);
    void remove(ProdutoGenerico cleinte);
    List<ProdutoGenerico> getAll();
    ProdutoGenerico findById(Long id);
    List<ProdutoGenerico> findByNome(String nome);
}
