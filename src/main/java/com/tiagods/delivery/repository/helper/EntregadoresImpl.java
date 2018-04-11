package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.Entregador;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.EntregadorDAO;
import com.tiagods.delivery.repository.interfaces.ObservacaoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntregadoresImpl extends AbstractRepository<Entregador, Long> implements EntregadorDAO {

    public EntregadoresImpl(EntityManager manager) {
        super(manager);
    }

    @Override
    public Entregador save(Entregador e) {
        getEntityManager().getTransaction().begin();
        Entregador v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(Entregador e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }
    @Override
    public Entregador findByNome(String nome) {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Entregador.class);
        criteria.add(Restrictions.ilike("nome", nome));
        return (Entregador)criteria.uniqueResult();
    }
    @Override
    public List<Entregador> filtrarAtivos() {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Entregador.class);
        criteria.add(Restrictions.eq("ativo", true));
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }


    @Override
    public List<Entregador> getAll() {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Entregador.class);
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }
}
