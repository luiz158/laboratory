/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package br.gov.demoiselle.portal.evento.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EventoConvite}.
 * </p>
 *
 * @author    Clovis Lemes Ferreira Junior
 * @see       EventoConvite
 * @generated
 */
public class EventoConviteWrapper implements EventoConvite,
	ModelWrapper<EventoConvite> {
	public EventoConviteWrapper(EventoConvite eventoConvite) {
		_eventoConvite = eventoConvite;
	}

	public Class<?> getModelClass() {
		return EventoConvite.class;
	}

	public String getModelClassName() {
		return EventoConvite.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventoConviteId", getEventoConviteId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("eventoId", getEventoId());
		attributes.put("textoConvite", getTextoConvite());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventoConviteId = (Long)attributes.get("eventoConviteId");

		if (eventoConviteId != null) {
			setEventoConviteId(eventoConviteId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long eventoId = (Long)attributes.get("eventoId");

		if (eventoId != null) {
			setEventoId(eventoId);
		}

		String textoConvite = (String)attributes.get("textoConvite");

		if (textoConvite != null) {
			setTextoConvite(textoConvite);
		}
	}

	/**
	* Returns the primary key of this evento convite.
	*
	* @return the primary key of this evento convite
	*/
	public long getPrimaryKey() {
		return _eventoConvite.getPrimaryKey();
	}

	/**
	* Sets the primary key of this evento convite.
	*
	* @param primaryKey the primary key of this evento convite
	*/
	public void setPrimaryKey(long primaryKey) {
		_eventoConvite.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the evento convite ID of this evento convite.
	*
	* @return the evento convite ID of this evento convite
	*/
	public long getEventoConviteId() {
		return _eventoConvite.getEventoConviteId();
	}

	/**
	* Sets the evento convite ID of this evento convite.
	*
	* @param eventoConviteId the evento convite ID of this evento convite
	*/
	public void setEventoConviteId(long eventoConviteId) {
		_eventoConvite.setEventoConviteId(eventoConviteId);
	}

	/**
	* Returns the company ID of this evento convite.
	*
	* @return the company ID of this evento convite
	*/
	public long getCompanyId() {
		return _eventoConvite.getCompanyId();
	}

	/**
	* Sets the company ID of this evento convite.
	*
	* @param companyId the company ID of this evento convite
	*/
	public void setCompanyId(long companyId) {
		_eventoConvite.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this evento convite.
	*
	* @return the user ID of this evento convite
	*/
	public long getUserId() {
		return _eventoConvite.getUserId();
	}

	/**
	* Sets the user ID of this evento convite.
	*
	* @param userId the user ID of this evento convite
	*/
	public void setUserId(long userId) {
		_eventoConvite.setUserId(userId);
	}

	/**
	* Returns the user uuid of this evento convite.
	*
	* @return the user uuid of this evento convite
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoConvite.getUserUuid();
	}

	/**
	* Sets the user uuid of this evento convite.
	*
	* @param userUuid the user uuid of this evento convite
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_eventoConvite.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this evento convite.
	*
	* @return the create date of this evento convite
	*/
	public java.util.Date getCreateDate() {
		return _eventoConvite.getCreateDate();
	}

	/**
	* Sets the create date of this evento convite.
	*
	* @param createDate the create date of this evento convite
	*/
	public void setCreateDate(java.util.Date createDate) {
		_eventoConvite.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this evento convite.
	*
	* @return the modified date of this evento convite
	*/
	public java.util.Date getModifiedDate() {
		return _eventoConvite.getModifiedDate();
	}

	/**
	* Sets the modified date of this evento convite.
	*
	* @param modifiedDate the modified date of this evento convite
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_eventoConvite.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the evento ID of this evento convite.
	*
	* @return the evento ID of this evento convite
	*/
	public long getEventoId() {
		return _eventoConvite.getEventoId();
	}

	/**
	* Sets the evento ID of this evento convite.
	*
	* @param eventoId the evento ID of this evento convite
	*/
	public void setEventoId(long eventoId) {
		_eventoConvite.setEventoId(eventoId);
	}

	/**
	* Returns the texto convite of this evento convite.
	*
	* @return the texto convite of this evento convite
	*/
	public java.lang.String getTextoConvite() {
		return _eventoConvite.getTextoConvite();
	}

	/**
	* Sets the texto convite of this evento convite.
	*
	* @param textoConvite the texto convite of this evento convite
	*/
	public void setTextoConvite(java.lang.String textoConvite) {
		_eventoConvite.setTextoConvite(textoConvite);
	}

	public boolean isNew() {
		return _eventoConvite.isNew();
	}

	public void setNew(boolean n) {
		_eventoConvite.setNew(n);
	}

	public boolean isCachedModel() {
		return _eventoConvite.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_eventoConvite.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _eventoConvite.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _eventoConvite.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_eventoConvite.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _eventoConvite.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_eventoConvite.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new EventoConviteWrapper((EventoConvite)_eventoConvite.clone());
	}

	public int compareTo(
		br.gov.demoiselle.portal.evento.model.EventoConvite eventoConvite) {
		return _eventoConvite.compareTo(eventoConvite);
	}

	@Override
	public int hashCode() {
		return _eventoConvite.hashCode();
	}

	public com.liferay.portal.model.CacheModel<br.gov.demoiselle.portal.evento.model.EventoConvite> toCacheModel() {
		return _eventoConvite.toCacheModel();
	}

	public br.gov.demoiselle.portal.evento.model.EventoConvite toEscapedModel() {
		return new EventoConviteWrapper(_eventoConvite.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _eventoConvite.toString();
	}

	public java.lang.String toXmlString() {
		return _eventoConvite.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_eventoConvite.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public EventoConvite getWrappedEventoConvite() {
		return _eventoConvite;
	}

	public EventoConvite getWrappedModel() {
		return _eventoConvite;
	}

	public void resetOriginalValues() {
		_eventoConvite.resetOriginalValues();
	}

	private EventoConvite _eventoConvite;
}