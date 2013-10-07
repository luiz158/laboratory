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

import br.gov.demoiselle.portal.evento.model.EventoConfiguracao;
import br.gov.demoiselle.portal.evento.service.EventoConfiguracaoLocalServiceUtil;
import br.gov.demoiselle.portal.evento.service.base.EventoConfiguracaoLocalServiceBaseImpl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the evento configuracao local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link br.gov.demoiselle.portal.evento.service.EventoConfiguracaoLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see br.gov.demoiselle.portal.evento.service.base.EventoConfiguracaoLocalServiceBaseImpl
 * @see br.gov.demoiselle.portal.evento.service.EventoConfiguracaoLocalServiceUtil
 */
public class EventoConfiguracaoLocalServiceImpl
	extends EventoConfiguracaoLocalServiceBaseImpl {
	
	@Override
	public EventoConfiguracao addEventoConfiguracao(EventoConfiguracao eventoConfiguracao) throws SystemException{
		
		long id = CounterLocalServiceUtil.increment(EventoConfiguracao.class.getName());
		eventoConfiguracao.setEventoConfiguracaoId(id);
		return super.addEventoConfiguracao(eventoConfiguracao);
	}
	
	public EventoConfiguracao getEventoConfiguracaoPeloEvento(long eventoId) throws SystemException{
		
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(EventoConfiguracao.class)
				.add(PropertyFactoryUtil.forName("eventoId").eq(eventoId));
		
		List<EventoConfiguracao> lista = EventoConfiguracaoLocalServiceUtil.dynamicQuery(query);
		
		if(lista != null && !lista.isEmpty()){
			return lista.get(0);
		}
		
		return null;
	}
	
}