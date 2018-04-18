package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.produto.Pizza;
import com.tiagods.delivery.model.produto.pizza.PizzaTipo;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.PizzaDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
        if(nome.length()>0){
            criteria.createAlias("categoria","cat");
            Criterion criterion = Restrictions.ilike("nome", nome, MatchMode.ANYWHERE);
            Criterion criterion2 = Restrictions.ilike("personalizado", nome, MatchMode.ANYWHERE);
            criteria.add(Restrictions.or(criterion,criterion2));
        }
        return criteria.list();
    }

    public List<Pizza> filtrarPorTamanho(PizzaTipo tipo) {
        List<Pizza> lista = new ArrayList<>();
        if(tipo!=null) {
            Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Pizza.class);
            String nome = "";
            if(tipo.equals(PizzaTipo.FATIA))
                nome="fatiaHabilitada";
            else if(tipo.equals(PizzaTipo.PEQUENA))
                nome="pequenaHabilitada";
            else if(tipo.equals(PizzaTipo.MEDIA))
                nome="mediaHabilitada";
            else if(tipo.equals(PizzaTipo.GRANDE))
                nome="grandeHabilitada";
            criteria.add(Restrictions.eq(nome,true));
            lista = criteria.list();
        }
        return lista;
    }
}
