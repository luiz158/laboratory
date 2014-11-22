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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the evento configuracao local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoConfiguracaoLocalServiceUtil
 * @see br.gov.demoiselle.portal.evento.service.base.EventoConfiguracaoLocalServiceBaseImpl
 * @see br.gov.demoiselle.portal.evento.service.impl.EventoConfiguracaoLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface EventoConfiguracaoLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventoConfiguracaoLocalServiceUtil} to access the evento configuracao local service. Add custom service methods to {@link br.gov.demoiselle.portal.evento.service.impl.EventoConfiguracaoLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the evento configuracao to the database. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracao the evento configuracao
	* @return the evento configuracao that was added
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao addEventoConfiguracao(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new evento configuracao with the primary key. Does not add the evento configuracao to the database.
	*
	* @param eventoConfiguracaoId the primary key for the new evento configuracao
	* @return the new evento configuracao
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao createEventoConfiguracao(
		long eventoConfiguracaoId);

	/**
	* Deletes the evento configuracao with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao that was removed
	* @throws PortalException if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao deleteEventoConfiguracao(
		long eventoConfiguracaoId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the evento configuracao from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracao the evento configuracao
	* @return the evento configuracao that was removed
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao deleteEventoConfiguracao(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

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
		throws com.liferay.portal.kernel.exception.SystemException;

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
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao fetchEventoConfiguracao(
		long eventoConfiguracaoId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento configuracao with the primary key.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao
	* @throws PortalException if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao getEventoConfiguracao(
		long eventoConfiguracaoId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> getEventoConfiguracaos(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of evento configuracaos.
	*
	* @return the number of evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEventoConfiguracaosCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the evento configuracao in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracao the evento configuracao
	* @return the evento configuracao that was updated
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao updateEventoConfiguracao(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the evento configuracao in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracao the evento configuracao
	* @param merge whether to merge the evento configuracao with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the evento configuracao that was updated
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao updateEventoConfiguracao(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao getEventoConfiguracaoPeloEvento(
		long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException;
}