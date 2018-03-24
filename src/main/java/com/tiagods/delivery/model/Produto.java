package com.tiagods.delivery.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tiagods.delivery.model.produto.Estoque;
import com.tiagods.delivery.model.produto.Pizza;

@Entity
public class Produto implements AbstractEntity, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String personalizado;
	private String nome;
	@Enumerated(value=EnumType.STRING)
	private ProdutoCategoria categoria;
	@Enumerated(value=EnumType.STRING)
	private ProdutoUnidade unidade;
	private String descricao;
	private String foto;
	@Embedded
	private Pizza pizza;
	@Column(name="controle_estoque")
	private int controleEstoque;
	@Embedded
	private Estoque estoque;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Calendar criadoEm;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "criado_por_id")
	private Usuario criadoPor;
	@Override
	public Number getId() {
		return this.id;
	}
	/**
	 * @return the personalizado
	 */
	public String getPersonalizado() {
		return personalizado;
	}
	/**
	 * @param personalizado the personalizado to set
	 */
	public void setPersonalizado(String personalizado) {
		this.personalizado = personalizado;
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
	 * @return the categoria
	 */
	public ProdutoCategoria getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(ProdutoCategoria categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return the unidade
	 */
	public ProdutoUnidade getUnidade() {
		return unidade;
	}
	/**
	 * @param unidade the unidade to set
	 */
	public void setUnidade(ProdutoUnidade unidade) {
		this.unidade = unidade;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}
	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	/**
	 * @return the pizza
	 */
	public Pizza getPizza() {
		return pizza;
	}
	/**
	 * @param pizza the pizza to set
	 */
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	/**
	 * @return the estoque
	 */
	public Estoque getEstoque() {
		return estoque;
	}
	/**
	 * @param estoque the estoque to set
	 */
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	/**
	 * @return the criadoEm
	 */
	public Calendar getCriadoEm() {
		return criadoEm;
	}
	/**
	 * @param criadoEm the criadoEm to set
	 */
	public void setCriadoEm(Calendar criadoEm) {
		this.criadoEm = criadoEm;
	}
	/**
	 * @return the criadoPor
	 */
	public Usuario getCriadoPor() {
		return criadoPor;
	}
	/**
	 * @param criadoPor the criadoPor to set
	 */
	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	
}
