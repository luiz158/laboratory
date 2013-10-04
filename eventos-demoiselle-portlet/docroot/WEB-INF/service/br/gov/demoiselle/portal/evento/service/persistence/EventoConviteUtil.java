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

import br.gov.demoiselle.portal.evento.model.EventoConvite;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the evento convite service. This utility wraps {@link EventoConvitePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoConvitePersistence
 * @see EventoConvitePersistenceImpl
 * @generated
 */
public class EventoConviteUtil {
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
	public static void clearCache(EventoConvite eventoConvite) {
		getPersistence().clearCache(eventoConvite);
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
	public static List<EventoConvite> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EventoConvite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EventoConvite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static EventoConvite update(EventoConvite eventoConvite,
		boolean merge) throws SystemException {
		return getPersistence().update(eventoConvite, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static EventoConvite update(EventoConvite eventoConvite,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(eventoConvite, merge, serviceContext);
	}

	/**
	* Caches the evento convite in the entity cache if it is enabled.
	*
	* @param eventoConvite the evento convite
	*/
	public static void cacheResult(
		br.gov.demoiselle.portal.evento.model.EventoConvite eventoConvite) {
		getPersistence().cacheResult(eventoConvite);
	}

	/**
	* Caches the evento convites in the entity cache if it is enabled.
	*
	* @param eventoConvites the evento convites
	*/
	public static void cacheResult(
		java.util.List<br.gov.demoiselle.portal.evento.model.EventoConvite> eventoConvites) {
		getPersistence().cacheResult(eventoConvites);
	}

	/**
	* Creates a new evento convite with the primary key. Does not add the evento convite to the database.
	*
	* @param eventoConviteId the primary key for the new evento convite
	* @return the new evento convite
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConvite create(
		long eventoConviteId) {
		return getPersistence().create(eventoConviteId);
	}

	/**
	* Removes the evento convite with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoConviteId the primary key of the evento convite
	* @return the evento convite that was removed
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException if a evento convite with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConvite remove(
		long eventoConviteId)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(eventoConviteId);
	}

	public static br.gov.demoiselle.portal.evento.model.EventoConvite updateImpl(
		br.gov.demoiselle.portal.evento.model.EventoConvite eventoConvite,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(eventoConvite, merge);
	}

	/**
	* Returns the evento convite with the primary key or throws a {@link br.gov.demoiselle.portal.evento.NoSuchEventoConviteException} if it could not be found.
	*
	* @param eventoConviteId the primary key of the evento convite
	* @return the evento convite
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException if a evento convite with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConvite findByPrimaryKey(
		long eventoConviteId)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(eventoConviteId);
	}

	/**
	* Returns the evento convite with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventoConviteId the primary key of the evento convite
	* @return the evento convite, or <code>null</code> if a evento convite with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConvite fetchByPrimaryKey(
		long eventoConviteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(eventoConviteId);
	}

	/**
	* Returns all the evento convites where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @return the matching evento convites
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConvite> findByEventoId(
		long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventoId(eventoId);
	}

	/**
	* Returns a range of all the evento convites where eventoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventoId the evento ID
	* @param start the lower bound of the range of evento convites
	* @param end the upper bound of the range of evento convites (not inclusive)
	* @return the range of matching evento convites
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConvite> findByEventoId(
		long eventoId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventoId(eventoId, start, end);
	}

	/**
	* Returns an ordered range of all the evento convites where eventoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventoId the evento ID
	* @param start the lower bound of the range of evento convites
	* @param end the upper bound of the range of evento convites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching evento convites
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConvite> findByEventoId(
		long eventoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventoId(eventoId, start, end, orderByComparator);
	}

	/**
	* Returns the first evento convite in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento convite
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException if a matching evento convite could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConvite findByEventoId_First(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventoId_First(eventoId, orderByComparator);
	}

	/**
	* Returns the first evento convite in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento convite, or <code>null</code> if a matching evento convite could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConvite fetchByEventoId_First(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEventoId_First(eventoId, orderByComparator);
	}

	/**
	* Returns the last evento convite in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento convite
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException if a matching evento convite could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConvite findByEventoId_Last(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventoId_Last(eventoId, orderByComparator);
	}

	/**
	* Returns the last evento convite in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento convite, or <code>null</code> if a matching evento convite could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConvite fetchByEventoId_Last(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEventoId_Last(eventoId, orderByComparator);
	}

	/**
	* Returns the evento convites before and after the current evento convite in the ordered set where eventoId = &#63;.
	*
	* @param eventoConviteId the primary key of the current evento convite
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next evento convite
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException if a evento convite with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static br.gov.demoiselle.portal.evento.model.EventoConvite[] findByEventoId_PrevAndNext(
		long eventoConviteId, long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventoId_PrevAndNext(eventoConviteId, eventoId,
			orderByComparator);
	}

	/**
	* Returns all the evento convites.
	*
	* @return the evento convites
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConvite> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the evento convites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of evento convites
	* @param end the upper bound of the range of evento convites (not inclusive)
	* @return the range of evento convites
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConvite> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the evento convites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of evento convites
	* @param end the upper bound of the range of evento convites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of evento convites
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<br.gov.demoiselle.portal.evento.model.EventoConvite> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the evento convites where eventoId = &#63; from the database.
	*
	* @param eventoId the evento ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEventoId(long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEventoId(eventoId);
	}

	/**
	* Removes all the evento convites from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of evento convites where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @return the number of matching evento convites
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEventoId(long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEventoId(eventoId);
	}

	/**
	* Returns the number of evento convites.
	*
	* @return the number of evento convites
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static EventoConvitePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (EventoConvitePersistence)PortletBeanLocatorUtil.locate(br.gov.demoiselle.portal.evento.service.ClpSerializer.getServletContextName(),
					EventoConvitePersistence.class.getName());

			ReferenceRegistry.registerReference(EventoConviteUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(EventoConvitePersistence persistence) {
	}

	private static EventoConvitePersistence _persistence;
}