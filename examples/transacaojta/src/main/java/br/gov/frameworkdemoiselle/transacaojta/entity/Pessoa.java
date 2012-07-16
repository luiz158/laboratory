package br.gov.frameworkdemoiselle.transacaojta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pessoa {
	
	@Id
	private String cpf;

	private String nome;
	
	public Pessoa() {
		
	}
	
	public Pessoa(String pCpf, String pNome) {
		cpf = pCpf;
		nome = pNome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
