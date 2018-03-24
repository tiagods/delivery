package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.Cliente;

import java.util.List;

public interface ClienteDAO {
    Cliente save(Cliente cliente);
    void remove(Cliente cleinte);
    List<Cliente> getAll();
    Cliente findById(Long id);
    Cliente findByNome(String nome);
}
