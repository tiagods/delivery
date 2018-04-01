package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.ProdutoCategoria;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.ProdutoCategoriaDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutosCategoriasImpl extends AbstractRepository<ProdutoCategoria, Long> implements ProdutoCategoriaDAO {

    public ProdutosCategoriasImpl(EntityManager manager) {
        super(manager);
    }
    @Override
    public ProdutoCategoria save(ProdutoCategoria e) {
        getEntityManager().getTransaction().begin();
        ProdutoCategoria v = super.save(e);
        getEntityManager().getTransaction().commit();
        return v;
    }

    @Override
    public void remove(ProdutoCategoria e) {
        getEntityManager().getTransaction().begin();
        super.remove(e);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public List<ProdutoCategoria> findByNome(String nome) {
        Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(ProdutoCategoria.class);
        criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
        return criteria.list();
    }
}
