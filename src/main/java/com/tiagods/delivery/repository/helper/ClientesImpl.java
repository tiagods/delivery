package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.Cidade;
import com.tiagods.delivery.model.Cliente;
import com.tiagods.delivery.model.Estado;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.CidadeDAO;
import com.tiagods.delivery.repository.interfaces.ClienteDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import javax.persistence.EntityManager;
import java.util.List;


public class ClientesImpl extends AbstractRepository<Cliente, Long> implements ClienteDAO {
	public ClientesImpl(EntityManager manager) {
		super(manager);
	}

	@Override
	public Cliente save(Cliente e) {
		getEntityManager().getTransaction().begin();
		Cliente cli = super.save(e);
		getEntityManager().getTransaction().commit();
		return cli;
	}

	@Override
	public void remove(Cliente e) {
		getEntityManager().getTransaction().begin();
		super.remove(e);
		getEntityManager().getTransaction().commit();
	}
	@Override
	public Cliente findByNome(String nome) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Cliente.class);
		criteria.add(Restrictions.ilike("nome", nome));
		return (Cliente) criteria.uniqueResult();
	}
	public List<Cliente> filtrar(String nome, String ordem) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Cliente.class);
		if (nome.length()>0) {
			Criterion criterion = Restrictions.ilike("pessoa.nome", nome, MatchMode.ANYWHERE);
			Criterion criterion2 = Restrictions.ilike("pessoa.telefone", nome, MatchMode.ANYWHERE);
			Criterion criterion3 = Restrictions.ilike("pessoa.celular", nome, MatchMode.ANYWHERE);
			Criterion c = Restrictions.or(criterion,criterion2,criterion3);
			criteria.add(c);
		}
		criteria.addOrder(Order.asc("pessoa."+ordem));
		return (List<Cliente>) criteria.list();
	}
}
