package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.pedido.PedidoCaixa;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.PedidoCaixaDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PedidosCaixaImpl extends AbstractRepository<PedidoCaixa, Long> implements PedidoCaixaDAO {

    public PedidosCaixaImpl(EntityManager manager) {
        super(manager);
    }

    @Override
    public PedidoCaixa findById(Long id) {
        Query query = getEntityManager().createQuery("from PedidoCaixa as a "
                + "LEFT JOIN FETCH a.produtos, LEFT JOIN FETCH a.pagamentos "
                + "where a.id=:id");
        query.setParameter("id", id);
        PedidoCaixa a = (PedidoCaixa)query.getSingleResult();
        return a;
    }

    @Override
    public PedidoCaixa save(PedidoCaixa e) {
        getEntityManager().getTransaction().begin();
        PedidoCaixa v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(PedidoCaixa e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }
    @Override
    public List<PedidoCaixa> getAll() {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(PedidoCaixa.class);
        criteria.addOrder(Order.desc("criadoEm"));
        return criteria.list();
    }
}
