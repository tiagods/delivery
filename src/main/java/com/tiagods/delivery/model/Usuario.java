package com.tiagods.delivery.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario implements AbstractEntity, Serializable {
	public enum UsuarioNivel {
		ADMIN("Admin","O administrador é tem permissão sobre todo o sistema"),
		GERENTE("Gerente","O gerente tem permissão para excluir e alterar registros, cadastrar usuários, relatórios de vendas"),
		OPERADOR("Operador","O operador não pode: excluir registros, criar ou alterar contas, ver relatórios financeiros");
		private String descricao;
		private String nome;
		UsuarioNivel(String nome,String descricao) {
			this.nome = nome;
			this.descricao = descricao;
		}
		public String getDescricao() {
			return this.descricao;
		}
		public String getNome() {return nome;}
		@Override
		public String toString() {
			return this.nome;
		}
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int ativo = 1;
	private String login;
	private String senha;
	@Embedded
	private Pessoa pessoa;
	@Embedded
	private PessoaFisica pessoaFisica;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ultimo_acesso")
	private Calendar ultimoAcesso;
	@Enumerated(EnumType.STRING)
	private UsuarioNivel nivel;
	/**
	 * @return the id
	 */
	public Number getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Calendar getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Calendar ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public UsuarioNivel getNivel() {
		return nivel;
	}

	public void setNivel(UsuarioNivel nivel) {
		this.nivel = nivel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.login;
	}
	

}
