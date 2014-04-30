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
package br.gov.frameworkdemoiselle.component.audit.dashboard.domain;

import br.gov.frameworkdemoiselle.component.audit.domain.Trail;
import java.util.Date;
import java.util.Objects;
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
public class Trilha extends Trail {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idaudit;

    /**
     *
     */
    public Trilha() {
    }

    public Trilha(Long idaudit) {
        this.idaudit = idaudit;
    }

    public Trilha(Long idaudit, String systemName, String userName, String idName, String profile, String what, String how, String where, Date when, String className, String objSerial, String layerName, String processorName) {
        super(systemName, userName, idName, profile, what, how, where, when, className, objSerial, layerName, processorName);
        this.idaudit = idaudit;
    }

    public Trilha(Long idaudit, Trail trail) {
        super(trail.getSystemName(), trail.getUserName(), trail.getIdName(),
              trail.getProfile(), trail.getWhat(), trail.getHow(), trail.getWhere(),
              trail.getWhen(), trail.getClassName(), trail.getObjSerial(),
              trail.getLayerName(), trail.getProcessorName());
        this.idaudit = idaudit;
    }

    public Trilha(Trail trail) {
        super(trail.getSystemName(), trail.getUserName(), trail.getIdName(),
              trail.getProfile(), trail.getWhat(), trail.getHow(), trail.getWhere(),
              trail.getWhen(), trail.getClassName(), trail.getObjSerial(),
              trail.getLayerName(), trail.getProcessorName());
        this.idaudit = 1L;
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
    public String getSystemName() {
        return systemName;
    }

    /**
     *
     * @param systemName
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return super.getUserName();
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.setUserName(userName);
    }

    /**
     *
     * @return
     */
    public String getProfile() {
        return super.getProfile();
    }

    /**
     *
     * @param profile
     */
    public void setProfile(String profile) {
        super.setProfile(profile);
    }

    /**
     *
     * @return
     */
    public String getWhat() {
        return super.getWhat();
    }

    /**
     *
     * @param what
     */
    public void setWhat(String what) {
        super.setWhat(what);
    }

    /**
     *
     * @return
     */
    public String getHow() {
        return super.getHow();
    }

    /**
     *
     * @param how
     */
    public void setHow(String how) {
        super.setHow(how);
    }

    /**
     *
     * @return
     */
    public String getWhere() {
        return super.getWhere();
    }

    /**
     *
     * @param where
     */
    public void setWhere(String where) {
        super.setWhere(where);
    }

    /**
     *
     * @return
     */
    public Date getWhen() {
        return super.getWhen();
    }

    /**
     *
     * @param when
     */
    public void setWhen(Date when) {
        super.setWhen(when);
    }

    /**
     *
     * @return
     */
    public String getClassName() {
        return super.getClassName();
    }

    /**
     *
     * @param className
     */
    public void setClassName(String className) {
        super.setClassName(className);
    }

    /**
     *
     * @return
     */
    public String getObjSerial() {
        return super.getObjSerial();
    }

    /**
     *
     * @param objSerial
     */
    public void setObjSerial(String objSerial) {
        super.setObjSerial(objSerial);
    }

    /**
     *
     * @return
     */
    public String getLayerName() {
        return super.getLayerName();
    }

    /**
     *
     * @param layerName
     */
    public void setLayerName(String layerName) {
        super.setLayerName(layerName);
    }

    /**
     *
     * @return
     */
    public String getProcessorName() {
        return super.getProcessorName();
    }

    /**
     *
     * @param processorName
     */
    public void setProcessorName(String processorName) {
        super.setProcessorName(processorName);
    }

    /**
     *
     * @return
     */
    public String getIdName() {
        return super.getIdName();
    }

    /**
     *
     * @param idName
     */
    public void setIdName(String idName) {
        super.setIdName(idName);
    }

    @Override
    public String toString() {
        return "Trilha{" + "idaudit=" + idaudit + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idaudit);
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
        final Trilha other = (Trilha) obj;
        if (!Objects.equals(this.idaudit, other.idaudit)) {
            return false;
        }
        return true;
    }

}
