package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.pedido.PedidoProdutoItemAdicional;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.PedidoProdutoItemAdicionalDAO;

import javax.persistence.EntityManager;

public class PedidosProdutosItensAddImpl extends AbstractRepository<PedidoProdutoItemAdicional, Long> implements PedidoProdutoItemAdicionalDAO {

    public PedidosProdutosItensAddImpl(EntityManager manager) {
        super(manager);
    }
    @Override
    public PedidoProdutoItemAdicional save(PedidoProdutoItemAdicional e) {
        getEntityManager().getTransaction().begin();
        PedidoProdutoItemAdicional v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(PedidoProdutoItemAdicional e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }

}
