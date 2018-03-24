package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.Cidade;
import com.tiagods.delivery.model.Estado;

import java.util.List;

public interface CidadeDAO {
	Cidade save(Cidade cidade);
	void remove(Cidade cidade);
	List<Cidade> getAll();
	Cidade findById(Long id);
	Cidade findByNome(String nome);
	List<Cidade> findByEstado(Estado estado);
}
