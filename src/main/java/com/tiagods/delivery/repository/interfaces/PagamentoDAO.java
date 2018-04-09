package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.pedido.Pagamento;

import java.util.List;

public interface PagamentoDAO {
    Pagamento save(Pagamento c);
    void remove(Pagamento c);
    List<Pagamento> getAll();
    Pagamento findById(Long id);
}
