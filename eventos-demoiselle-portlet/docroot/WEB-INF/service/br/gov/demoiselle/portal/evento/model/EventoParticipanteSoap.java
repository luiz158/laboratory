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
public class EventoParticipanteSoap implements Serializable {
	public static EventoParticipanteSoap toSoapModel(EventoParticipante model) {
		EventoParticipanteSoap soapModel = new EventoParticipanteSoap();

		soapModel.setEventoParticipanteId(model.getEventoParticipanteId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEventoId(model.getEventoId());
		soapModel.setNome(model.getNome());
		soapModel.setEmail(model.getEmail());
		soapModel.setInstituicaoEmpresa(model.getInstituicaoEmpresa());
		soapModel.setConviteEnviado(model.getConviteEnviado());
		soapModel.setCertificadoImpresso(model.getCertificadoImpresso());
		soapModel.setInscricaoConfirmada(model.getInscricaoConfirmada());
		soapModel.setParticipacaoConfirmada(model.getParticipacaoConfirmada());

		return soapModel;
	}

	public static EventoParticipanteSoap[] toSoapModels(
		EventoParticipante[] models) {
		EventoParticipanteSoap[] soapModels = new EventoParticipanteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EventoParticipanteSoap[][] toSoapModels(
		EventoParticipante[][] models) {
		EventoParticipanteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EventoParticipanteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EventoParticipanteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EventoParticipanteSoap[] toSoapModels(
		List<EventoParticipante> models) {
		List<EventoParticipanteSoap> soapModels = new ArrayList<EventoParticipanteSoap>(models.size());

		for (EventoParticipante model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EventoParticipanteSoap[soapModels.size()]);
	}

	public EventoParticipanteSoap() {
	}

	public long getPrimaryKey() {
		return _eventoParticipanteId;
	}

	public void setPrimaryKey(long pk) {
		setEventoParticipanteId(pk);
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

	private long _eventoParticipanteId;
	private long _companyId;
	private long _userId;
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
}