package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.Produto;
import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.PizzaDAO;
import com.tiagods.delivery.repository.interfaces.ProdutoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.List;

public class PizzasImpl extends AbstractRepository<Pizza, Long> implements PizzaDAO {

    public PizzasImpl(EntityManager manager) {
        super(manager);
    }
    @Override
    public Pizza save(Pizza e) {
        getEntityManager().getTransaction().begin();
        Pizza v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(Pizza e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public List<Pizza> findByNome(String nome) {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Pizza.class);
        criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
        return criteria.list();
    }
}
