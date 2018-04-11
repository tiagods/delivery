package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.ClienteRegistrado;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.ClienteRegistradoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.List;


public class ClientesRegistradosImpl extends AbstractRepository<ClienteRegistrado, Long> implements ClienteRegistradoDAO {
	public ClientesRegistradosImpl(EntityManager manager) {
		super(manager);
	}

	@Override
	public ClienteRegistrado save(ClienteRegistrado e) {
		getEntityManager().getTransaction().begin();
		ClienteRegistrado cli = super.save(e);
		getEntityManager().getTransaction().commit();
		return cli;
	}

	@Override
	public void remove(ClienteRegistrado e) {
		getEntityManager().getTransaction().begin();
		super.remove(e);
		getEntityManager().getTransaction().commit();
	}

	@Override
	public ClienteRegistrado findByNome(String nome) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(ClienteRegistrado.class);
		criteria.add(Restrictions.ilike("nome", nome));
		return (ClienteRegistrado) criteria.uniqueResult();
	}
	public List<ClienteRegistrado> filtrar(String nome, String ordem) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(ClienteRegistrado.class);
		if (nome.length()>0) {
			Criterion criterion = Restrictions.ilike("nome", nome, MatchMode.ANYWHERE);
			Criterion criterion2 = Restrictions.ilike("telefone", nome, MatchMode.ANYWHERE);
			Criterion criterion3 = Restrictions.ilike("celular", nome, MatchMode.ANYWHERE);
			Criterion c = Restrictions.or(criterion,criterion2,criterion3);
			criteria.add(c);
		}
		criteria.addOrder(Order.asc(ordem));
		return (List<ClienteRegistrado>) criteria.list();
	}
}
