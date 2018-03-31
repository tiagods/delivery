package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.produto.Pizza;

import java.util.List;

public interface PizzaDAO {
    Pizza save(Pizza cliente);
    void remove(Pizza cleinte);
    List<Pizza> getAll();
    Pizza findById(Long id);
    List<Pizza> findByNome(String nome);
}
