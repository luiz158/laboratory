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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Clovis Lemes Ferreira Junior
 * @generated
 */
public class EventoConviteSoap implements Serializable {
	public static EventoConviteSoap toSoapModel(EventoConvite model) {
		EventoConviteSoap soapModel = new EventoConviteSoap();

		soapModel.setEventoConviteId(model.getEventoConviteId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEventoId(model.getEventoId());
		soapModel.setTextoConvite(model.getTextoConvite());

		return soapModel;
	}

	public static EventoConviteSoap[] toSoapModels(EventoConvite[] models) {
		EventoConviteSoap[] soapModels = new EventoConviteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EventoConviteSoap[][] toSoapModels(EventoConvite[][] models) {
		EventoConviteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EventoConviteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EventoConviteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EventoConviteSoap[] toSoapModels(List<EventoConvite> models) {
		List<EventoConviteSoap> soapModels = new ArrayList<EventoConviteSoap>(models.size());

		for (EventoConvite model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EventoConviteSoap[soapModels.size()]);
	}

	public EventoConviteSoap() {
	}

	public long getPrimaryKey() {
		return _eventoConviteId;
	}

	public void setPrimaryKey(long pk) {
		setEventoConviteId(pk);
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

	private long _eventoConviteId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _eventoId;
	private String _textoConvite;
}