package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.pedido.PedidoProdutoItem;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.PedidoProdutoItemDAO;

import javax.persistence.EntityManager;

public class PedidosProdutosItensImpl extends AbstractRepository<PedidoProdutoItem, Long> implements PedidoProdutoItemDAO {

    public PedidosProdutosItensImpl(EntityManager manager) {
        super(manager);
    }
    @Override
    public PedidoProdutoItem save(PedidoProdutoItem e) {
        getEntityManager().getTransaction().begin();
        PedidoProdutoItem v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(PedidoProdutoItem e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }

}
