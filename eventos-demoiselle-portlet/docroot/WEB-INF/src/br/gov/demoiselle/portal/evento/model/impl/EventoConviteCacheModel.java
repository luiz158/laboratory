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

import br.gov.demoiselle.portal.evento.model.EventoConvite;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing EventoConvite in entity cache.
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoConvite
 * @generated
 */
public class EventoConviteCacheModel implements CacheModel<EventoConvite>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{eventoConviteId=");
		sb.append(eventoConviteId);
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
		sb.append(", textoConvite=");
		sb.append(textoConvite);
		sb.append("}");

		return sb.toString();
	}

	public EventoConvite toEntityModel() {
		EventoConviteImpl eventoConviteImpl = new EventoConviteImpl();

		eventoConviteImpl.setEventoConviteId(eventoConviteId);
		eventoConviteImpl.setCompanyId(companyId);
		eventoConviteImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			eventoConviteImpl.setCreateDate(null);
		}
		else {
			eventoConviteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			eventoConviteImpl.setModifiedDate(null);
		}
		else {
			eventoConviteImpl.setModifiedDate(new Date(modifiedDate));
		}

		eventoConviteImpl.setEventoId(eventoId);

		if (textoConvite == null) {
			eventoConviteImpl.setTextoConvite(StringPool.BLANK);
		}
		else {
			eventoConviteImpl.setTextoConvite(textoConvite);
		}

		eventoConviteImpl.resetOriginalValues();

		return eventoConviteImpl;
	}

	public long eventoConviteId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long eventoId;
	public String textoConvite;
}