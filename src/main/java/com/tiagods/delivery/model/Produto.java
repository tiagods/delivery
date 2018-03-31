package com.tiagods.delivery.model;

import com.tiagods.delivery.model.produto.Estoque;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "produto_type")
public abstract class Produto implements AbstractEntity, Serializable{
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

	public void setId(long id) {
		this.id = id;
	}

	public String getPersonalizado() {
		return personalizado;
	}

	public void setPersonalizado(String personalizado) {
		this.personalizado = personalizado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ProdutoCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(ProdutoCategoria categoria) {
		this.categoria = categoria;
	}

	public ProdutoUnidade getUnidade() {
		return unidade;
	}

	public void setUnidade(ProdutoUnidade unidade) {
		this.unidade = unidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
