package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.Entregador;
import com.tiagods.delivery.model.pedido.Pedido;
import com.tiagods.delivery.model.pedido.PedidoStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@DiscriminatorValue(value = "delivery")
public class PedidoDelivery extends Pedido {
    @ManyToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;
    @Enumerated(value = EnumType.STRING)
    private PedidoStatus status;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar fimEntrega;
    @ManyToOne
    @JoinColumn(name = "taxa_id")
    private PedidoTaxa taxa;
    @Column(name = "valor_taxa")
    private BigDecimal valorTaxa;

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

    public Calendar getFimEntrega() {
        return fimEntrega;
    }
    public void setFimEntrega(Calendar fimEntrega) {
        this.fimEntrega = fimEntrega;
    }

    @Override
    public PedidoTaxa getTaxa() {
        return taxa;
    }

    @Override
    public void setTaxa(PedidoTaxa taxa) {
        this.taxa = taxa;
    }

    @Override
    public BigDecimal getValorTaxa() {
        return valorTaxa;
    }

    @Override
    public void setValorTaxa(BigDecimal valorTaxa) {
        this.valorTaxa = valorTaxa;
    }
}
