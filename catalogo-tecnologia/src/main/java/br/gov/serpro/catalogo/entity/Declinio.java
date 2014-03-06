package br.gov.serpro.catalogo.entity;

import javax.persistence.Entity;

@Entity
public class Declinio extends Fase{

	public Declinio() {
		this.setFase(FaseEnum.DECLINIO);
	}
		
	private Boolean novaAnaliseRiscos;
	
	private String conclusao;
	

	public Boolean getNovaAnaliseRiscos() {
		return novaAnaliseRiscos;
	}
	public void setNovaAnaliseRiscos(Boolean novaAnaliseRiscos) {
		this.novaAnaliseRiscos = novaAnaliseRiscos;
	}
	public String getConclusao() {
		return conclusao;
	}
	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

}
