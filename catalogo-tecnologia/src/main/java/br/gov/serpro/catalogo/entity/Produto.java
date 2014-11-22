package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "produto_seq")
	@SequenceGenerator(name = "produto_seq", sequenceName = "produto_id_seq", allocationSize=1)
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String versao;
	
	@NotNull
	private Date dataLancamento;
	
	private Date dataDescontinuidade;
	
	@ManyToOne
	private Licenciamento licenciamento;
	
	@ManyToOne
	private Fabricante fabricante;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	@NotEmpty 
	@Column(length=2000)
	private String descricao;
	
	private String produtoAnterior;
	
//	@ElementCollection
//	@CollectionTable(name="plataformas_produtos", joinColumns=@JoinColumn(name="produto_id"))
//	@Column(name="plataformas_suportadas")
//	private List<String> plataformasSuportadas;
	
	@ManyToMany
    @JoinTable(name="produto_plataforma_tecnologica", joinColumns={@JoinColumn(name="produto_id")}, inverseJoinColumns={@JoinColumn(name="plataforma_tecnologica_id")})
	private List<PlataformaTecnologica> plataformasSuportadas;
	
	@ManyToMany
    @JoinTable(name="produto_categoria", joinColumns={@JoinColumn(name="produto_id")}, inverseJoinColumns={@JoinColumn(name="categoria_id")})
	private List<Categoria> categorias;
	
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

	public Licenciamento getLicenciamento() {
		return licenciamento;
	}
	
	public void setLicenciamento(Licenciamento licenciamento) {
		this.licenciamento = licenciamento;
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<PlataformaTecnologica> getPlataformasSuportadas() {
		return plataformasSuportadas;
	}

	public void setPlataformasSuportadas(List<PlataformaTecnologica> plataformasSuportadas) {
		this.plataformasSuportadas = plataformasSuportadas;
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

	public String getProdutoAnterior() {
		return produtoAnterior;
	}

	public void setProdutoAnterior(String produtoAnterior) {
		this.produtoAnterior = produtoAnterior;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataDescontinuidade() {
		return dataDescontinuidade;
	}

	public void setDataDescontinuidade(Date dataDescontinuidade) {
		this.dataDescontinuidade = dataDescontinuidade;
	}
	
}