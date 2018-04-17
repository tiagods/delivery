package com.tiagods.delivery.model.pagamento;

import com.intellij.util.Options;

public enum CartaoBandeira {
    VISA("Visa"),MASTERCARD("MasterCard"),AMEX("American Express"),
    ELO("Elo"),DINERS("Diners Club"),HIPERCARD("Hipercard"),OUTRO("Outro");

    private String descricao;
    CartaoBandeira(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }


}
