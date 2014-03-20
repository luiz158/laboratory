package br.gov.serpro.catalogo.entity;

import javax.persistence.Entity;

@Entity
public class Declinio extends Fase{

	public Declinio() {
		this.setFase(FaseEnum.DECLINIO);
	}
		
	private Boolean novaAnaliseRiscos;
	

	public Boolean getNovaAnaliseRiscos() {
		return novaAnaliseRiscos;
	}
	public void setNovaAnaliseRiscos(Boolean novaAnaliseRiscos) {
		this.novaAnaliseRiscos = novaAnaliseRiscos;
	}


}
