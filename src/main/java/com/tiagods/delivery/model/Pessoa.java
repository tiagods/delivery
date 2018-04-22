package com.tiagods.delivery.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

@MappedSuperclass
@Embeddable
public class Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome="";
	private String email="";
	private String telefone="";
	private String celular="";
	private String cep="";
	private String endereco="";
	private String numero="";
	private String bairro="";
	private String complemento="";
	@ManyToOne
	@JoinColumn(name="cidade_id")
	private Cidade cidade;
	@Enumerated(value =EnumType.STRING)
	private Cidade.Estado estado;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Calendar criadoEm;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "criado_por_id")
	private Usuario criadoPor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cidade.Estado getEstado() {
		return estado;
	}

	public void setEstado(Cidade.Estado estado) {
		this.estado = estado;
	}

	public Calendar getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Calendar criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}
}
