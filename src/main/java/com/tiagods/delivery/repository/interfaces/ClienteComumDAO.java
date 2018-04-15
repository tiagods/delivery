package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.ClienteComum;

import java.util.List;

public interface ClienteComumDAO {
    ClienteComum save(ClienteComum c);
    void remove(ClienteComum c);
    List<ClienteComum> getAll();
    ClienteComum findById(Long id);
    ClienteComum findByNome(String nome);

}
