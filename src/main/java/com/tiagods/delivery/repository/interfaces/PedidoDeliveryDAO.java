package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.pedido.PedidoDelivery;

import java.util.List;

public interface PedidoDeliveryDAO {
    PedidoDelivery save(PedidoDelivery c);
    void remove(PedidoDelivery c);
    List<PedidoDelivery> getAll();
    PedidoDelivery findById(Long id);
}
