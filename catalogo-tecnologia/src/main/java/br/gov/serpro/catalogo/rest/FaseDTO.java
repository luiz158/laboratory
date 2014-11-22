package br.gov.serpro.catalogo.rest;

import java.util.Date;

import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.OrigemDemanda;

public class FaseDTO{
	/* Campos de pesquisa */
	private Long id;
	private String identificador;
	private String palavraChave;
	private Integer fase;
	private OrigemDemanda origemReferencia;
	private String codigoReferencia;
	private Integer finalizada;
	private Date finalizacaoApartir;
	private Date finalizacaoAte;	
	//private Usuario gestor;
	
	/* Campos de exibição */
	private String unidadeGestora;
	private Date dataRegistro;
	private Date dataFinalizacao;
	private String objetivo;
	
	public FaseDTO() {
		
	}
	
	public FaseDTO(Fase a) {
		this.setId(a.getId());
		this.setFase(a.getFase().ordinal()+1);
		this.setDataFinalizacao(a.getDataFinalizacao());
		this.setDataRegistro(a.getDataRealizacao());
		this.setUnidadeGestora(a.getUnidadeGestora());
		this.setObjetivo(a.getObjetivo());
		this.setOrigemReferencia(a.getOrigemReferencia());
		this.setCodigoReferencia(a.getCodigoReferencia());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPalavraChave() {
		return palavraChave;
	}
	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	public Integer getFase() {
		return fase;
	}
	public void setFase(Integer fase) {
		this.fase = fase;
	}
	public OrigemDemanda getOrigemReferencia() {
		return origemReferencia;
	}
	public void setOrigemReferencia(OrigemDemanda origemReferencia) {
		this.origemReferencia = origemReferencia;
	}
	public String getCodigoReferencia() {
		return codigoReferencia;
	}
	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	public Integer getFinalizada() {
		return finalizada;
	}
	public void setFinalizada(Integer finalizada) {
		this.finalizada = finalizada;
	}
	public Date getFinalizacaoApartir() {
		return finalizacaoApartir;
	}
	public void setFinalizacaoApartir(Date finalizacaoApartir) {
		this.finalizacaoApartir = finalizacaoApartir;
	}
	public Date getFinalizacaoAte() {
		return finalizacaoAte;
	}
	public void setFinalizacaoAte(Date finalizacaoAte) {
		this.finalizacaoAte = finalizacaoAte;
	}
	

	public String getUnidadeGestora() {
		return unidadeGestora;
	}
	public void setUnidadeGestora(String gestorArea) {
		this.unidadeGestora = gestorArea;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	
	public String getIdentificador() {
		return identificador;
	}

	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
}