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

import br.gov.demoiselle.portal.evento.service.EventoConfiguracaoLocalServiceUtil;

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
public class EventoConfiguracaoClp extends BaseModelImpl<EventoConfiguracao>
	implements EventoConfiguracao {
	public EventoConfiguracaoClp() {
	}

	public Class<?> getModelClass() {
		return EventoConfiguracao.class;
	}

	public String getModelClassName() {
		return EventoConfiguracao.class.getName();
	}

	public long getPrimaryKey() {
		return _eventoConfiguracaoId;
	}

	public void setPrimaryKey(long primaryKey) {
		setEventoConfiguracaoId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_eventoConfiguracaoId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
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

	@Override
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

	public long getEventoConfiguracaoId() {
		return _eventoConfiguracaoId;
	}

	public void setEventoConfiguracaoId(long eventoConfiguracaoId) {
		_eventoConfiguracaoId = eventoConfiguracaoId;
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

	public boolean getAbertoAoPublico() {
		return _abertoAoPublico;
	}

	public boolean isAbertoAoPublico() {
		return _abertoAoPublico;
	}

	public void setAbertoAoPublico(boolean abertoAoPublico) {
		_abertoAoPublico = abertoAoPublico;
	}

	public String getCidadeDoEvento() {
		return _cidadeDoEvento;
	}

	public void setCidadeDoEvento(String cidadeDoEvento) {
		_cidadeDoEvento = cidadeDoEvento;
	}

	public BaseModel<?> getEventoConfiguracaoRemoteModel() {
		return _eventoConfiguracaoRemoteModel;
	}

	public void setEventoConfiguracaoRemoteModel(
		BaseModel<?> eventoConfiguracaoRemoteModel) {
		_eventoConfiguracaoRemoteModel = eventoConfiguracaoRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			EventoConfiguracaoLocalServiceUtil.addEventoConfiguracao(this);
		}
		else {
			EventoConfiguracaoLocalServiceUtil.updateEventoConfiguracao(this);
		}
	}

	@Override
	public EventoConfiguracao toEscapedModel() {
		return (EventoConfiguracao)Proxy.newProxyInstance(EventoConfiguracao.class.getClassLoader(),
			new Class[] { EventoConfiguracao.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		EventoConfiguracaoClp clone = new EventoConfiguracaoClp();

		clone.setEventoConfiguracaoId(getEventoConfiguracaoId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setEventoId(getEventoId());
		clone.setAbertoAoPublico(getAbertoAoPublico());
		clone.setCidadeDoEvento(getCidadeDoEvento());

		return clone;
	}

	public int compareTo(EventoConfiguracao eventoConfiguracao) {
		int value = 0;

		if (getEventoId() < eventoConfiguracao.getEventoId()) {
			value = -1;
		}
		else if (getEventoId() > eventoConfiguracao.getEventoId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		EventoConfiguracaoClp eventoConfiguracao = null;

		try {
			eventoConfiguracao = (EventoConfiguracaoClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = eventoConfiguracao.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{eventoConfiguracaoId=");
		sb.append(getEventoConfiguracaoId());
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
		sb.append(", abertoAoPublico=");
		sb.append(getAbertoAoPublico());
		sb.append(", cidadeDoEvento=");
		sb.append(getCidadeDoEvento());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("br.gov.demoiselle.portal.evento.model.EventoConfiguracao");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>eventoConfiguracaoId</column-name><column-value><![CDATA[");
		sb.append(getEventoConfiguracaoId());
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
			"<column><column-name>abertoAoPublico</column-name><column-value><![CDATA[");
		sb.append(getAbertoAoPublico());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cidadeDoEvento</column-name><column-value><![CDATA[");
		sb.append(getCidadeDoEvento());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _eventoConfiguracaoId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private long _eventoId;
	private boolean _abertoAoPublico;
	private String _cidadeDoEvento;
	private BaseModel<?> _eventoConfiguracaoRemoteModel;
}