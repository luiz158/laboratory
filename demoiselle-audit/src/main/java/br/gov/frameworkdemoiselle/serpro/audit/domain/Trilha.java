/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.audit.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trilha.findAll", query = "SELECT d FROM Trilha d"),
    @NamedQuery(name = "Trilha.findByIdaudit", query = "SELECT d FROM Trilha d WHERE d.idaudit = :idaudit"),
    @NamedQuery(name = "Trilha.findBySistema", query = "SELECT d FROM Trilha d WHERE d.sistema = :sistema"),
    @NamedQuery(name = "Trilha.findByUsuario", query = "SELECT d FROM Trilha d WHERE d.usuario = :usuario"),
    @NamedQuery(name = "Trilha.findByOque", query = "SELECT d FROM Trilha d WHERE d.oque = :oque"),
    @NamedQuery(name = "Trilha.findByComo", query = "SELECT d FROM Trilha d WHERE d.como = :como"),
    @NamedQuery(name = "Trilha.findByOnde", query = "SELECT d FROM Trilha d WHERE d.onde = :onde"),
    @NamedQuery(name = "Trilha.findByQuando", query = "SELECT d FROM Trilha d WHERE d.quando = :quando")})
public class Trilha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idaudit;
    @Column(length = 64)
    private String sistema;
    @Column(length = 64)
    private String usuario;
    @Column(length = 64)
    private String oque;
    @Column(length = 64)
    private String como;
    @Column(length = 64)
    private String onde;
    @Column(length = 49)
    @Temporal(TemporalType.TIMESTAMP)
    private Date quando;
    @Lob
    @Column(length = 65535)
    private String objserial;

    /**
     *
     */
    public Trilha() {
    }

    /**
     *
     * @param idaudit
     */
    public Trilha(Long idaudit) {
        this.idaudit = idaudit;
    }

    /**
     *
     * @return
     */
    public Long getIdaudit() {
        return idaudit;
    }

    /**
     *
     * @param idaudit
     */
    public void setIdaudit(Long idaudit) {
        this.idaudit = idaudit;
    }

    /**
     *
     * @return
     */
    public String getSistema() {
        return sistema;
    }

    /**
     *
     * @param sistema
     */
    public void setSistema(String sistema) {
        this.sistema = sistema;
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
    public String getOque() {
        return oque;
    }

    /**
     *
     * @param oque
     */
    public void setOque(String oque) {
        this.oque = oque;
    }

    /**
     *
     * @return
     */
    public String getComo() {
        return como;
    }

    /**
     *
     * @param como
     */
    public void setComo(String como) {
        this.como = como;
    }

    /**
     *
     * @return
     */
    public String getOnde() {
        return onde;
    }

    /**
     *
     * @param onde
     */
    public void setOnde(String onde) {
        this.onde = onde;
    }

    /**
     *
     * @return
     */
    public Date getQuando() {
        return quando;
    }

    /**
     *
     * @param quando
     */
    public void setQuando(Date quando) {
        this.quando = quando;
    }

    /**
     *
     * @return
     */
    public String getObjserial() {
        return objserial;
    }

    /**
     *
     * @param objserial
     */
    public void setObjserial(String objserial) {
        this.objserial = objserial;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaudit != null ? idaudit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trilha)) {
            return false;
        }
        Trilha other = (Trilha) object;
        if ((this.idaudit == null && other.idaudit != null) || (this.idaudit != null && !this.idaudit.equals(other.idaudit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trilha{" + "idaudit=" + idaudit + ", sistema=" + sistema + ", usuario=" + usuario + ", oque=" + oque + ", como=" + como + ", onde=" + onde + ", quando=" + quando + ", objserial=" + objserial + '}';
    }



}
