package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({ @NamedQuery(name = FaseProduto.PRODUTOS_DA_FASE, query = "select fp from FaseProduto fp where (fp.fase.id = :fase)") })
public class FaseProduto {
	
	public static final String PRODUTOS_DA_FASE = "PRODUTOS_DA_FASE";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "fase_produto_seq")
	@SequenceGenerator(name = "fase_produto_seq", sequenceName = "fase_produto_id_seq", allocationSize=1)
	private Long id;
	
	@NotNull
	@ManyToOne
	private Fase fase;
	
	@NotNull
	@ManyToOne
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	
}
