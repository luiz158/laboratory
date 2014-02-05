package br.gov.frameworkdemoiselle.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Clóvis Lemes Ferreira Júnior
 */
@Entity
@Table
@Cacheable
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuario.findByAminesia", query = "SELECT u FROM Usuario u WHERE u.aminesia = :aminesia"),
    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 330085550479838859L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @NotNull
    private Short papel = 0;

    @Size(min=3, max = 50)
    @Column(length = 50)
    @NotNull
    private String nome;

    @Size(min=5, max = 64)
    @Column(length = 64)
    @NotNull
    private String email;

    @Size(min=3, max = 64)
    @Column(length = 64)
    @NotNull
    private String aminesia;

    @Size(min=3, max = 30)
    @Column(length = 30)
    @NotNull
    private String usuario;

    @Size(min=3, max = 32)
    @Column(length = 32)
    @NotNull
    private String senha;


    @Column(name = "ativo")
    private Boolean isAtivo;

    @Transient
    private List<String> recursos;

    /**
     *
     */
    public Usuario() {
    }

    /**
     *
     * @param id
     */
    public Usuario(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Short getPapel() {
        return papel;
    }

    /**
     *
     * @param papel
     */
    public void setPapel(Short papel) {
        this.papel = papel;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }


    /**
     *
     * @return
     */
    public Boolean getIsAtivo() {
        return isAtivo;
    }

    /**
     *
     * @param isAtivo
     */
    public void setIsAtivo(Boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    /**
     *
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public String getAminesia() {
        return aminesia;
    }

    /**
     *
     * @param aminesia
     */
    public void setAminesia(String aminesia) {
        this.aminesia = aminesia;
    }

    /**
     *
     * @return
     */
    public List<String> getRecursos() {
		return Collections.unmodifiableList(recursos);
	}

    /**
     *
     * @param recursos
     */
    public void setRecursos(List<String> recursos) {
		this.recursos = recursos;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.guddi.domain.Usuario[ id=" + id + " ]";
    }

}
