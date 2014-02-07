package br.gov.serpro.catalogo.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Analise {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Long id;

	@NotEmpty
	private String demandante;
	
	@NotEmpty
	private String detalhamento;

	@NotNull
	@Enumerated(STRING)
	private Situacao situacao;
	
	public String getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDemandante() {
		return demandante;
	}

	public void setDemandante(String demandante) {
		this.demandante = demandante;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

}
