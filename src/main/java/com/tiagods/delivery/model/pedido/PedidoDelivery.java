package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.Entregador;
import com.tiagods.delivery.model.Pedido;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@DiscriminatorValue(value = "delivery")
public class PedidoDelivery extends Pedido {
    public enum PedidoStatus {
        INICIADO("Iniciado"), AGUARDANDO("Aguardardando"), ANDAMENTO("Em Andamento"), ENTREGUE("Entregue");
        private String descricao;
        PedidoStatus(String descricao){this.descricao = descricao;}
        public String getDescricao() {
            return descricao;
        }
        @Override
        public String toString() {
            return super.toString();
        }
    }
    @ManyToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;
    @Enumerated(value = EnumType.STRING)
    private PedidoStatus status;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "data_entrega")
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

    public PedidoTaxa getTaxa() {
        return taxa;
    }

    public void setTaxa(PedidoTaxa taxa) {
        this.taxa = taxa;
    }

    public BigDecimal getValorTaxa() {
        return valorTaxa;
    }

    public void setValorTaxa(BigDecimal valorTaxa) {
        this.valorTaxa = valorTaxa;
    }
}
