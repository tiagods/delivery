package com.tiagods.delivery.model.pagamento;

import com.tiagods.delivery.model.Pagamento;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "debito")
public class Debito extends Pagamento{
    @Enumerated(value = EnumType.STRING)
    private CartaoBandeira bandeira;

    public CartaoBandeira getBandeira() {
        return bandeira;
    }

    public void setBandeira(CartaoBandeira bandeira) {
        this.bandeira = bandeira;
    }
}
