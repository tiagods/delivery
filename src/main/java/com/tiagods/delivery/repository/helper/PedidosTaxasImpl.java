package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.pedido.PedidoTaxa;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.PedidoTaxaDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidosTaxasImpl extends AbstractRepository<PedidoTaxa, Long> implements PedidoTaxaDAO {

        public PedidosTaxasImpl(EntityManager manager) {
            super(manager);
        }
        @Override
        public PedidoTaxa save(PedidoTaxa e) {
            getEntityManager().getTransaction().begin();
            PedidoTaxa v = super.save(e);
            getEntityManager().getTransaction().commit();
            return v;
        }

        @Override
        public void remove(PedidoTaxa e) {
            getEntityManager().getTransaction().begin();
            super.remove(e);
            getEntityManager().getTransaction().commit();
        }

        @Override
        public List<PedidoTaxa> findByNome(String nome) {
            Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(PedidoTaxa.class);
            criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
            return criteria.list();
        }

        @Override
        public List<PedidoTaxa> getAll() {
            Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(PedidoTaxa.class);
            criteria.addOrder(Order.asc("nome"));
            return criteria.list();
        }
}
