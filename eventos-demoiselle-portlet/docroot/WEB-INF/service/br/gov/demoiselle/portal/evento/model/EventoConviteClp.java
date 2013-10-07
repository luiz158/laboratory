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

import br.gov.demoiselle.portal.evento.service.EventoConviteLocalServiceUtil;

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
public class EventoConviteClp extends BaseModelImpl<EventoConvite>
	implements EventoConvite {
	public EventoConviteClp() {
	}

	public Class<?> getModelClass() {
		return EventoConvite.class;
	}

	public String getModelClassName() {
		return EventoConvite.class.getName();
	}

	public long getPrimaryKey() {
		return _eventoConviteId;
	}

	public void setPrimaryKey(long primaryKey) {
		setEventoConviteId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_eventoConviteId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
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

	@Override
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

	public long getEventoConviteId() {
		return _eventoConviteId;
	}

	public void setEventoConviteId(long eventoConviteId) {
		_eventoConviteId = eventoConviteId;
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

	public String getTextoConvite() {
		return _textoConvite;
	}

	public void setTextoConvite(String textoConvite) {
		_textoConvite = textoConvite;
	}

	public BaseModel<?> getEventoConviteRemoteModel() {
		return _eventoConviteRemoteModel;
	}

	public void setEventoConviteRemoteModel(
		BaseModel<?> eventoConviteRemoteModel) {
		_eventoConviteRemoteModel = eventoConviteRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			EventoConviteLocalServiceUtil.addEventoConvite(this);
		}
		else {
			EventoConviteLocalServiceUtil.updateEventoConvite(this);
		}
	}

	@Override
	public EventoConvite toEscapedModel() {
		return (EventoConvite)Proxy.newProxyInstance(EventoConvite.class.getClassLoader(),
			new Class[] { EventoConvite.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		EventoConviteClp clone = new EventoConviteClp();

		clone.setEventoConviteId(getEventoConviteId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setEventoId(getEventoId());
		clone.setTextoConvite(getTextoConvite());

		return clone;
	}

	public int compareTo(EventoConvite eventoConvite) {
		int value = 0;

		if (getEventoId() < eventoConvite.getEventoId()) {
			value = -1;
		}
		else if (getEventoId() > eventoConvite.getEventoId()) {
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

		EventoConviteClp eventoConvite = null;

		try {
			eventoConvite = (EventoConviteClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = eventoConvite.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{eventoConviteId=");
		sb.append(getEventoConviteId());
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
		sb.append(", textoConvite=");
		sb.append(getTextoConvite());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("br.gov.demoiselle.portal.evento.model.EventoConvite");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>eventoConviteId</column-name><column-value><![CDATA[");
		sb.append(getEventoConviteId());
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
			"<column><column-name>textoConvite</column-name><column-value><![CDATA[");
		sb.append(getTextoConvite());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _eventoConviteId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private long _eventoId;
	private String _textoConvite;
	private BaseModel<?> _eventoConviteRemoteModel;
}