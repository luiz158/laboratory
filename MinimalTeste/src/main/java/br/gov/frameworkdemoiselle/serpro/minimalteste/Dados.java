/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.minimalteste;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(catalog = "audit", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dados.findAll", query = "SELECT d FROM Dados d"),
    @NamedQuery(name = "Dados.findByIddados", query = "SELECT d FROM Dados d WHERE d.iddados = :iddados"),
    @NamedQuery(name = "Dados.findByUsuario", query = "SELECT d FROM Dados d WHERE d.usuario = :usuario"),
    @NamedQuery(name = "Dados.findBySistema", query = "SELECT d FROM Dados d WHERE d.sistema = :sistema"),
    @NamedQuery(name = "Dados.findByOque", query = "SELECT d FROM Dados d WHERE d.oque = :oque"),
    @NamedQuery(name = "Dados.findByComo", query = "SELECT d FROM Dados d WHERE d.como = :como"),
    @NamedQuery(name = "Dados.findByOnde", query = "SELECT d FROM Dados d WHERE d.onde = :onde"),
    @NamedQuery(name = "Dados.findByQuando", query = "SELECT d FROM Dados d WHERE d.quando = :quando"),
    @NamedQuery(name = "Dados.findByObjini", query = "SELECT d FROM Dados d WHERE d.objini = :objini"),
    @NamedQuery(name = "Dados.findByObjfim", query = "SELECT d FROM Dados d WHERE d.objfim = :objfim")})
public class Dados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long iddados;
    @Size(max = 45)
    @Column(length = 45)
    private String usuario;
    @Size(max = 45)
    @Column(length = 45)
    private String sistema;
    @Size(max = 45)
    @Column(length = 45)
    private String oque;
    @Size(max = 45)
    @Column(length = 45)
    private String como;
    @Size(max = 45)
    @Column(length = 45)
    private String onde;
    @Size(max = 45)
    @Column(length = 45)
    private String quando;
    @Size(max = 100)
    @Column(length = 100)
    private String objini;
    @Size(max = 100)
    @Column(length = 100)
    private String objfim;

    public Dados() {
    }

    public Dados(Long iddados) {
        this.iddados = iddados;
    }

    public Long getIddados() {
        return iddados;
    }

    public void setIddados(Long iddados) {
        this.iddados = iddados;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getOque() {
        return oque;
    }

    public void setOque(String oque) {
        this.oque = oque;
    }

    public String getComo() {
        return como;
    }

    public void setComo(String como) {
        this.como = como;
    }

    public String getOnde() {
        return onde;
    }

    public void setOnde(String onde) {
        this.onde = onde;
    }

    public String getQuando() {
        return quando;
    }

    public void setQuando(String quando) {
        this.quando = quando;
    }

    public String getObjini() {
        return objini;
    }

    public void setObjini(String objini) {
        this.objini = objini;
    }

    public String getObjfim() {
        return objfim;
    }

    public void setObjfim(String objfim) {
        this.objfim = objfim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddados != null ? iddados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dados)) {
            return false;
        }
        Dados other = (Dados) object;
        if ((this.iddados == null && other.iddados != null) || (this.iddados != null && !this.iddados.equals(other.iddados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.serpro.minimalteste.Dados[ iddados=" + iddados + " ]";
    }

}
