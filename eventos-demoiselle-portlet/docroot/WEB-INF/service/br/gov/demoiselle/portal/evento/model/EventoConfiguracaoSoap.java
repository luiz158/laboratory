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
public class EventoConfiguracaoSoap implements Serializable {
	public static EventoConfiguracaoSoap toSoapModel(EventoConfiguracao model) {
		EventoConfiguracaoSoap soapModel = new EventoConfiguracaoSoap();

		soapModel.setEventoConfiguracaoId(model.getEventoConfiguracaoId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEventoId(model.getEventoId());
		soapModel.setAbertoAoPublico(model.getAbertoAoPublico());
		soapModel.setCidadeDoEvento(model.getCidadeDoEvento());

		return soapModel;
	}

	public static EventoConfiguracaoSoap[] toSoapModels(
		EventoConfiguracao[] models) {
		EventoConfiguracaoSoap[] soapModels = new EventoConfiguracaoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EventoConfiguracaoSoap[][] toSoapModels(
		EventoConfiguracao[][] models) {
		EventoConfiguracaoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EventoConfiguracaoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EventoConfiguracaoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EventoConfiguracaoSoap[] toSoapModels(
		List<EventoConfiguracao> models) {
		List<EventoConfiguracaoSoap> soapModels = new ArrayList<EventoConfiguracaoSoap>(models.size());

		for (EventoConfiguracao model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EventoConfiguracaoSoap[soapModels.size()]);
	}

	public EventoConfiguracaoSoap() {
	}

	public long getPrimaryKey() {
		return _eventoConfiguracaoId;
	}

	public void setPrimaryKey(long pk) {
		setEventoConfiguracaoId(pk);
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

	private long _eventoConfiguracaoId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _eventoId;
	private boolean _abertoAoPublico;
	private String _cidadeDoEvento;
}