package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String versao;
	
	@NotEmpty
	private String release;
	
	@NotEmpty
	private String licenciamento;
	
	@NotEmpty
	private String fabricante;
	
	@NotEmpty
	private String fornecedor;
	
	@NotEmpty
	private String descricao;
	
	private boolean legado;
	
	private boolean atualizacao;

	
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

	
	public String getVersao() {
		return versao;
	}

	
	public void setVersao(String versao) {
		this.versao = versao;
	}

	
	public String getDescricao() {
		return descricao;
	}

	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	
	public String getRelease() {
		return release;
	}


	
	public void setRelease(String release) {
		this.release = release;
	}


	
	public String getLicenciamento() {
		return licenciamento;
	}


	
	public void setLicenciamento(String licenciamento) {
		this.licenciamento = licenciamento;
	}


	
	public String getFabricante() {
		return fabricante;
	}


	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}


	
	public String getFornecedor() {
		return fornecedor;
	}


	
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public boolean isLegado() {
		return legado;
	}

	public void setLegado(boolean legado) {
		this.legado = legado;
	}

	public boolean isAtualizacao() {
		return atualizacao;
	}


	
	public void setAtualizacao(boolean atualizacao) {
		this.atualizacao = atualizacao;
	}
}