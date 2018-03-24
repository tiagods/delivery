package com.tiagods.delivery.repository.cep;

import org.json.JSONObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class CEPRepositoryImpl {
	public static void main(String[] args) {
		new CEPRepositoryImpl();
	}
	public CEPRepositoryImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(obter("05765200"));
	}

	private static String URL_API = "http://www.cepaberto.com";
	private static String TOKEN = "a145b4f77a9af90a745af82030c6232f";
	private static String AUTHORIZATION_HEADER = "Authorization";
	private static String PATH = "api/v3/cep";

	public Endereco obter(String cep) {
		try {
			Client clienteHttp = ClientBuilder.newClient();
			WebTarget cepAberto = clienteHttp.target(URL_API).path(PATH).queryParam("cep", cep);
			Response response = cepAberto.request().header(AUTHORIZATION_HEADER, TOKEN).get();
			return fromJson(response.readEntity(String.class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Endereco fromJson(String response) {
		JSONObject jsonObject = new JSONObject(response);
		if (jsonObject.has("cep")) {
			String cep = jsonObject.getString("cep");
			String logradouro = jsonObject.getString("logradouro");
			String cidade = jsonObject.getString("cidade");
			String estado = jsonObject.getString("estado");
			return new Endereco(cep, logradouro, cidade, estado);
		}
		return null;
	}

	public class Endereco {
		private String cep, logradouro, cidade, estado;

		public Endereco(String cep, String logradouro, String cidade, String estado) {
			this.cep = cep;
			this.logradouro = logradouro;
			this.cidade = cidade;
			this.estado = estado;
		}

		/**
		 * @return the cep
		 */
		public String getCep() {
			return cep;
		}

		/**
		 * @param cep
		 *            the cep to set
		 */
		public void setCep(String cep) {
			this.cep = cep;
		}

		/**
		 * @return the logradouro
		 */
		public String getLogradouro() {
			return logradouro;
		}

		/**
		 * @param logradouro
		 *            the logradouro to set
		 */
		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}

		/**
		 * @return the cidade
		 */
		public String getCidade() {
			return cidade;
		}

		/**
		 * @param cidade
		 *            the cidade to set
		 */
		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		/**
		 * @return the estado
		 */
		public String getEstado() {
			return estado;
		}

		/**
		 * @param estado
		 *            the estado to set
		 */
		public void setEstado(String estado) {
			this.estado = estado;
		}

		@Override
		public String toString() {
			return this.logradouro + " - " + this.cep;
		}
	}
}
