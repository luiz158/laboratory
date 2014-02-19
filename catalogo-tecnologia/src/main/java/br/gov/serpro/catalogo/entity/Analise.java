package br.gov.serpro.catalogo.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQueries({  
	  @NamedQuery(
			  name = Analise.ANALISES_POR_FASE, 
			  query = "select a from Analise a")
	})
public class Analise {

	public static final String ANALISES_POR_FASE = "ANALISES_POR_FASE";

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
	
	private String justificativa;
	
	//@NotNull
	private Date dataAnalise;
	
	private Date dataFinalizacao;
	
	@NotEmpty
	private String gestorAnalise;
	
	@NotEmpty
	private String areaGestorAnalise;
	
	//Proxima Fase
	
	private String proximaFase;
	
	private String justificativaProximaFase;
	
	private String gestorProximaFase;
	
	private String areaGestorProximaFase;
		
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

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public String getGestorProximaFase() {
		return gestorProximaFase;
	}

	public void setGestorProximaFase(String gestorProximaFase) {
		this.gestorProximaFase = gestorProximaFase;
	}

	public String getAreaGestorProximaFase() {
		return areaGestorProximaFase;
	}

	public void setAreaGestorProximaFase(String areaGestorProximaFase) {
		this.areaGestorProximaFase = areaGestorProximaFase;
	}

	public String getJustificativaProximaFase() {
		return justificativaProximaFase;
	}

	public void setJustificativaProximaFase(String justificativaProximaFase) {
		this.justificativaProximaFase = justificativaProximaFase;
	}

	public String getProximaFase() {
		return proximaFase;
	}

	public void setProximaFase(String proximaFase) {
		this.proximaFase = proximaFase;
	}

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

}
