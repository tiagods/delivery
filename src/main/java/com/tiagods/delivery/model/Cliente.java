package com.tiagods.delivery.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente implements AbstractEntity,Serializable{
	public enum ClienteTipo {
		EMPRESA, PESSOA
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Enumerated(value=EnumType.STRING)
	private ClienteTipo tipo;
	@Embedded
	private Pessoa pessoa;
	@Embedded
	private PessoaFisica fisico;
	@Embedded
	private PessoaJuridica juridico;
	/**
	 * @return the id
	 */
	public Number getId() {
		return id;
	}
	/**
	 * @return the tipo
	 */
	public ClienteTipo getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(ClienteTipo tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the fisico
	 */
	public PessoaFisica getFisico() {
		return fisico;
	}
	/**
	 * @param fisico the fisico to set
	 */
	public void setFisico(PessoaFisica fisico) {
		this.fisico = fisico;
	}
	/**
	 * @return the juridico
	 */
	public PessoaJuridica getJuridico() {
		return juridico;
	}
	/**
	 * @param juridico the juridico to set
	 */
	public void setJuridico(PessoaJuridica juridico) {
		this.juridico = juridico;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
}
