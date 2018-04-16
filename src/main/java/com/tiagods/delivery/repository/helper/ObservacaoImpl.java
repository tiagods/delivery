package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.Observacao;
import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.ObservacaoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ObservacaoImpl extends AbstractRepository<Observacao, Long> implements ObservacaoDAO {

    public ObservacaoImpl(EntityManager manager) {
        super(manager);
    }

    @Override
    public Observacao findById(Long id) {
        Query query = getEntityManager().createQuery("from Observacao as a "
                + "LEFT JOIN FETCH a.categorias "
                + "where a.id=:id");
        query.setParameter("id", id);
        Observacao a = (Observacao)query.getSingleResult();
        return a;
    }

    @Override
    public Observacao save(Observacao e) {
        getEntityManager().getTransaction().begin();
        Observacao v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(Observacao e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public List<Observacao> findByNome(String nome) {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Observacao.class);
        criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
        return criteria.list();
    }

    @Override
    public List<Observacao> getAll() {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Observacao.class);
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }
    @Override
    public List<Observacao> findByCategoria(ProdutoCategoria categoria){
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Observacao.class);
        criteria.createAlias("categorias","cat");
        criteria.add(Restrictions.eq("cat", categoria));
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }
}
