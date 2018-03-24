package com.tiagods.delivery.repository.interfaces;

import java.util.List;

import com.tiagods.delivery.model.Usuario;

public interface UsuarioDAO {
	Usuario save(Usuario usuario);

	void remove(Usuario usuario);

	List<Usuario> getAll();

	List<Usuario> getUsuariosByNome(String nome);

	Usuario findById(Long id);

	Usuario findByLoginAndSenha(String login, String senha);
}
