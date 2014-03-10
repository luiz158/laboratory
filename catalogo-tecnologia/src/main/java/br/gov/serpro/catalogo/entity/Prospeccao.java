package br.gov.serpro.catalogo.entity;

import javax.persistence.Entity;

@Entity
public class Prospeccao extends Fase{

	public Prospeccao() {
		this.setFase(FaseEnum.PROSPECCAO);
	}
		
	private String testes;
		
	public String getTestes() {
		return testes;
	}
	public void setTestes(String testes) {
		this.testes = testes;
	}

}
