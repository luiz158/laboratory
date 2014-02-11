/*
 * Demoiselle Framework
 * Copyright (C) 2014 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 *
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 *
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.component.audit.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SERPRO
 *
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trail.findAll", query = "SELECT d FROM Trail d"),
    @NamedQuery(name = "Trail.findByIdaudit", query = "SELECT d FROM Trail d WHERE d.idaudit = :idaudit"),
    @NamedQuery(name = "Trail.findBySystemName", query = "SELECT d FROM Trail d WHERE d.systemName = :systemName AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "Trail.findByUserName", query = "SELECT d FROM Trail d WHERE d.userName = :userName AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "Trail.findByWhat", query = "SELECT d FROM Trail d WHERE d.what = :what AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "Trail.findByHow", query = "SELECT d FROM Trail d WHERE d.how = :how AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "Trail.findByWhere", query = "SELECT d FROM Trail d WHERE d.where = :where AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "Trail.findByWhen", query = "SELECT d FROM Trail d WHERE d.when = :when AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "Trail.findDistinctSystemName", query = "SELECT DISTINCT d.systemName FROM Trail d"),
    @NamedQuery(name = "Trail.findDistinctUserName", query = "SELECT DISTINCT d.userName FROM Trail d"),
    @NamedQuery(name = "Trail.findDistinctWhat", query = "SELECT DISTINCT d.what FROM Trail d"),
    @NamedQuery(name = "Trail.findDistinctClassName", query = "SELECT DISTINCT d.className FROM Trail d WHERE d.systemName = :systemName"),
    @NamedQuery(name = "Trail.findIdNameBySystemAndObject", query = "SELECT DISTINCT d.idName FROM Trail d WHERE d.systemName = :systemName AND d.className = :className"),
    @NamedQuery(name = "Trail.findByNamedQuerySystemAndObjectAndIdName", query = "SELECT d FROM Trail d WHERE d.systemName = :systemName AND d.className = :className AND d.objSerial LIKE :value ORDER BY d.when, d.idaudit")
})
public class Trail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long idaudit;

    @Column(length = 1024)
    protected String systemName;

    @Column(length = 1024)
    private String userName;

    @Column(length = 1024)
    private String idName;

    @Column(length = 1024)
    private String profile;

    @Column(length = 1024)
    private String what;

    @Column(name = "_WHERE", length = 1024)
    private String where;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "_WHEN")
    private Date when;

    @Column(length = 1024)
    private String how;

    @Column(length = 1024)
    private String className;

    @Column(length = 1024)
    private String objSerial;

    @Column(length = 1024)
    private String layerName;

    @Column(length = 1024)
    private String processorName;

    public Trail() {
    }

    public Trail(Long idaudit, String systemName, String userName, String idName,
                 String profile, String what, String how, String where, Date when,
                 String className, String objSerial, String layerName,
                 String processorName) {
        super();
        this.idaudit = idaudit;
        this.systemName = systemName;
        this.userName = userName;
        this.idName = idName;
        this.profile = profile;
        this.what = what;
        this.how = how;
        this.where = where;
        this.when = when;
        this.className = className;
        this.objSerial = objSerial;
        this.layerName = layerName;
        this.processorName = processorName;
    }

    public Long getIdaudit() {
        return idaudit;
    }

    public void setIdaudit(Long idaudit) {
        this.idaudit = idaudit;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getObjSerial() {
        return objSerial;
    }

    public void setObjSerial(String objSerial) {
        this.objSerial = objSerial;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.idaudit != null ? this.idaudit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trail other = (Trail) obj;
        if (this.idaudit != other.idaudit && (this.idaudit == null || !this.idaudit.equals(other.idaudit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trail{" + "idaudit=" + idaudit + ", systemName=" + systemName + ", userName=" + userName + ", idName=" + idName + ", profile=" + profile + ", what=" + what + ", where=" + where + ", when=" + when + ", how=" + how + ", className=" + className + ", objSerial=" + objSerial + ", layerName=" + layerName + ", processorName=" + processorName + '}';
    }

}
