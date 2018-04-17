package com.tiagods.delivery.model.pagamento;

public enum ValeBandeira {
    SODEXO("Sodexo"),TICKET("Ticket Restaurante"),VA("Vale Alimentação"),VR("Vale Refeição"),;
    private String descricao;
    ValeBandeira(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
