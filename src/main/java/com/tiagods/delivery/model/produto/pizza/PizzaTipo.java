package com.tiagods.delivery.model.produto.pizza;

public enum PizzaTipo {
    FATIA(1),PEQUENA(2),MEDIA(3),GRANDE(4);

    private int codigo;

    PizzaTipo(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigo() {
        return codigo;
    }
}
