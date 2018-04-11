package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.pedido.PedidoTaxa;

import java.util.List;

public interface PedidoTaxaDAO {
    PedidoTaxa save(PedidoTaxa c);
    void remove(PedidoTaxa c);
    List<PedidoTaxa> getAll();
    PedidoTaxa findById(Long id);
    List<PedidoTaxa> findByNome(String nome);
}
