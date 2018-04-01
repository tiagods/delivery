package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.Complemento;

import java.util.List;

public interface ComplementoDAO {
    Complemento save(Complemento c);
    void remove(Complemento c);
    List<Complemento> getAll();
    Complemento findById(Long id);
    List<Complemento> findByNome(String nome);
}
