package br.gov.serpro.catalogo.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Analise extends Fase {

	public Analise() {
		this.setFase(FaseEnum.ANALISE);
	}
	
	private String demandanteUnidade;

	@ManyToOne 
	private User demandanteRepresentante;

	public String getDemandanteUnidade() {
		return demandanteUnidade;
	}

	public void setDemandanteUnidade(String demandanteUnidade) {
		this.demandanteUnidade = demandanteUnidade;
	}

	public User getDemandanteRepresentante() {
		return demandanteRepresentante;
	}

	public void setDemandanteRepresentante(User demandanteRepresentante) {
		this.demandanteRepresentante = demandanteRepresentante;
	}
	
	
	

}
