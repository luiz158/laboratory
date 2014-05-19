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
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.frameworkdemoiselle.component.audit.domain.Trail;

/**
 *
 * @author SERPRO
 *
 */
@Entity(name = "LocalTrail")
@Table(name = "Trail")
@XmlRootElement(name = "Trail")
@NamedQueries({
    @NamedQuery(name = "LocalTrail.findAll", query = "SELECT d FROM LocalTrail d"),
    @NamedQuery(name = "LocalTrail.findByIdaudit", query = "SELECT d FROM LocalTrail d WHERE d.idaudit = :idaudit"),
    @NamedQuery(name = "LocalTrail.findBySystemName", query = "SELECT d FROM LocalTrail d WHERE d.systemName = :systemName AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findByUserName", query = "SELECT d FROM LocalTrail d WHERE d.userName = :userName AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findByWhat", query = "SELECT d FROM LocalTrail d WHERE d.what = :what AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findByHow", query = "SELECT d FROM LocalTrail d WHERE d.how = :how AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findByWhere", query = "SELECT d FROM LocalTrail d WHERE d.where = :where AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findByWhen", query = "SELECT d FROM LocalTrail d WHERE d.when = :when AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findDistinctSystemName", query = "SELECT DISTINCT d.systemName FROM LocalTrail d"),
    @NamedQuery(name = "LocalTrail.findDistinctUserName", query = "SELECT DISTINCT d.userName FROM LocalTrail d"),
    @NamedQuery(name = "LocalTrail.findDistinctWhat", query = "SELECT DISTINCT d.what FROM LocalTrail d"),
    @NamedQuery(name = "LocalTrail.findDistinctClassName", query = "SELECT DISTINCT d.className FROM LocalTrail d WHERE d.systemName = :systemName"),
    @NamedQuery(name = "LocalTrail.findIdNameBySystemAndObject", query = "SELECT DISTINCT d.idName FROM LocalTrail d WHERE d.systemName = :systemName AND d.className = :className"),
    @NamedQuery(name = "LocalTrail.findByNamedQuerySystemAndObjectAndIdName", query = "SELECT d FROM LocalTrail d WHERE d.systemName = :systemName AND d.className = :className AND d.objSerial LIKE :value ORDER BY d.when, d.idaudit")
})
public class LocalTrail implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idaudit;
    
    private String systemName;
    private String userName;
    private String idName;
    private String profile;
    
    @Column(name = "_WHAT")
    private String what;
    
    @Column(name = "_WHERE")
    private String where;
    
    @Column(name = "_WHEN")
    private Date when;
    
    @Column(name = "_HOW")
    private String how;
    private String className;
    private String objSerial;
    private String layerName;
    private String processorName;

    /**
     *
     */
    public LocalTrail() {
    }

    public LocalTrail(Long idaudit) {
        this.idaudit = idaudit;
    }

    
    public LocalTrail(Long idaudit, String systemName, String userName,
			String idName, String profile, String what, String where,
			Date when, String how, String className, String objSerial,
			String layerName, String processorName) {
		super();
		this.idaudit = idaudit;
		this.systemName = systemName;
		this.userName = userName;
		this.idName = idName;
		this.profile = profile;
		this.what = what;
		this.where = where;
		this.when = when;
		this.how = how;
		this.className = className;
		this.objSerial = objSerial;
		this.layerName = layerName;
		this.processorName = processorName;
	}

	public LocalTrail(Long idaudit, Trail trail) {
		
		this.idaudit = idaudit;
		this.systemName = trail.getSystemName();
		this.userName = trail.getUserName();
		this.idName = trail.getIdName();
		this.profile = trail.getProfile();
		this.what = trail.getWhat();
		this.where = trail.getWhere();
		this.when = trail.getWhen();
		this.how = trail.getHow();
		this.className = trail.getClassName();
		this.objSerial = trail.getObjSerial();
		this.layerName = trail.getLayerName();
		this.processorName = trail.getProcessorName();
        
    }

    public LocalTrail(Trail trail) {
    	this.idaudit = 1L;
    	this.systemName = trail.getSystemName();
		this.userName = trail.getUserName();
		this.idName = trail.getIdName();
		this.profile = trail.getProfile();
		this.what = trail.getWhat();
		this.where = trail.getWhere();
		this.when = trail.getWhen();
		this.how = trail.getHow();
		this.className = trail.getClassName();
		this.objSerial = trail.getObjSerial();
		this.layerName = trail.getLayerName();
		this.processorName = trail.getProcessorName();
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

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
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

	public String getHow() {
		return how;
	}

	public void setHow(String how) {
		this.how = how;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((how == null) ? 0 : how.hashCode());
		result = prime * result + ((idName == null) ? 0 : idName.hashCode());
		result = prime * result + ((idaudit == null) ? 0 : idaudit.hashCode());
		result = prime * result
				+ ((layerName == null) ? 0 : layerName.hashCode());
		result = prime * result
				+ ((objSerial == null) ? 0 : objSerial.hashCode());
		result = prime * result
				+ ((processorName == null) ? 0 : processorName.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result
				+ ((systemName == null) ? 0 : systemName.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((what == null) ? 0 : what.hashCode());
		result = prime * result + ((when == null) ? 0 : when.hashCode());
		result = prime * result + ((where == null) ? 0 : where.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalTrail other = (LocalTrail) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (how == null) {
			if (other.how != null)
				return false;
		} else if (!how.equals(other.how))
			return false;
		if (idName == null) {
			if (other.idName != null)
				return false;
		} else if (!idName.equals(other.idName))
			return false;
		if (idaudit == null) {
			if (other.idaudit != null)
				return false;
		} else if (!idaudit.equals(other.idaudit))
			return false;
		if (layerName == null) {
			if (other.layerName != null)
				return false;
		} else if (!layerName.equals(other.layerName))
			return false;
		if (objSerial == null) {
			if (other.objSerial != null)
				return false;
		} else if (!objSerial.equals(other.objSerial))
			return false;
		if (processorName == null) {
			if (other.processorName != null)
				return false;
		} else if (!processorName.equals(other.processorName))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (systemName == null) {
			if (other.systemName != null)
				return false;
		} else if (!systemName.equals(other.systemName))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (what == null) {
			if (other.what != null)
				return false;
		} else if (!what.equals(other.what))
			return false;
		if (when == null) {
			if (other.when != null)
				return false;
		} else if (!when.equals(other.when))
			return false;
		if (where == null) {
			if (other.where != null)
				return false;
		} else if (!where.equals(other.where))
			return false;
		return true;
	}

   
}
