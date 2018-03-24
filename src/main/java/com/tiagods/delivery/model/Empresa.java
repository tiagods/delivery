package com.tiagods.delivery.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Empresa implements AbstractEntity,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Embedded
	private Pessoa pessoa;
	@Embedded
	private PessoaJuridica juridico;
	/**
	 * @return the id
	 */
	public Number getId() {
		return id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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

	@Override
	public String toString() {
		return this.pessoa!=null?this.pessoa.getNome():"";
	}
}
