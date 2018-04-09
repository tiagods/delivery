package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.pedido.PedidoDelivery;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.PedidoDeliveryDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PedidosDeliveryImpl extends AbstractRepository<PedidoDelivery, Long> implements PedidoDeliveryDAO {

    public PedidosDeliveryImpl(EntityManager manager) {
        super(manager);
    }

    @Override
    public PedidoDelivery findById(Long id) {
        Query query = getEntityManager().createQuery("from PedidoDelivery as a "
                + "LEFT JOIN FETCH a.produtos, LEFT JOIN FETCH a.pagamentos "
                + "where a.id=:id");
        query.setParameter("id", id);
        PedidoDelivery a = (PedidoDelivery)query.getSingleResult();
        return a;
    }

    @Override
    public PedidoDelivery save(PedidoDelivery e) {
        getEntityManager().getTransaction().begin();
        PedidoDelivery v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(PedidoDelivery e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }
    @Override
    public List<PedidoDelivery> getAll() {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(PedidoDelivery.class);
        criteria.addOrder(Order.desc("criadoEm"));
        return criteria.list();
    }
}
