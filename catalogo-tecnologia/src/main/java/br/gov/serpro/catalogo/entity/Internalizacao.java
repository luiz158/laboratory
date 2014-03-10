package br.gov.serpro.catalogo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Internalizacao extends Fase {

	public Internalizacao() {
		this.setFase(FaseEnum.INTERNALIZACAO);
	}

	// Restricoes de uso
	private Boolean restricao;
	private String restricaoQuemPode;
	private String restricaoJustificativa;

	// Aquisições para a internalização
	private Boolean aquisicaoNecessaria;
	private String aquisicaoItens;
	private String aquisicaoResultado;
	@OneToMany
	private List<Documento> aquisicaoContratos;

	// capacitacoes
	private Boolean capacitacao;
	private String capacitacaoNecessarias;
	private String capacitacaoRealizadas;
	
	// capacitacoes
	private Boolean analiseDeRiscos;
	private String analiseDeRiscosJustificativa;
	public Boolean getRestricao() {
		return restricao;
	}
	public void setRestricao(Boolean restricao) {
		this.restricao = restricao;
	}
	public String getRestricaoQuemPode() {
		return restricaoQuemPode;
	}
	public void setRestricaoQuemPode(String restricaoQuemPode) {
		this.restricaoQuemPode = restricaoQuemPode;
	}
	public String getRestricaoJustificativa() {
		return restricaoJustificativa;
	}
	public void setRestricaoJustificativa(String restricaoJustificativa) {
		this.restricaoJustificativa = restricaoJustificativa;
	}
	public Boolean getAquisicaoNecessaria() {
		return aquisicaoNecessaria;
	}
	public void setAquisicaoNecessaria(Boolean aquisicaoNecessaria) {
		this.aquisicaoNecessaria = aquisicaoNecessaria;
	}
	public String getAquisicaoItens() {
		return aquisicaoItens;
	}
	public void setAquisicaoItens(String aquisicaoItens) {
		this.aquisicaoItens = aquisicaoItens;
	}
	public String getAquisicaoResultado() {
		return aquisicaoResultado;
	}
	public void setAquisicaoResultado(String aquisicaoResultado) {
		this.aquisicaoResultado = aquisicaoResultado;
	}
	public List<Documento> getAquisicaoContratos() {
		return aquisicaoContratos;
	}
	public void setAquisicaoContratos(List<Documento> aquisicaoContratos) {
		this.aquisicaoContratos = aquisicaoContratos;
	}
	public Boolean getCapacitacao() {
		return capacitacao;
	}
	public void setCapacitacao(Boolean capacitacao) {
		this.capacitacao = capacitacao;
	}
	public String getCapacitacaoNecessarias() {
		return capacitacaoNecessarias;
	}
	public void setCapacitacaoNecessarias(String capacitacaoNecessarias) {
		this.capacitacaoNecessarias = capacitacaoNecessarias;
	}
	public String getCapacitacaoRealizadas() {
		return capacitacaoRealizadas;
	}
	public void setCapacitacaoRealizadas(String capacitacaoRealizadas) {
		this.capacitacaoRealizadas = capacitacaoRealizadas;
	}
	public Boolean getAnaliseDeRiscos() {
		return analiseDeRiscos;
	}
	public void setAnaliseDeRiscos(Boolean analiseDeRiscos) {
		this.analiseDeRiscos = analiseDeRiscos;
	}
	public String getAnaliseDeRiscosJustificativa() {
		return analiseDeRiscosJustificativa;
	}
	public void setAnaliseDeRiscosJustificativa(String analiseDeRiscosJustificativa) {
		this.analiseDeRiscosJustificativa = analiseDeRiscosJustificativa;
	}
	
	

}
