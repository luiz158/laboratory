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

import br.gov.demoiselle.portal.evento.model.EventoConfiguracao;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing EventoConfiguracao in entity cache.
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoConfiguracao
 * @generated
 */
public class EventoConfiguracaoCacheModel implements CacheModel<EventoConfiguracao>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{eventoConfiguracaoId=");
		sb.append(eventoConfiguracaoId);
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
		sb.append(", abertoAoPublico=");
		sb.append(abertoAoPublico);
		sb.append(", cidadeDoEvento=");
		sb.append(cidadeDoEvento);
		sb.append("}");

		return sb.toString();
	}

	public EventoConfiguracao toEntityModel() {
		EventoConfiguracaoImpl eventoConfiguracaoImpl = new EventoConfiguracaoImpl();

		eventoConfiguracaoImpl.setEventoConfiguracaoId(eventoConfiguracaoId);
		eventoConfiguracaoImpl.setCompanyId(companyId);
		eventoConfiguracaoImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			eventoConfiguracaoImpl.setCreateDate(null);
		}
		else {
			eventoConfiguracaoImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			eventoConfiguracaoImpl.setModifiedDate(null);
		}
		else {
			eventoConfiguracaoImpl.setModifiedDate(new Date(modifiedDate));
		}

		eventoConfiguracaoImpl.setEventoId(eventoId);
		eventoConfiguracaoImpl.setAbertoAoPublico(abertoAoPublico);

		if (cidadeDoEvento == null) {
			eventoConfiguracaoImpl.setCidadeDoEvento(StringPool.BLANK);
		}
		else {
			eventoConfiguracaoImpl.setCidadeDoEvento(cidadeDoEvento);
		}

		eventoConfiguracaoImpl.resetOriginalValues();

		return eventoConfiguracaoImpl;
	}

	public long eventoConfiguracaoId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long eventoId;
	public boolean abertoAoPublico;
	public String cidadeDoEvento;
}