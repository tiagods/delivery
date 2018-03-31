package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.Produto;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.ProdutoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutosImpl extends AbstractRepository<Produto, Long> implements ProdutoDAO {

    public ProdutosImpl(EntityManager manager) {
        super(manager);
    }
    @Override
    public Produto save(Produto e) {
        getEntityManager().getTransaction().begin();
        Produto v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(Produto e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public List<Produto> findByNome(String nome) {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Produto.class);
        criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
        return criteria.list();
    }
}
