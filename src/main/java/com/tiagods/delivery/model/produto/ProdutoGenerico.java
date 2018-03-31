package com.tiagods.delivery.model.produto;

import com.tiagods.delivery.model.Produto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="generico")
public class ProdutoGenerico extends Produto {
}
