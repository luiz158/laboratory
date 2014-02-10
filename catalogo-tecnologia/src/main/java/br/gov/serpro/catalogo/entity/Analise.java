package br.gov.serpro.catalogo.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

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
	
	private String codigoReferencia;
	
	private String origemReferencia;
	
	//@NotNull
	private Date dataAnalise;
	
	@NotEmpty
	private String gestorAnalise;
	
	@NotEmpty
	private String areaGestorAnalise;
	
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

	
	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	
	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}

	
	public String getOrigemReferencia() {
		return origemReferencia;
	}

	
	public void setOrigemReferencia(String origemReferencia) {
		this.origemReferencia = origemReferencia;
	}

	
	public Date getDataAnalise() {
		return dataAnalise;
	}

	
	public void setDataAnalise(Date dataAnalise) {
		this.dataAnalise = dataAnalise;
	}

	
	public String getGestorAnalise() {
		return gestorAnalise;
	}

	
	public void setGestorAnalise(String gestorAnalise) {
		this.gestorAnalise = gestorAnalise;
	}

	
	public String getAreaGestorAnalise() {
		return areaGestorAnalise;
	}

	
	public void setAreaGestorAnalise(String areaGestorAnalise) {
		this.areaGestorAnalise = areaGestorAnalise;
	}

}
