package com.tiagods.delivery.repository.helper;

import java.util.List;

import javax.persistence.EntityManager;

import com.tiagods.delivery.model.Usuario;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.UsuarioDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class UsuariosImpl extends AbstractRepository<Usuario, Long> implements UsuarioDAO {

	public UsuariosImpl(EntityManager manager) {
		super(manager);
	}

	@Override
	public Usuario save(Usuario e) {
		getEntityManager().getTransaction().begin();
		Usuario v = super.save(e);
		getEntityManager().getTransaction().commit();
		return v;
	}

	@Override
	public void remove(Usuario e) {
		getEntityManager().getTransaction().begin();
		super.remove(e);
		getEntityManager().getTransaction().commit();
	}

	public Usuario findByNome(String nome) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.ilike("nome", nome));
		return (Usuario) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getUsuariosByNome(String nome) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Usuario.class);
		criteria.addOrder(Order.asc("nome"));
		return (List<Usuario>) criteria.list();
	}

	@Override
	public Usuario findByLoginAndSenha(String login, String senha) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.ilike("login", login));
		criteria.add(Restrictions.ilike("senha", senha));
		return (Usuario) criteria.uniqueResult();
	}

	public Usuario findByLogin(String login) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.ilike("login", login));
		return (Usuario) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrar(String nome, int ativo, String ordem) {
		Criteria criteria = getEntityManager().unwrap(Session.class).createCriteria(Usuario.class);
		if (!nome.trim().equals("")) {
			Criterion criterion = Restrictions.ilike("nome", nome, MatchMode.ANYWHERE);
			Criterion criterion2 = Restrictions.ilike("pessoa.telefone", nome, MatchMode.ANYWHERE);
			Criterion criterion3 = Restrictions.ilike("pessoa.celular", nome, MatchMode.ANYWHERE);
			Criterion c = Restrictions.or(criterion,criterion2,criterion3);
			criteria.add(c);
			//criteria.add(Restrictions.ilike("nome", nome, MatchMode.START));
		}
		if (ativo == 1 || ativo == 0)
			criteria.add(Restrictions.eq("ativo", ativo));
		criteria.addOrder(Order.asc(ordem));
		return (List<Usuario>) criteria.list();
	}

}
