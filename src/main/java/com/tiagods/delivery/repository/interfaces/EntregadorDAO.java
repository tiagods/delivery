package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.Entregador;

import java.util.List;

public interface EntregadorDAO {
    Entregador save(Entregador c);
    void remove(Entregador c);
    Entregador findByNome(String nome);
    List<Entregador> filtrarAtivos();
    List<Entregador> getAll();
    Entregador findById(Long id);
}
