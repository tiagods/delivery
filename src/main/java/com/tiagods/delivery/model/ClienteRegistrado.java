package com.tiagods.delivery.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "registrado")
public class ClienteRegistrado extends Cliente {
    public enum ClienteTipo {
        EMPRESA, PESSOA
    }
    @Embedded
    private PessoaFisica fisico;
    @Embedded
    private PessoaJuridica juridico;
    @Enumerated(value= EnumType.STRING)
    private ClienteTipo tipo;

    public PessoaFisica getFisico() {
        return fisico;
    }

    public void setFisico(PessoaFisica fisico) {
        this.fisico = fisico;
    }

    public PessoaJuridica getJuridico() {
        return juridico;
    }

    public void setJuridico(PessoaJuridica juridico) {
        this.juridico = juridico;
    }

    public ClienteTipo getTipo() {
        return tipo;
    }

    public void setTipo(ClienteTipo tipo) {
        this.tipo = tipo;
    }
}
