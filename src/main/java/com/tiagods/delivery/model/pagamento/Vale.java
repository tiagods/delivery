package com.tiagods.delivery.model.pagamento;

import com.tiagods.delivery.model.Pagamento;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "vale")
public class Vale extends Pagamento{
    enum Type{
        Refeicao,Alimentacao
    }
    @Enumerated(value = EnumType.STRING)
    private Type tipo;

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }
}
