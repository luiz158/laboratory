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

package br.gov.demoiselle.portal.evento.service.persistence;

import br.gov.demoiselle.portal.evento.model.EventoConfiguracao;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the evento configuracao service. This utility wraps {@link EventoConfiguracaoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoConfiguracaoPersistence
 * @see EventoConfiguracaoPersistenceImpl
 * @generated
 */
public class EventoConfiguracaoUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(EventoConfiguracao eventoConfiguracao) {
		getPersistence().clearCache(eventoConfiguracao);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EventoConfiguracao> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EventoConfiguracao> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EventoConfiguracao> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static EventoConfiguracao update(
		EventoConfiguracao eventoConfiguracao, boolean merge)
		throws SystemException {
		return getPersistence().update(eventoConfiguracao, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static EventoConfiguracao update(
		EventoConfiguracao eventoConfiguracao, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(eventoConfiguracao, merge, serviceContext);
	}

	/**
	* Caches the evento configuracao in the entity cache if it is enabled.
	*
	* @param eventoConfiguracao the evento configuracao
	*/
	public static void cacheResult(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao) {
		getPersistence().cacheResult(eventoConfiguracao);
	}

	/**
	* Caches the evento configuracaos in the entity cache if it is enabled.
	*
	* @param eventoConfiguracaos the evento configuracaos
	*/
	public static void cacheResult(
		java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> eventoConfiguracaos) {
		getPersistence().cacheResult(eventoConfiguracaos);
	}

	/**
	* Creates a new evento configuracao with the primary key. Does not add the evento configuracao to the database.
	*
	* @param eventoConfiguracaoId the primary key for the new evento configuracao
	* @return the new evento configuracao
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao create(
		long eventoConfiguracaoId) {
		return getPersistence().create(eventoConfiguracaoId);
	}

	/**
	* Removes the evento configuracao with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao that was removed
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao remove(
		long eventoConfiguracaoId)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(eventoConfiguracaoId);
	}

	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao updateImpl(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(eventoConfiguracao, merge);
	}

	/**
	* Returns the evento configuracao with the primary key or throws a {@link br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException} if it could not be found.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao findByPrimaryKey(
		long eventoConfiguracaoId)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(eventoConfiguracaoId);
	}

	/**
	* Returns the evento configuracao with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao, or <code>null</code> if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao fetchByPrimaryKey(
		long eventoConfiguracaoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(eventoConfiguracaoId);
	}

	/**
	* Returns all the evento configuracaos where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @return the matching evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findByEventoId(
		long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventoId(eventoId);
	}

	/**
	* Returns a range of all the evento configuracaos where eventoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventoId the evento ID
	* @param start the lower bound of the range of evento configuracaos
	* @param end the upper bound of the range of evento configuracaos (not inclusive)
	* @return the range of matching evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findByEventoId(
		long eventoId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventoId(eventoId, start, end);
	}

	/**
	* Returns an ordered range of all the evento configuracaos where eventoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventoId the evento ID
	* @param start the lower bound of the range of evento configuracaos
	* @param end the upper bound of the range of evento configuracaos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findByEventoId(
		long eventoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventoId(eventoId, start, end, orderByComparator);
	}

	/**
	* Returns the first evento configuracao in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento configuracao
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a matching evento configuracao could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao findByEventoId_First(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventoId_First(eventoId, orderByComparator);
	}

	/**
	* Returns the first evento configuracao in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento configuracao, or <code>null</code> if a matching evento configuracao could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao fetchByEventoId_First(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEventoId_First(eventoId, orderByComparator);
	}

	/**
	* Returns the last evento configuracao in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento configuracao
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a matching evento configuracao could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao findByEventoId_Last(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventoId_Last(eventoId, orderByComparator);
	}

	/**
	* Returns the last evento configuracao in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento configuracao, or <code>null</code> if a matching evento configuracao could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao fetchByEventoId_Last(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEventoId_Last(eventoId, orderByComparator);
	}

	/**
	* Returns the evento configuracaos before and after the current evento configuracao in the ordered set where eventoId = &#63;.
	*
	* @param eventoConfiguracaoId the primary key of the current evento configuracao
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next evento configuracao
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConfiguracao[] findByEventoId_PrevAndNext(
		long eventoConfiguracaoId, long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventoId_PrevAndNext(eventoConfiguracaoId, eventoId,
			orderByComparator);
	}

	/**
	* Returns all the evento configuracaos.
	*
	* @return the evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the evento configuracaos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of evento configuracaos
	* @param end the upper bound of the range of evento configuracaos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the evento configuracaos where eventoId = &#63; from the database.
	*
	* @param eventoId the evento ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEventoId(long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEventoId(eventoId);
	}

	/**
	* Removes all the evento configuracaos from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of evento configuracaos where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @return the number of matching evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEventoId(long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEventoId(eventoId);
	}

	/**
	* Returns the number of evento configuracaos.
	*
	* @return the number of evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static EventoConfiguracaoPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (EventoConfiguracaoPersistence)PortletBeanLocatorUtil.locate(br.gov.demoiselle.portal.evento.service.ClpSerializer.getServletContextName(),
					EventoConfiguracaoPersistence.class.getName());

			ReferenceRegistry.registerReference(EventoConfiguracaoUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(EventoConfiguracaoPersistence persistence) {
	}

	private static EventoConfiguracaoPersistence _persistence;
}