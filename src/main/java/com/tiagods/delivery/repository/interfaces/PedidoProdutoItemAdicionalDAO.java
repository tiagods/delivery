package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.pedido.PedidoProdutoItemAdicional;

public interface PedidoProdutoItemAdicionalDAO {
    PedidoProdutoItemAdicional save(PedidoProdutoItemAdicional c);
    void remove(PedidoProdutoItemAdicional c);
    PedidoProdutoItemAdicional findById(Long id);
}
