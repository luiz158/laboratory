package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints= {
	@UniqueConstraint(columnNames = {"fase_id", "user_id"})
}) 
@NamedQueries({ @NamedQuery(name = FaseMembro.MEMBROS_DA_FASE, query = "select fp from FaseMembro fp where (fp.fase.id = :fase)") })
public class FaseMembro {
	
	public static final String MEMBROS_DA_FASE = "MEMBROS_DA_FASE";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "fase_membro_seq")
	@SequenceGenerator(name = "fase_membro_seq", sequenceName = "fase_membro_id_seq", allocationSize=1)
	private Long id;
	
	@NotNull
	@ManyToOne
	private Fase fase;
	
	@NotNull
	@ManyToOne
	private User user;
	
	public FaseMembro() {
		// TODO Auto-generated constructor stub
	}

	public FaseMembro(Fase proximafase, User proximaFaseLider) {
		this.fase = proximafase;
		this.user = proximaFaseLider;
	}

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
