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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the evento configuracao local service. This utility wraps {@link br.gov.demoiselle.portal.evento.service.impl.EventoConfiguracaoLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoConfiguracaoLocalService
 * @see br.gov.demoiselle.portal.evento.service.base.EventoConfiguracaoLocalServiceBaseImpl
 * @see br.gov.demoiselle.portal.evento.service.impl.EventoConfiguracaoLocalServiceImpl
 * @generated
 */
public class EventoConfiguracaoLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link br.gov.demoiselle.portal.evento.service.impl.EventoConfiguracaoLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the evento configuracao to the database. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracao the evento configuracao
	* @return the evento configuracao that was added
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao addEventoConfiguracao(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addEventoConfiguracao(eventoConfiguracao);
	}

	/**
	* Creates a new evento configuracao with the primary key. Does not add the evento configuracao to the database.
	*
	* @param eventoConfiguracaoId the primary key for the new evento configuracao
	* @return the new evento configuracao
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao createEventoConfiguracao(
		long eventoConfiguracaoId) {
		return getService().createEventoConfiguracao(eventoConfiguracaoId);
	}

	/**
	* Deletes the evento configuracao with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao that was removed
	* @throws PortalException if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao deleteEventoConfiguracao(
		long eventoConfiguracaoId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteEventoConfiguracao(eventoConfiguracaoId);
	}

	/**
	* Deletes the evento configuracao from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracao the evento configuracao
	* @return the evento configuracao that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao deleteEventoConfiguracao(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteEventoConfiguracao(eventoConfiguracao);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao fetchEventoConfiguracao(
		long eventoConfiguracaoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchEventoConfiguracao(eventoConfiguracaoId);
	}

	/**
	* Returns the evento configuracao with the primary key.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao
	* @throws PortalException if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao getEventoConfiguracao(
		long eventoConfiguracaoId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getEventoConfiguracao(eventoConfiguracaoId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the evento configuracaos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of evento configuracaos
	* @param end the upper bound of the range of evento configuracaos (not inclusive)
	* @return the range of evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> getEventoConfiguracaos(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getEventoConfiguracaos(start, end);
	}

	/**
	* Returns the number of evento configuracaos.
	*
	* @return the number of evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public static int getEventoConfiguracaosCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getEventoConfiguracaosCount();
	}

	/**
	* Updates the evento configuracao in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracao the evento configuracao
	* @return the evento configuracao that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao updateEventoConfiguracao(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateEventoConfiguracao(eventoConfiguracao);
	}

	/**
	* Updates the evento configuracao in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracao the evento configuracao
	* @param merge whether to merge the evento configuracao with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the evento configuracao that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao updateEventoConfiguracao(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateEventoConfiguracao(eventoConfiguracao, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao getEventoConfiguracaoPeloEvento(
		long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getEventoConfiguracaoPeloEvento(eventoId);
	}

	public static void clearService() {
		_service = null;
	}

	public static EventoConfiguracaoLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					EventoConfiguracaoLocalService.class.getName());

			if (invokableLocalService instanceof EventoConfiguracaoLocalService) {
				_service = (EventoConfiguracaoLocalService)invokableLocalService;
			}
			else {
				_service = new EventoConfiguracaoLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(EventoConfiguracaoLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(EventoConfiguracaoLocalService service) {
	}

	private static EventoConfiguracaoLocalService _service;
}