package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({ @NamedQuery(name = FaseMembro.MEMBROS_DA_FASE, query = "select fp from FaseMembro fp where (fp.fase.id = :fase)") })
public class FaseMembro {
	
	public static final String MEMBROS_DA_FASE = "MEMBROS_DA_FASE";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Long id;
	
	@NotNull
	@ManyToOne
	private Fase fase;
	
	@NotNull
	@ManyToOne
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
}
