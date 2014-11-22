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

package br.gov.demoiselle.portal.evento.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link EventoParticipanteLocalService}.
 * </p>
 *
 * @author    Clovis Lemes Ferreira Junior
 * @see       EventoParticipanteLocalService
 * @generated
 */
public class EventoParticipanteLocalServiceWrapper
	implements EventoParticipanteLocalService,
		ServiceWrapper<EventoParticipanteLocalService> {
	public EventoParticipanteLocalServiceWrapper(
		EventoParticipanteLocalService eventoParticipanteLocalService) {
		_eventoParticipanteLocalService = eventoParticipanteLocalService;
	}

	/**
	* Adds the evento participante to the database. Also notifies the appropriate model listeners.
	*
	* @param eventoParticipante the evento participante
	* @return the evento participante that was added
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante addEventoParticipante(
		br.gov.demoiselle.portal.evento.model.EventoParticipante eventoParticipante)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.addEventoParticipante(eventoParticipante);
	}

	/**
	* Creates a new evento participante with the primary key. Does not add the evento participante to the database.
	*
	* @param eventoParticipanteId the primary key for the new evento participante
	* @return the new evento participante
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante createEventoParticipante(
		long eventoParticipanteId) {
		return _eventoParticipanteLocalService.createEventoParticipante(eventoParticipanteId);
	}

	/**
	* Deletes the evento participante with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoParticipanteId the primary key of the evento participante
	* @return the evento participante that was removed
	* @throws PortalException if a evento participante with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante deleteEventoParticipante(
		long eventoParticipanteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.deleteEventoParticipante(eventoParticipanteId);
	}

	/**
	* Deletes the evento participante from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoParticipante the evento participante
	* @return the evento participante that was removed
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante deleteEventoParticipante(
		br.gov.demoiselle.portal.evento.model.EventoParticipante eventoParticipante)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.deleteEventoParticipante(eventoParticipante);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eventoParticipanteLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.dynamicQueryCount(dynamicQuery);
	}

	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchEventoParticipante(
		long eventoParticipanteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.fetchEventoParticipante(eventoParticipanteId);
	}

	/**
	* Returns the evento participante with the primary key.
	*
	* @param eventoParticipanteId the primary key of the evento participante
	* @return the evento participante
	* @throws PortalException if a evento participante with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante getEventoParticipante(
		long eventoParticipanteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.getEventoParticipante(eventoParticipanteId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the evento participantes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of evento participantes
	* @param end the upper bound of the range of evento participantes (not inclusive)
	* @return the range of evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> getEventoParticipantes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.getEventoParticipantes(start, end);
	}

	/**
	* Returns the number of evento participantes.
	*
	* @return the number of evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public int getEventoParticipantesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.getEventoParticipantesCount();
	}

	/**
	* Updates the evento participante in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eventoParticipante the evento participante
	* @return the evento participante that was updated
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante updateEventoParticipante(
		br.gov.demoiselle.portal.evento.model.EventoParticipante eventoParticipante)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.updateEventoParticipante(eventoParticipante);
	}

	/**
	* Updates the evento participante in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eventoParticipante the evento participante
	* @param merge whether to merge the evento participante with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the evento participante that was updated
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante updateEventoParticipante(
		br.gov.demoiselle.portal.evento.model.EventoParticipante eventoParticipante,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.updateEventoParticipante(eventoParticipante,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _eventoParticipanteLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_eventoParticipanteLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _eventoParticipanteLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public java.util.List<com.liferay.portlet.calendar.model.CalEvent> getListaDeEventos()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.getListaDeEventos();
	}

	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> getListaDeParticipantes(
		long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.getListaDeParticipantes(eventoId);
	}

	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> getListaDeParticipantes(
		long eventoId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.getListaDeParticipantes(eventoId,
			start, end);
	}

	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> getListaDeParticipantes(
		java.lang.String email)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.getListaDeParticipantes(email);
	}

	public br.gov.demoiselle.portal.evento.model.EventoParticipante getEventoParticipante(
		long eventoId, java.lang.String email)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventoParticipanteLocalService.getEventoParticipante(eventoId,
			email);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public EventoParticipanteLocalService getWrappedEventoParticipanteLocalService() {
		return _eventoParticipanteLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedEventoParticipanteLocalService(
		EventoParticipanteLocalService eventoParticipanteLocalService) {
		_eventoParticipanteLocalService = eventoParticipanteLocalService;
	}

	public EventoParticipanteLocalService getWrappedService() {
		return _eventoParticipanteLocalService;
	}

	public void setWrappedService(
		EventoParticipanteLocalService eventoParticipanteLocalService) {
		_eventoParticipanteLocalService = eventoParticipanteLocalService;
	}

	private EventoParticipanteLocalService _eventoParticipanteLocalService;
}