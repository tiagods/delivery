package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.Produto;

import java.util.List;

public interface ProdutoDAO {
    Produto save(Produto cliente);
    void remove(Produto cleinte);
    List<Produto> getAll();
    Produto findById(Long id);
    List<Produto> findByNome(String nome);
}
