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
 * This class is a wrapper for {@link EventoParticipante}.
 * </p>
 *
 * @author    Clovis Lemes Ferreira Junior
 * @see       EventoParticipante
 * @generated
 */
public class EventoParticipanteWrapper implements EventoParticipante,
	ModelWrapper<EventoParticipante> {
	public EventoParticipanteWrapper(EventoParticipante eventoParticipante) {
		_eventoParticipante = eventoParticipante;
	}

	public Class<?> getModelClass() {
		return EventoParticipante.class;
	}

	public String getModelClassName() {
		return EventoParticipante.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventoParticipanteId", getEventoParticipanteId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("eventoId", getEventoId());
		attributes.put("nome", getNome());
		attributes.put("email", getEmail());
		attributes.put("instituicaoEmpresa", getInstituicaoEmpresa());
		attributes.put("conviteEnviado", getConviteEnviado());
		attributes.put("certificadoImpresso", getCertificadoImpresso());
		attributes.put("inscricaoConfirmada", getInscricaoConfirmada());
		attributes.put("participacaoConfirmada", getParticipacaoConfirmada());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventoParticipanteId = (Long)attributes.get("eventoParticipanteId");

		if (eventoParticipanteId != null) {
			setEventoParticipanteId(eventoParticipanteId);
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

		String nome = (String)attributes.get("nome");

		if (nome != null) {
			setNome(nome);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String instituicaoEmpresa = (String)attributes.get("instituicaoEmpresa");

		if (instituicaoEmpresa != null) {
			setInstituicaoEmpresa(instituicaoEmpresa);
		}

		Boolean conviteEnviado = (Boolean)attributes.get("conviteEnviado");

		if (conviteEnviado != null) {
			setConviteEnviado(conviteEnviado);
		}

		Boolean certificadoImpresso = (Boolean)attributes.get(
				"certificadoImpresso");

		if (certificadoImpresso != null) {
			setCertificadoImpresso(certificadoImpresso);
		}

		Boolean inscricaoConfirmada = (Boolean)attributes.get(
				"inscricaoConfirmada");

		if (inscricaoConfirmada != null) {
			setInscricaoConfirmada(inscricaoConfirmada);
		}

		Boolean participacaoConfirmada = (Boolean)attributes.get(
				"participacaoConfirmada");

		if (participacaoConfirmada != null) {
			setParticipacaoConfirmada(participacaoConfirmada);
		}
	}

	/**
	* Returns the primary key of this evento participante.
	*
	* @return the primary key of this evento participante
	*/
	public long getPrimaryKey() {
		return _eventoParticipante.getPrimaryKey();
	}

	/**
	* Sets the primary key of this evento participante.
	*
	* @param primaryKey the primary key of this evento participante
	*/
	public void setPrimaryKey(long primaryKey) {
		_eventoParticipante.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the evento participante ID of this evento participante.
	*
	* @return the evento participante ID of this evento participante
	*/
	public long getEventoParticipanteId() {
		return _eventoParticipante.getEventoParticipanteId();
	}

	/**
	* Sets the evento participante ID of this evento participante.
	*
	* @param eventoParticipanteId the evento participante ID of this evento participante
	*/
	public void setEventoParticipanteId(long eventoParticipanteId) {
		_eventoParticipante.setEventoParticipanteId(eventoParticipanteId);
	}

	/**
	* Returns the company ID of this evento participante.
	*
	* @return the company ID of this evento participante
	*/
	public long getCompanyId() {
		return _eventoParticipante.getCompanyId();
	}

	/**
	* Sets the company ID of this evento participante.
	*
	* @param companyId the company ID of this evento participante
	*/
	public void setCompanyId(long companyId) {
		_eventoParticipante.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this evento participante.
	*
	* @return the user ID of this evento participante
	*/
	public long getUserId() {
		return _eventoParticipante.getUserId();
	}

	/**
	* Sets the user ID of this evento participante.
	*
	* @param userId the user ID of this evento participante
	*/
	public void setUserId(long userId) {
		_eventoParticipante.setUserId(userId);
	}

	/**
	* Returns the user uuid of this evento participante.
	*
	* @return the user uuid of this evento participante
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipante.getUserUuid();
	}

	/**
	* Sets the user uuid of this evento participante.
	*
	* @param userUuid the user uuid of this evento participante
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_eventoParticipante.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this evento participante.
	*
	* @return the create date of this evento participante
	*/
	public java.util.Date getCreateDate() {
		return _eventoParticipante.getCreateDate();
	}

	/**
	* Sets the create date of this evento participante.
	*
	* @param createDate the create date of this evento participante
	*/
	public void setCreateDate(java.util.Date createDate) {
		_eventoParticipante.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this evento participante.
	*
	* @return the modified date of this evento participante
	*/
	public java.util.Date getModifiedDate() {
		return _eventoParticipante.getModifiedDate();
	}

	/**
	* Sets the modified date of this evento participante.
	*
	* @param modifiedDate the modified date of this evento participante
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_eventoParticipante.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the evento ID of this evento participante.
	*
	* @return the evento ID of this evento participante
	*/
	public long getEventoId() {
		return _eventoParticipante.getEventoId();
	}

	/**
	* Sets the evento ID of this evento participante.
	*
	* @param eventoId the evento ID of this evento participante
	*/
	public void setEventoId(long eventoId) {
		_eventoParticipante.setEventoId(eventoId);
	}

	/**
	* Returns the nome of this evento participante.
	*
	* @return the nome of this evento participante
	*/
	public java.lang.String getNome() {
		return _eventoParticipante.getNome();
	}

	/**
	* Sets the nome of this evento participante.
	*
	* @param nome the nome of this evento participante
	*/
	public void setNome(java.lang.String nome) {
		_eventoParticipante.setNome(nome);
	}

	/**
	* Returns the email of this evento participante.
	*
	* @return the email of this evento participante
	*/
	public java.lang.String getEmail() {
		return _eventoParticipante.getEmail();
	}

	/**
	* Sets the email of this evento participante.
	*
	* @param email the email of this evento participante
	*/
	public void setEmail(java.lang.String email) {
		_eventoParticipante.setEmail(email);
	}

	/**
	* Returns the instituicao empresa of this evento participante.
	*
	* @return the instituicao empresa of this evento participante
	*/
	public java.lang.String getInstituicaoEmpresa() {
		return _eventoParticipante.getInstituicaoEmpresa();
	}

	/**
	* Sets the instituicao empresa of this evento participante.
	*
	* @param instituicaoEmpresa the instituicao empresa of this evento participante
	*/
	public void setInstituicaoEmpresa(java.lang.String instituicaoEmpresa) {
		_eventoParticipante.setInstituicaoEmpresa(instituicaoEmpresa);
	}

	/**
	* Returns the convite enviado of this evento participante.
	*
	* @return the convite enviado of this evento participante
	*/
	public boolean getConviteEnviado() {
		return _eventoParticipante.getConviteEnviado();
	}

	/**
	* Returns <code>true</code> if this evento participante is convite enviado.
	*
	* @return <code>true</code> if this evento participante is convite enviado; <code>false</code> otherwise
	*/
	public boolean isConviteEnviado() {
		return _eventoParticipante.isConviteEnviado();
	}

	/**
	* Sets whether this evento participante is convite enviado.
	*
	* @param conviteEnviado the convite enviado of this evento participante
	*/
	public void setConviteEnviado(boolean conviteEnviado) {
		_eventoParticipante.setConviteEnviado(conviteEnviado);
	}

	/**
	* Returns the certificado impresso of this evento participante.
	*
	* @return the certificado impresso of this evento participante
	*/
	public boolean getCertificadoImpresso() {
		return _eventoParticipante.getCertificadoImpresso();
	}

	/**
	* Returns <code>true</code> if this evento participante is certificado impresso.
	*
	* @return <code>true</code> if this evento participante is certificado impresso; <code>false</code> otherwise
	*/
	public boolean isCertificadoImpresso() {
		return _eventoParticipante.isCertificadoImpresso();
	}

	/**
	* Sets whether this evento participante is certificado impresso.
	*
	* @param certificadoImpresso the certificado impresso of this evento participante
	*/
	public void setCertificadoImpresso(boolean certificadoImpresso) {
		_eventoParticipante.setCertificadoImpresso(certificadoImpresso);
	}

	/**
	* Returns the inscricao confirmada of this evento participante.
	*
	* @return the inscricao confirmada of this evento participante
	*/
	public boolean getInscricaoConfirmada() {
		return _eventoParticipante.getInscricaoConfirmada();
	}

	/**
	* Returns <code>true</code> if this evento participante is inscricao confirmada.
	*
	* @return <code>true</code> if this evento participante is inscricao confirmada; <code>false</code> otherwise
	*/
	public boolean isInscricaoConfirmada() {
		return _eventoParticipante.isInscricaoConfirmada();
	}

	/**
	* Sets whether this evento participante is inscricao confirmada.
	*
	* @param inscricaoConfirmada the inscricao confirmada of this evento participante
	*/
	public void setInscricaoConfirmada(boolean inscricaoConfirmada) {
		_eventoParticipante.setInscricaoConfirmada(inscricaoConfirmada);
	}

	/**
	* Returns the participacao confirmada of this evento participante.
	*
	* @return the participacao confirmada of this evento participante
	*/
	public boolean getParticipacaoConfirmada() {
		return _eventoParticipante.getParticipacaoConfirmada();
	}

	/**
	* Returns <code>true</code> if this evento participante is participacao confirmada.
	*
	* @return <code>true</code> if this evento participante is participacao confirmada; <code>false</code> otherwise
	*/
	public boolean isParticipacaoConfirmada() {
		return _eventoParticipante.isParticipacaoConfirmada();
	}

	/**
	* Sets whether this evento participante is participacao confirmada.
	*
	* @param participacaoConfirmada the participacao confirmada of this evento participante
	*/
	public void setParticipacaoConfirmada(boolean participacaoConfirmada) {
		_eventoParticipante.setParticipacaoConfirmada(participacaoConfirmada);
	}

	public boolean isNew() {
		return _eventoParticipante.isNew();
	}

	public void setNew(boolean n) {
		_eventoParticipante.setNew(n);
	}

	public boolean isCachedModel() {
		return _eventoParticipante.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_eventoParticipante.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _eventoParticipante.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _eventoParticipante.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_eventoParticipante.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _eventoParticipante.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_eventoParticipante.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new EventoParticipanteWrapper((EventoParticipante)_eventoParticipante.clone());
	}

	public int compareTo(
		br.gov.demoiselle.portal.evento.model.EventoParticipante eventoParticipante) {
		return _eventoParticipante.compareTo(eventoParticipante);
	}

	@Override
	public int hashCode() {
		return _eventoParticipante.hashCode();
	}

	public com.liferay.portal.model.CacheModel<br.gov.demoiselle.portal.evento.model.EventoParticipante> toCacheModel() {
		return _eventoParticipante.toCacheModel();
	}

	public br.gov.demoiselle.portal.evento.model.EventoParticipante toEscapedModel() {
		return new EventoParticipanteWrapper(_eventoParticipante.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _eventoParticipante.toString();
	}

	public java.lang.String toXmlString() {
		return _eventoParticipante.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_eventoParticipante.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public EventoParticipante getWrappedEventoParticipante() {
		return _eventoParticipante;
	}

	public EventoParticipante getWrappedModel() {
		return _eventoParticipante;
	}

	public void resetOriginalValues() {
		_eventoParticipante.resetOriginalValues();
	}

	private EventoParticipante _eventoParticipante;
}