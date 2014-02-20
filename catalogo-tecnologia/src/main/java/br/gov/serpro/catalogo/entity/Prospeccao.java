package br.gov.serpro.catalogo.entity;

import javax.persistence.Entity;

@Entity
public class Prospeccao extends Fase{

	public Prospeccao() {
		this.setFase(FaseEnum.PROSPECCAO);
	}
		
	private String testes;
	
	private String conclusao;
	
	public String getTestes() {
		return testes;
	}
	public void setTestes(String testes) {
		this.testes = testes;
	}
	public String getConclusao() {
		return conclusao;
	}
	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}
	

}
