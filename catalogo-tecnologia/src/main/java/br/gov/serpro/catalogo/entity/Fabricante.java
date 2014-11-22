package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Fabricante {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "fabricante_seq")
	@SequenceGenerator(name = "fabricante_seq", sequenceName = "fabricante_id_seq", allocationSize=1)
	private Long id;
	
	@NotEmpty
	private String nome;
	
	
	private String representante;
	
	
	private String telefone;
	
	
	private String email;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getRepresentante() {
		return representante;
	}

	
	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
