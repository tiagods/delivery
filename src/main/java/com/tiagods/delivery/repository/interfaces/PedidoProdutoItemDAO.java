package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.pedido.PedidoProdutoItem;

import java.util.List;

public interface PedidoProdutoItemDAO {
    PedidoProdutoItem save(PedidoProdutoItem c);
    void remove(PedidoProdutoItem c);
    PedidoProdutoItem findById(Long id);
}
