package com.tiagods.delivery.repository.helper;

import com.tiagods.delivery.model.Cliente;
import com.tiagods.delivery.model.Empresa;
import com.tiagods.delivery.repository.AbstractRepository;
import com.tiagods.delivery.repository.interfaces.ClienteDAO;
import com.tiagods.delivery.repository.interfaces.EmpresaDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;


public class EmpresasImpl extends AbstractRepository<Empresa, Long> implements EmpresaDAO {
	public EmpresasImpl(EntityManager manager) {
		super(manager);
	}
	@Override
	public Empresa save(Empresa e) {
		getEntityManager().getTransaction().begin();
		Empresa i = super.save(e);
		getEntityManager().getTransaction().commit();
		return i;
	}

}
