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

import br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Clovis Lemes Ferreira Junior
 */
public class EventoParticipanteClp extends BaseModelImpl<EventoParticipante>
	implements EventoParticipante {
	public EventoParticipanteClp() {
	}

	public Class<?> getModelClass() {
		return EventoParticipante.class;
	}

	public String getModelClassName() {
		return EventoParticipante.class.getName();
	}

	public long getPrimaryKey() {
		return _eventoParticipanteId;
	}

	public void setPrimaryKey(long primaryKey) {
		setEventoParticipanteId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_eventoParticipanteId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
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

	@Override
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

	public long getEventoParticipanteId() {
		return _eventoParticipanteId;
	}

	public void setEventoParticipanteId(long eventoParticipanteId) {
		_eventoParticipanteId = eventoParticipanteId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getEventoId() {
		return _eventoId;
	}

	public void setEventoId(long eventoId) {
		_eventoId = eventoId;
	}

	public String getNome() {
		return _nome;
	}

	public void setNome(String nome) {
		_nome = nome;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getInstituicaoEmpresa() {
		return _instituicaoEmpresa;
	}

	public void setInstituicaoEmpresa(String instituicaoEmpresa) {
		_instituicaoEmpresa = instituicaoEmpresa;
	}

	public boolean getConviteEnviado() {
		return _conviteEnviado;
	}

	public boolean isConviteEnviado() {
		return _conviteEnviado;
	}

	public void setConviteEnviado(boolean conviteEnviado) {
		_conviteEnviado = conviteEnviado;
	}

	public boolean getCertificadoImpresso() {
		return _certificadoImpresso;
	}

	public boolean isCertificadoImpresso() {
		return _certificadoImpresso;
	}

	public void setCertificadoImpresso(boolean certificadoImpresso) {
		_certificadoImpresso = certificadoImpresso;
	}

	public boolean getInscricaoConfirmada() {
		return _inscricaoConfirmada;
	}

	public boolean isInscricaoConfirmada() {
		return _inscricaoConfirmada;
	}

	public void setInscricaoConfirmada(boolean inscricaoConfirmada) {
		_inscricaoConfirmada = inscricaoConfirmada;
	}

	public boolean getParticipacaoConfirmada() {
		return _participacaoConfirmada;
	}

	public boolean isParticipacaoConfirmada() {
		return _participacaoConfirmada;
	}

	public void setParticipacaoConfirmada(boolean participacaoConfirmada) {
		_participacaoConfirmada = participacaoConfirmada;
	}

	public BaseModel<?> getEventoParticipanteRemoteModel() {
		return _eventoParticipanteRemoteModel;
	}

	public void setEventoParticipanteRemoteModel(
		BaseModel<?> eventoParticipanteRemoteModel) {
		_eventoParticipanteRemoteModel = eventoParticipanteRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			EventoParticipanteLocalServiceUtil.addEventoParticipante(this);
		}
		else {
			EventoParticipanteLocalServiceUtil.updateEventoParticipante(this);
		}
	}

	@Override
	public EventoParticipante toEscapedModel() {
		return (EventoParticipante)Proxy.newProxyInstance(EventoParticipante.class.getClassLoader(),
			new Class[] { EventoParticipante.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		EventoParticipanteClp clone = new EventoParticipanteClp();

		clone.setEventoParticipanteId(getEventoParticipanteId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setEventoId(getEventoId());
		clone.setNome(getNome());
		clone.setEmail(getEmail());
		clone.setInstituicaoEmpresa(getInstituicaoEmpresa());
		clone.setConviteEnviado(getConviteEnviado());
		clone.setCertificadoImpresso(getCertificadoImpresso());
		clone.setInscricaoConfirmada(getInscricaoConfirmada());
		clone.setParticipacaoConfirmada(getParticipacaoConfirmada());

		return clone;
	}

	public int compareTo(EventoParticipante eventoParticipante) {
		int value = 0;

		if (getEventoId() < eventoParticipante.getEventoId()) {
			value = -1;
		}
		else if (getEventoId() > eventoParticipante.getEventoId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = getNome().compareTo(eventoParticipante.getNome());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		EventoParticipanteClp eventoParticipante = null;

		try {
			eventoParticipante = (EventoParticipanteClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = eventoParticipante.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{eventoParticipanteId=");
		sb.append(getEventoParticipanteId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", eventoId=");
		sb.append(getEventoId());
		sb.append(", nome=");
		sb.append(getNome());
		sb.append(", email=");
		sb.append(getEmail());
		sb.append(", instituicaoEmpresa=");
		sb.append(getInstituicaoEmpresa());
		sb.append(", conviteEnviado=");
		sb.append(getConviteEnviado());
		sb.append(", certificadoImpresso=");
		sb.append(getCertificadoImpresso());
		sb.append(", inscricaoConfirmada=");
		sb.append(getInscricaoConfirmada());
		sb.append(", participacaoConfirmada=");
		sb.append(getParticipacaoConfirmada());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("br.gov.demoiselle.portal.evento.model.EventoParticipante");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>eventoParticipanteId</column-name><column-value><![CDATA[");
		sb.append(getEventoParticipanteId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>eventoId</column-name><column-value><![CDATA[");
		sb.append(getEventoId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nome</column-name><column-value><![CDATA[");
		sb.append(getNome());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>email</column-name><column-value><![CDATA[");
		sb.append(getEmail());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>instituicaoEmpresa</column-name><column-value><![CDATA[");
		sb.append(getInstituicaoEmpresa());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>conviteEnviado</column-name><column-value><![CDATA[");
		sb.append(getConviteEnviado());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>certificadoImpresso</column-name><column-value><![CDATA[");
		sb.append(getCertificadoImpresso());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inscricaoConfirmada</column-name><column-value><![CDATA[");
		sb.append(getInscricaoConfirmada());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>participacaoConfirmada</column-name><column-value><![CDATA[");
		sb.append(getParticipacaoConfirmada());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _eventoParticipanteId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private long _eventoId;
	private String _nome;
	private String _email;
	private String _instituicaoEmpresa;
	private boolean _conviteEnviado;
	private boolean _certificadoImpresso;
	private boolean _inscricaoConfirmada;
	private boolean _participacaoConfirmada;
	private BaseModel<?> _eventoParticipanteRemoteModel;
}