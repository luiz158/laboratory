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

package br.gov.demoiselle.portal.evento.model.impl;

import br.gov.demoiselle.portal.evento.model.EventoParticipante;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing EventoParticipante in entity cache.
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoParticipante
 * @generated
 */
public class EventoParticipanteCacheModel implements CacheModel<EventoParticipante>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{eventoParticipanteId=");
		sb.append(eventoParticipanteId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", eventoId=");
		sb.append(eventoId);
		sb.append(", nome=");
		sb.append(nome);
		sb.append(", email=");
		sb.append(email);
		sb.append(", instituicaoEmpresa=");
		sb.append(instituicaoEmpresa);
		sb.append(", conviteEnviado=");
		sb.append(conviteEnviado);
		sb.append(", certificadoImpresso=");
		sb.append(certificadoImpresso);
		sb.append(", inscricaoConfirmada=");
		sb.append(inscricaoConfirmada);
		sb.append(", participacaoConfirmada=");
		sb.append(participacaoConfirmada);
		sb.append("}");

		return sb.toString();
	}

	public EventoParticipante toEntityModel() {
		EventoParticipanteImpl eventoParticipanteImpl = new EventoParticipanteImpl();

		eventoParticipanteImpl.setEventoParticipanteId(eventoParticipanteId);
		eventoParticipanteImpl.setCompanyId(companyId);
		eventoParticipanteImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			eventoParticipanteImpl.setCreateDate(null);
		}
		else {
			eventoParticipanteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			eventoParticipanteImpl.setModifiedDate(null);
		}
		else {
			eventoParticipanteImpl.setModifiedDate(new Date(modifiedDate));
		}

		eventoParticipanteImpl.setEventoId(eventoId);

		if (nome == null) {
			eventoParticipanteImpl.setNome(StringPool.BLANK);
		}
		else {
			eventoParticipanteImpl.setNome(nome);
		}

		if (email == null) {
			eventoParticipanteImpl.setEmail(StringPool.BLANK);
		}
		else {
			eventoParticipanteImpl.setEmail(email);
		}

		if (instituicaoEmpresa == null) {
			eventoParticipanteImpl.setInstituicaoEmpresa(StringPool.BLANK);
		}
		else {
			eventoParticipanteImpl.setInstituicaoEmpresa(instituicaoEmpresa);
		}

		eventoParticipanteImpl.setConviteEnviado(conviteEnviado);
		eventoParticipanteImpl.setCertificadoImpresso(certificadoImpresso);
		eventoParticipanteImpl.setInscricaoConfirmada(inscricaoConfirmada);
		eventoParticipanteImpl.setParticipacaoConfirmada(participacaoConfirmada);

		eventoParticipanteImpl.resetOriginalValues();

		return eventoParticipanteImpl;
	}

	public long eventoParticipanteId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long eventoId;
	public String nome;
	public String email;
	public String instituicaoEmpresa;
	public boolean conviteEnviado;
	public boolean certificadoImpresso;
	public boolean inscricaoConfirmada;
	public boolean participacaoConfirmada;
}