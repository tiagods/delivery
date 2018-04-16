package com.tiagods.delivery.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Cliente extends Pessoa implements AbstractEntity,Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	public Number getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return super.getNome();
	}
}
