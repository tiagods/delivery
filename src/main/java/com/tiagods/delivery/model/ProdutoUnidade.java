package com.tiagods.delivery.model;

public enum ProdutoUnidade {
	DUZIA("Dúzia(s)"), GRAMA("Grama(s)"),
	LITRO("Litro"),METRO("Metros(s)"),METROS_CUBICOS("Metro(s) Cúbico(s)"),
	METROS_QUADRADOS("Metro(s) Quadrados(s)"),MILHEIRO("Milheiro"),
	PECA("Peça"),QUILOGRAMA("Quilograma"),TONELADA("Tonelada"),UNIDADE("Unidade");
	
	private String descricao;
	
	ProdutoUnidade(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	
}
