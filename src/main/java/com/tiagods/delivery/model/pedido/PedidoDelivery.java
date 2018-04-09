package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.Entregador;
import com.tiagods.delivery.model.pedido.Pedido;
import com.tiagods.delivery.model.pedido.PedidoStatus;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "delivery")
public class PedidoDelivery extends Pedido {
    @ManyToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;
    @Enumerated(value = EnumType.STRING)
    private PedidoStatus status;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
