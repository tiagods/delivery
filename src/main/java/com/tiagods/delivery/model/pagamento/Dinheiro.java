package com.tiagods.delivery.model.pagamento;

import com.tiagods.delivery.model.Pagamento;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "dinheiro")
public class Dinheiro extends Pagamento{

}
