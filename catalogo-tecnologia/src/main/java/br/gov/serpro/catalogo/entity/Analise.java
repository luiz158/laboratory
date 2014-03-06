package br.gov.serpro.catalogo.entity;

import javax.persistence.Entity;

@Entity
public class Analise extends Fase {

	public Analise() {
		this.setFase(FaseEnum.ANALISE);
	}
	
	private String demandante;

	public String getDemandante() {
		return demandante;
	}

	public void setDemandante(String demandante) {
		this.demandante = demandante;
	}
	
	

}
