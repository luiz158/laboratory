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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the evento configuracao service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoConfiguracaoPersistenceImpl
 * @see EventoConfiguracaoUtil
 * @generated
 */
public interface EventoConfiguracaoPersistence extends BasePersistence<EventoConfiguracao> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventoConfiguracaoUtil} to access the evento configuracao persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the evento configuracao in the entity cache if it is enabled.
	*
	* @param eventoConfiguracao the evento configuracao
	*/
	public void cacheResult(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao);

	/**
	* Caches the evento configuracaos in the entity cache if it is enabled.
	*
	* @param eventoConfiguracaos the evento configuracaos
	*/
	public void cacheResult(
		java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> eventoConfiguracaos);

	/**
	* Creates a new evento configuracao with the primary key. Does not add the evento configuracao to the database.
	*
	* @param eventoConfiguracaoId the primary key for the new evento configuracao
	* @return the new evento configuracao
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao create(
		long eventoConfiguracaoId);

	/**
	* Removes the evento configuracao with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao that was removed
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao remove(
		long eventoConfiguracaoId)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException;

	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao updateImpl(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento configuracao with the primary key or throws a {@link br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException} if it could not be found.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao findByPrimaryKey(
		long eventoConfiguracaoId)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento configuracao with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventoConfiguracaoId the primary key of the evento configuracao
	* @return the evento configuracao, or <code>null</code> if a evento configuracao with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao fetchByPrimaryKey(
		long eventoConfiguracaoId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the evento configuracaos where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @return the matching evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findByEventoId(
		long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findByEventoId(
		long eventoId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findByEventoId(
		long eventoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first evento configuracao in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento configuracao
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a matching evento configuracao could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao findByEventoId_First(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first evento configuracao in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento configuracao, or <code>null</code> if a matching evento configuracao could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao fetchByEventoId_First(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last evento configuracao in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento configuracao
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a matching evento configuracao could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao findByEventoId_Last(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last evento configuracao in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento configuracao, or <code>null</code> if a matching evento configuracao could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao fetchByEventoId_Last(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public br.gov.demoiselle.portal.evento.model.EventoConfiguracao[] findByEventoId_PrevAndNext(
		long eventoConfiguracaoId, long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the evento configuracaos.
	*
	* @return the evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoConfiguracao> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the evento configuracaos where eventoId = &#63; from the database.
	*
	* @param eventoId the evento ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEventoId(long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the evento configuracaos from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of evento configuracaos where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @return the number of matching evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public int countByEventoId(long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of evento configuracaos.
	*
	* @return the number of evento configuracaos
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}