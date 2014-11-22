package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="usuario")
public class User implements br.gov.frameworkdemoiselle.security.User {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "usuario_seq")
	@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_id_seq", allocationSize=1)
	private Long id;

	@Column(unique = true)
	@NotEmpty
	private String cpf;

	@NotEmpty
	private String name;

	private String email;

	private String telephoneNumber;
	
	private String setor;
	
	@ManyToMany
    @JoinTable(name="usuario_grupo", joinColumns={@JoinColumn(name="usuario_id")}, inverseJoinColumns={@JoinColumn(name="grupo_id")})
	private List<Grupo> grupos;
	
	public String getId() {
		return id == null ? null : id.toString();
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Object getAttribute(Object key) {
		return null;
	}

	@Override
	public void setAttribute(Object key, Object value) {
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public String getSetor() {
		return setor;
	}
	
	public void setSetor(String setor) {
		this.setor = setor;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
}
