package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.pedido.PedidoCaixa;

import java.util.List;

public interface PedidoCaixaDAO {
    PedidoCaixa save(PedidoCaixa c);
    void remove(PedidoCaixa c);
    List<PedidoCaixa> getAll();
    PedidoCaixa findById(Long id);
}
