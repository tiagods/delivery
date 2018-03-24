package com.tiagods.delivery.model;

public enum UsuarioNivel {
	ADMIN("Admin"), GERENTE("Gerente"), OPERADOR("Operador");

	private String descricao;

	UsuarioNivel(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
