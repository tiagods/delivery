package com.tiagods.delivery.model.pagamento;

import com.tiagods.delivery.model.Pagamento;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "cheque")
public class Cheque extends Pagamento{

}
