package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.pedido.PedidoPagamento;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.PedidoPagamentoDAO;

import javax.persistence.EntityManager;

public class PedidosPagamentosImpl extends AbstractRepository<PedidoPagamento, Long> implements PedidoPagamentoDAO {

        public PedidosPagamentosImpl(EntityManager manager) {
            super(manager);
        }
        @Override
        public PedidoPagamento save(PedidoPagamento e) {
            getEntityManager().getTransaction().begin();
            PedidoPagamento v = super.save(e);
            getEntityManager().getTransaction().commit();
            return v;
        }

        @Override
        public void remove(PedidoPagamento e) {
            getEntityManager().getTransaction().begin();
            super.remove(e);
            getEntityManager().getTransaction().commit();
        }
}
