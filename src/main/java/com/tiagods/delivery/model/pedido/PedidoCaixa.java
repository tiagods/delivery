package com.tiagods.delivery.model.pedido;

import com.tiagods.delivery.model.pedido.Pedido;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "caixa")
public class PedidoCaixa extends Pedido{

}
