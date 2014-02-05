/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author escritorio
 */
@Embeddable
public class UsuarioRecursoPK implements Serializable {
	
	private static final long serialVersionUID = -1849399048118149823L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer recursos;

    /**
     *
     */
    public UsuarioRecursoPK() {
    }

    /**
     *
     * @param idUsuario
     * @param recursos
     */
    public UsuarioRecursoPK(Long idUsuario, Integer recursos) {
        this.idUsuario = idUsuario;
        this.recursos = recursos;
    }

    /**
     *
     * @return
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     *
     * @param idUsuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     *
     * @return
     */
    public int getRecursos() {
        return recursos;
    }

    /**
     *
     * @param recursos
     */
    public void setRecursos(Integer recursos) {
        this.recursos = recursos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Long) idUsuario;
        hash += recursos;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRecursoPK)) {
            return false;
        }
        UsuarioRecursoPK other = (UsuarioRecursoPK) object;
        if (!this.idUsuario.equals(other.idUsuario)) {
            return false;
        }
        if (!this.recursos.equals(other.recursos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.org.guddi.domain.UsuarioRecursoPK[ idUsuario=" + idUsuario + ", recursos=" + recursos + " ]";
    }


}
