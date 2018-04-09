package com.tiagods.delivery.model.pedido;

public enum PedidoStatus {
    INICIADO("Iniciado"), AGUARDANDO("Aguardardando"), ANDAMENTO("Em andamento"), ENTREGUE("ENTREGUE");

    private String descricao;
    PedidoStatus(String descricao){this.descricao = descricao;}
}
