package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.Observacao;
import com.tiagods.delivery.model.ProdutoCategoria;

import java.util.List;

public interface ObservacaoDAO {
    Observacao save(Observacao c);
    void remove(Observacao c);
    List<Observacao> getAll();
    Observacao findById(Long id);
    List<Observacao> findByNome(String nome);

    List<Observacao> findByCategoria(ProdutoCategoria categoria);
}
