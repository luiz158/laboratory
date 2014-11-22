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

package br.gov.demoiselle.portal.evento.service.impl;

import java.util.List;

import br.gov.demoiselle.portal.evento.model.EventoParticipante;
import br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil;
import br.gov.demoiselle.portal.evento.service.base.EventoParticipanteLocalServiceBaseImpl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;

/**
 * The implementation of the evento participante local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see br.gov.demoiselle.portal.evento.service.base.EventoParticipanteLocalServiceBaseImpl
 * @see br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil
 */
public class EventoParticipanteLocalServiceImpl extends EventoParticipanteLocalServiceBaseImpl {
	
	@Override
	public EventoParticipante addEventoParticipante(EventoParticipante eventoParticipante) throws SystemException{
		
		long id = CounterLocalServiceUtil.increment(EventoParticipante.class.getName());
		eventoParticipante.setEventoParticipanteId(id);
		return super.addEventoParticipante(eventoParticipante);
	}
	
	public List<CalEvent> getListaDeEventos() throws SystemException{
		return CalEventLocalServiceUtil.getCalEvents(0, CalEventLocalServiceUtil.getCalEventsCount());
	}
	
	@SuppressWarnings("unchecked")
	public List<EventoParticipante> getListaDeParticipantes(long eventoId) throws SystemException{
		return EventoParticipanteLocalServiceUtil.dynamicQuery(construirConsultaDeParticipantes(eventoId));
	}
	
	@SuppressWarnings("unchecked")
	public List<EventoParticipante> getListaDeParticipantes(long eventoId, int start, int end) throws SystemException{
		return EventoParticipanteLocalServiceUtil.dynamicQuery(construirConsultaDeParticipantes(eventoId), start, end);
	}
	
	@SuppressWarnings("unchecked")
	public List<EventoParticipante> getListaDeParticipantes(String email) throws SystemException {
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(EventoParticipante.class)
				.add(PropertyFactoryUtil.forName("email").eq(email));
		
		return EventoParticipanteLocalServiceUtil.dynamicQuery(query);
	}
	
	@SuppressWarnings("unchecked")
	public EventoParticipante getEventoParticipante(long eventoId, String email) throws SystemException {
		
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(EventoParticipante.class)
				.add(PropertyFactoryUtil.forName("email").eq(email))
				.add(PropertyFactoryUtil.forName("eventoId").eq(eventoId));
		
		List<EventoParticipante> lista = EventoParticipanteLocalServiceUtil.dynamicQuery(query);
		
		if(lista != null && !lista.isEmpty()){
			return lista.get(0);
		}
		
		return null;
		
	}
	
	private DynamicQuery construirConsultaDeParticipantes(long eventoId){
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(EventoParticipante.class)
				.add(PropertyFactoryUtil.forName("eventoId").eq(eventoId));
		
		return query;
	}
	
}