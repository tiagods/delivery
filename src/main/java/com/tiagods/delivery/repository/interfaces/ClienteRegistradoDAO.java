package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.ClienteRegistrado;

import java.util.List;

public interface ClienteRegistradoDAO {
    ClienteRegistrado save(ClienteRegistrado c);
    void remove(ClienteRegistrado c);
    List<ClienteRegistrado> getAll();
    ClienteRegistrado findById(Long id);
    ClienteRegistrado findByNome(String nome);

}
