package br.gov.serpro.jpa_jdbc_sample.domain;

import java.io.Serializable;

public class Log implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String datahora;
	private String operacao;
	private String registro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatahora() {
		return datahora;
	}

	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

}
