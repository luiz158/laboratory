package exp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.gov.frameworkdemoiselle.validation.annotation.Cpf;
import exp.validator.Url;

@Entity
public class Favorito implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column
	private String descricao;

	@Url
	@Column
	private String url;

	@Cpf
	@Column
	private String cpf;

	public Favorito() {
		super();
	}

	public Favorito(String descricao, String url) {
		this.descricao = descricao;
		this.url = url;
	}
	
	public Favorito(String descricao, String url, String cpf) {
		this.descricao = descricao;
		this.url = url;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
