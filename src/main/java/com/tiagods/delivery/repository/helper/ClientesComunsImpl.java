package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.ClienteComum;
import com.tiagods.delivery.model.ClienteRegistrado;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.ClienteComumDAO;
import com.tiagods.delivery.repository.interfaces.ClienteRegistradoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.List;


public class ClientesComunsImpl extends AbstractRepository<ClienteComum, Long> implements ClienteComumDAO {
	public ClientesComunsImpl(EntityManager manager) {
		super(manager);
	}

	@Override
	public ClienteComum save(ClienteComum e) {
		getEntityManager().getTransaction().begin();
		ClienteComum cli = super.save(e);
		getEntityManager().getTransaction().commit();
		return cli;
	}

	@Override
	public void remove(ClienteComum e) {
		getEntityManager().getTransaction().begin();
		super.remove(e);
		getEntityManager().getTransaction().commit();
	}

	@Override
	public ClienteComum findByNome(String nome) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(ClienteComum.class);
		criteria.add(Restrictions.ilike("nome", nome));
		return (ClienteComum) criteria.uniqueResult();
	}
	public List<ClienteComum> filtrar(String nome, String ordem) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(ClienteComum.class);
		if (nome.length()>0) {
			Criterion criterion = Restrictions.ilike("nome", nome, MatchMode.ANYWHERE);
			Criterion criterion2 = Restrictions.ilike("telefone", nome, MatchMode.ANYWHERE);
			Criterion criterion3 = Restrictions.ilike("celular", nome, MatchMode.ANYWHERE);
			Criterion c = Restrictions.or(criterion,criterion2,criterion3);
			criteria.add(c);
		}
		criteria.addOrder(Order.asc(ordem));
		return (List<ClienteComum>) criteria.list();
	}
}
