package com.tiagods.delivery.model.pagamento;

public enum ModalidadePagamento {
    CHEQUE("Cheque"),CREDITO("Crédito"),DEBITO("Débito"),DINHEIRO("Dinheiro"),VALE("Vale");

    private String descricao;
    ModalidadePagamento(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
    @Override
    public String toString() {
        return getDescricao();
    }
}
