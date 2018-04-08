package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.model.produto.ProdutoGenerico;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.PizzaDAO;
import com.tiagods.delivery.repository.interfaces.ProdutoGenericoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutosGenericosImpl extends AbstractRepository<ProdutoGenerico, Long> implements ProdutoGenericoDAO {

    public ProdutosGenericosImpl(EntityManager manager) {
        super(manager);
    }
    @Override
    public ProdutoGenerico save(ProdutoGenerico e) {
        getEntityManager().getTransaction().begin();
        ProdutoGenerico v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(ProdutoGenerico e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public List<ProdutoGenerico> findByNome(String nome) {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(ProdutoGenerico.class);
        if(nome.length()>0) {
            criteria.createAlias("categoria","cat");
            Criterion criterion = Restrictions.ilike("nome", nome, MatchMode.ANYWHERE);
            Criterion criterion2 = Restrictions.ilike("personalizado", nome, MatchMode.ANYWHERE);
            Criterion criterion3 = Restrictions.ilike("cat.nome", nome, MatchMode.ANYWHERE);
            criteria.add(Restrictions.or(criterion,criterion2,criterion3));
        }
        criteria.addOrder(Order.asc("categoria"));
        return criteria.list();
    }
}
