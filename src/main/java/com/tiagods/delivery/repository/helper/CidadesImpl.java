package com.tiagods.delivery.repository.helper;

import java.util.List;

import javax.persistence.EntityManager;

import com.tiagods.delivery.model.Cidade;
import com.tiagods.delivery.model.Estado;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.CidadeDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class CidadesImpl extends AbstractRepository<Cidade, Long> implements CidadeDAO {
	public CidadesImpl(EntityManager manager) {
		super(manager);
	}
	@Override
	public Cidade findByNome(String nome) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Cidade.class);
		criteria.add(Restrictions.ilike("nome", nome));
		return (Cidade) criteria.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> findByEstado(Estado estado) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Cidade.class);
		criteria.add(Restrictions.eq("estado", estado));
		return (List<Cidade>)criteria.list();
	}

}
