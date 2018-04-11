package com.tiagods.delivery.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cidade implements AbstractEntity,Serializable{
	public enum Estado {
		AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MG, MS, MT,
		PA, PB, PE, PI, PR, RJ, RN, RO, RR, RS, SC, SE, SP, TO;
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	@Enumerated(value = EnumType.STRING)
	@Column(length=2)
	private Estado estado;
	@Column(name="cod_extra")
	private String codExtra;
	@Override
	public Number getId() {
		return this.id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the estado
	 */
	public Cidade.Estado getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Cidade.Estado estado) {
		this.estado = estado;
	}
	/**
	 * @return the codExtra
	 */
	public String getCodExtra() {
		return codExtra;
	}
	/**
	 * @param codExtra the codExtra to set
	 */
	public void setCodExtra(String codExtra) {
		this.codExtra = codExtra;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cidade cidade = (Cidade) o;
		return id == cidade.id;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
