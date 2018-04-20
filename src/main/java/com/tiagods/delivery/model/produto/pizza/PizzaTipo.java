package com.tiagods.delivery.model.produto.pizza;

public enum PizzaTipo {
    FATIA(1,"F"),PEQUENA(2,"P"),MEDIA(3,"M"),GRANDE(4,"G");

    private int codigo;
    private String descricao;

    PizzaTipo(int codigo,String descricao) {
        this.codigo = codigo;
        this.descricao=descricao;
    }
    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
