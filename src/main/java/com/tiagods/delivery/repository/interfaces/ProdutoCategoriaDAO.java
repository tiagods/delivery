package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.ProdutoCategoria;

import java.util.List;

public interface ProdutoCategoriaDAO {
    ProdutoCategoria save(ProdutoCategoria c);
    void remove(ProdutoCategoria c);
    List<ProdutoCategoria> getAll();
    ProdutoCategoria findById(Long id);
    List<ProdutoCategoria> findByNome(String nome);
}
