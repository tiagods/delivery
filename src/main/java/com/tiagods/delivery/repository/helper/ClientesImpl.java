package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.Cliente;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.ClienteDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
			Criterion criterion = Restrictions.ilike("nome", nome, MatchMode.ANYWHERE);
			Criterion criterion2 = Restrictions.ilike("telefone", nome, MatchMode.ANYWHERE);
			Criterion criterion3 = Restrictions.ilike("celular", nome, MatchMode.ANYWHERE);
			Criterion c = Restrictions.or(criterion,criterion2,criterion3);
			criteria.add(c);
		}
		criteria.addOrder(Order.asc(ordem));
		return (List<Cliente>) criteria.list();
	}
	public Cliente procurarTelefone(String telefone) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Cliente.class);
		if (telefone.length()>0) {
			Criterion criterion2 = Restrictions.eq("telefone", telefone);
			Criterion criterion3 = Restrictions.eq("celular", telefone);
			Criterion c = Restrictions.or(criterion2,criterion3);
			criteria.add(c);
		}
		return (Cliente)criteria.uniqueResult();
	}
}
