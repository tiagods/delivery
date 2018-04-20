package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.pedido.PedidoPagamento;
import com.tiagods.delivery.model.pedido.PedidoTaxa;

import java.util.List;

public interface PedidoPagamentoDAO {
    PedidoPagamento save(PedidoPagamento c);
    void remove(PedidoPagamento c);
    List<PedidoPagamento> getAll();
    PedidoPagamento findById(Long id);
}
