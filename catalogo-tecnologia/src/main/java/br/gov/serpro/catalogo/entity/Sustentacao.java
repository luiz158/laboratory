package br.gov.serpro.catalogo.entity;

import javax.persistence.Entity;

@Entity
public class Sustentacao extends Fase{

	public Sustentacao() {
		this.setFase(FaseEnum.SUSTENTACAO);
	}
		


}
