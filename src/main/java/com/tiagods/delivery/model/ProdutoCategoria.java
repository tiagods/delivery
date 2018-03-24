package com.tiagods.delivery.model;

public enum ProdutoCategoria {
	CERVEJAS("Cervejas"), PIZZAS("Pizzas"), LANCHES("Lanches"),
	PORCOES("Poções"), DOCES_E_SOBREMESAS("Doces e Sobremesas"), 
	REFEICOES("Refeições"),REFRIGERANTES("Refrigerantes"), SUCOS("Sucos");
	
	private String descricao;
	
	ProdutoCategoria(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
