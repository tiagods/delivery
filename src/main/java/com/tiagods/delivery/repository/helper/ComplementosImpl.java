package com.tiagods.delivery.repository.helper;


import com.tiagods.delivery.model.Complemento;
import com.tiagods.delivery.model.Observacao;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.ComplementoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ComplementosImpl extends AbstractRepository<Complemento, Long> implements ComplementoDAO {

    public ComplementosImpl(EntityManager manager) {
        super(manager);
    }

    @Override
    public Complemento findById(Long id) {
        Query query = getEntityManager().createQuery("from Complemento as a "
                + "LEFT JOIN FETCH a.categorias "
                + "where a.id=:id");
        query.setParameter("id", id);
        Complemento a = (Complemento)query.getSingleResult();
        return a;
    }

    @Override
    public Complemento save(Complemento e) {
        getEntityManager().getTransaction().begin();
        Complemento v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(Complemento e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public List<Complemento> findByNome(String nome) {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Complemento.class);
        criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
        return criteria.list();
    }
    @Override
    public List<Complemento> getAll() {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Complemento.class);
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }
    @Override
    public List<Complemento> findByCategoria(ProdutoCategoria categoria) {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Complemento.class);
        criteria.createAlias("categorias", "cat");
        criteria.add(Restrictions.eq("cat", categoria));
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }
}
