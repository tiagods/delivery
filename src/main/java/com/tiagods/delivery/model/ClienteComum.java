package com.tiagods.delivery.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "comum")
/*
Cliente que não se cadastrou para participar de promoções ...
 */
public class ClienteComum extends Cliente {

}
