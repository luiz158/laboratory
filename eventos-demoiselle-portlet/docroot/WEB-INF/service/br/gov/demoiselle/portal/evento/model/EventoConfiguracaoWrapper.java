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
 * This class is a wrapper for {@link EventoConfiguracao}.
 * </p>
 *
 * @author    Clovis Lemes Ferreira Junior
 * @see       EventoConfiguracao
 * @generated
 */
public class EventoConfiguracaoWrapper implements EventoConfiguracao,
	ModelWrapper<EventoConfiguracao> {
	public EventoConfiguracaoWrapper(EventoConfiguracao eventoConfiguracao) {
		_eventoConfiguracao = eventoConfiguracao;
	}

	public Class<?> getModelClass() {
		return EventoConfiguracao.class;
	}

	public String getModelClassName() {
		return EventoConfiguracao.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventoConfiguracaoId", getEventoConfiguracaoId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("eventoId", getEventoId());
		attributes.put("abertoAoPublico", getAbertoAoPublico());
		attributes.put("cidadeDoEvento", getCidadeDoEvento());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventoConfiguracaoId = (Long)attributes.get("eventoConfiguracaoId");

		if (eventoConfiguracaoId != null) {
			setEventoConfiguracaoId(eventoConfiguracaoId);
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

		Boolean abertoAoPublico = (Boolean)attributes.get("abertoAoPublico");

		if (abertoAoPublico != null) {
			setAbertoAoPublico(abertoAoPublico);
		}

		String cidadeDoEvento = (String)attributes.get("cidadeDoEvento");

		if (cidadeDoEvento != null) {
			setCidadeDoEvento(cidadeDoEvento);
		}
	}

	/**
	* Returns the primary key of this evento configuracao.
	*
	* @return the primary key of this evento configuracao
	*/
	public long getPrimaryKey() {
		return _eventoConfiguracao.getPrimaryKey();
	}

	/**
	* Sets the primary key of this evento configuracao.
	*
	* @param primaryKey the primary key of this evento configuracao
	*/
	public void setPrimaryKey(long primaryKey) {
		_eventoConfiguracao.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the evento configuracao ID of this evento configuracao.
	*
	* @return the evento configuracao ID of this evento configuracao
	*/
	public long getEventoConfiguracaoId() {
		return _eventoConfiguracao.getEventoConfiguracaoId();
	}

	/**
	* Sets the evento configuracao ID of this evento configuracao.
	*
	* @param eventoConfiguracaoId the evento configuracao ID of this evento configuracao
	*/
	public void setEventoConfiguracaoId(long eventoConfiguracaoId) {
		_eventoConfiguracao.setEventoConfiguracaoId(eventoConfiguracaoId);
	}

	/**
	* Returns the company ID of this evento configuracao.
	*
	* @return the company ID of this evento configuracao
	*/
	public long getCompanyId() {
		return _eventoConfiguracao.getCompanyId();
	}

	/**
	* Sets the company ID of this evento configuracao.
	*
	* @param companyId the company ID of this evento configuracao
	*/
	public void setCompanyId(long companyId) {
		_eventoConfiguracao.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this evento configuracao.
	*
	* @return the user ID of this evento configuracao
	*/
	public long getUserId() {
		return _eventoConfiguracao.getUserId();
	}

	/**
	* Sets the user ID of this evento configuracao.
	*
	* @param userId the user ID of this evento configuracao
	*/
	public void setUserId(long userId) {
		_eventoConfiguracao.setUserId(userId);
	}

	/**
	* Returns the user uuid of this evento configuracao.
	*
	* @return the user uuid of this evento configuracao
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoConfiguracao.getUserUuid();
	}

	/**
	* Sets the user uuid of this evento configuracao.
	*
	* @param userUuid the user uuid of this evento configuracao
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_eventoConfiguracao.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this evento configuracao.
	*
	* @return the create date of this evento configuracao
	*/
	public java.util.Date getCreateDate() {
		return _eventoConfiguracao.getCreateDate();
	}

	/**
	* Sets the create date of this evento configuracao.
	*
	* @param createDate the create date of this evento configuracao
	*/
	public void setCreateDate(java.util.Date createDate) {
		_eventoConfiguracao.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this evento configuracao.
	*
	* @return the modified date of this evento configuracao
	*/
	public java.util.Date getModifiedDate() {
		return _eventoConfiguracao.getModifiedDate();
	}

	/**
	* Sets the modified date of this evento configuracao.
	*
	* @param modifiedDate the modified date of this evento configuracao
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_eventoConfiguracao.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the evento ID of this evento configuracao.
	*
	* @return the evento ID of this evento configuracao
	*/
	public long getEventoId() {
		return _eventoConfiguracao.getEventoId();
	}

	/**
	* Sets the evento ID of this evento configuracao.
	*
	* @param eventoId the evento ID of this evento configuracao
	*/
	public void setEventoId(long eventoId) {
		_eventoConfiguracao.setEventoId(eventoId);
	}

	/**
	* Returns the aberto ao publico of this evento configuracao.
	*
	* @return the aberto ao publico of this evento configuracao
	*/
	public boolean getAbertoAoPublico() {
		return _eventoConfiguracao.getAbertoAoPublico();
	}

	/**
	* Returns <code>true</code> if this evento configuracao is aberto ao publico.
	*
	* @return <code>true</code> if this evento configuracao is aberto ao publico; <code>false</code> otherwise
	*/
	public boolean isAbertoAoPublico() {
		return _eventoConfiguracao.isAbertoAoPublico();
	}

	/**
	* Sets whether this evento configuracao is aberto ao publico.
	*
	* @param abertoAoPublico the aberto ao publico of this evento configuracao
	*/
	public void setAbertoAoPublico(boolean abertoAoPublico) {
		_eventoConfiguracao.setAbertoAoPublico(abertoAoPublico);
	}

	/**
	* Returns the cidade do evento of this evento configuracao.
	*
	* @return the cidade do evento of this evento configuracao
	*/
	public java.lang.String getCidadeDoEvento() {
		return _eventoConfiguracao.getCidadeDoEvento();
	}

	/**
	* Sets the cidade do evento of this evento configuracao.
	*
	* @param cidadeDoEvento the cidade do evento of this evento configuracao
	*/
	public void setCidadeDoEvento(java.lang.String cidadeDoEvento) {
		_eventoConfiguracao.setCidadeDoEvento(cidadeDoEvento);
	}

	public boolean isNew() {
		return _eventoConfiguracao.isNew();
	}

	public void setNew(boolean n) {
		_eventoConfiguracao.setNew(n);
	}

	public boolean isCachedModel() {
		return _eventoConfiguracao.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_eventoConfiguracao.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _eventoConfiguracao.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _eventoConfiguracao.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_eventoConfiguracao.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _eventoConfiguracao.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_eventoConfiguracao.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new EventoConfiguracaoWrapper((EventoConfiguracao)_eventoConfiguracao.clone());
	}

	public int compareTo(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao) {
		return _eventoConfiguracao.compareTo(eventoConfiguracao);
	}

	@Override
	public int hashCode() {
		return _eventoConfiguracao.hashCode();
	}

	public com.liferay.portal.model.CacheModel<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> toCacheModel() {
		return _eventoConfiguracao.toCacheModel();
	}

	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao toEscapedModel() {
		return new EventoConfiguracaoWrapper(_eventoConfiguracao.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _eventoConfiguracao.toString();
	}

	public java.lang.String toXmlString() {
		return _eventoConfiguracao.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_eventoConfiguracao.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public EventoConfiguracao getWrappedEventoConfiguracao() {
		return _eventoConfiguracao;
	}

	public EventoConfiguracao getWrappedModel() {
		return _eventoConfiguracao;
	}

	public void resetOriginalValues() {
		_eventoConfiguracao.resetOriginalValues();
	}

	private EventoConfiguracao _eventoConfiguracao;
}