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

import br.gov.demoiselle.portal.evento.model.EventoParticipante;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the evento participante service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoParticipantePersistenceImpl
 * @see EventoParticipanteUtil
 * @generated
 */
public interface EventoParticipantePersistence extends BasePersistence<EventoParticipante> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventoParticipanteUtil} to access the evento participante persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the evento participante in the entity cache if it is enabled.
	*
	* @param eventoParticipante the evento participante
	*/
	public void cacheResult(
		br.gov.demoiselle.portal.evento.model.EventoParticipante eventoParticipante);

	/**
	* Caches the evento participantes in the entity cache if it is enabled.
	*
	* @param eventoParticipantes the evento participantes
	*/
	public void cacheResult(
		java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> eventoParticipantes);

	/**
	* Creates a new evento participante with the primary key. Does not add the evento participante to the database.
	*
	* @param eventoParticipanteId the primary key for the new evento participante
	* @return the new evento participante
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante create(
		long eventoParticipanteId);

	/**
	* Removes the evento participante with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventoParticipanteId the primary key of the evento participante
	* @return the evento participante that was removed
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a evento participante with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante remove(
		long eventoParticipanteId)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	public br.gov.demoiselle.portal.evento.model.EventoParticipante updateImpl(
		br.gov.demoiselle.portal.evento.model.EventoParticipante eventoParticipante,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento participante with the primary key or throws a {@link br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException} if it could not be found.
	*
	* @param eventoParticipanteId the primary key of the evento participante
	* @return the evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a evento participante with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante findByPrimaryKey(
		long eventoParticipanteId)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento participante with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventoParticipanteId the primary key of the evento participante
	* @return the evento participante, or <code>null</code> if a evento participante with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchByPrimaryKey(
		long eventoParticipanteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the evento participantes where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @return the matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findByEventoId(
		long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the evento participantes where eventoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventoId the evento ID
	* @param start the lower bound of the range of evento participantes
	* @param end the upper bound of the range of evento participantes (not inclusive)
	* @return the range of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findByEventoId(
		long eventoId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the evento participantes where eventoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventoId the evento ID
	* @param start the lower bound of the range of evento participantes
	* @param end the upper bound of the range of evento participantes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findByEventoId(
		long eventoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first evento participante in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante findByEventoId_First(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first evento participante in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento participante, or <code>null</code> if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchByEventoId_First(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last evento participante in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante findByEventoId_Last(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last evento participante in the ordered set where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento participante, or <code>null</code> if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchByEventoId_Last(
		long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento participantes before and after the current evento participante in the ordered set where eventoId = &#63;.
	*
	* @param eventoParticipanteId the primary key of the current evento participante
	* @param eventoId the evento ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a evento participante with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante[] findByEventoId_PrevAndNext(
		long eventoParticipanteId, long eventoId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the evento participantes where nome = &#63;.
	*
	* @param nome the nome
	* @return the matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findByNome(
		java.lang.String nome)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the evento participantes where nome = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param nome the nome
	* @param start the lower bound of the range of evento participantes
	* @param end the upper bound of the range of evento participantes (not inclusive)
	* @return the range of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findByNome(
		java.lang.String nome, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the evento participantes where nome = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param nome the nome
	* @param start the lower bound of the range of evento participantes
	* @param end the upper bound of the range of evento participantes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findByNome(
		java.lang.String nome, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first evento participante in the ordered set where nome = &#63;.
	*
	* @param nome the nome
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante findByNome_First(
		java.lang.String nome,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first evento participante in the ordered set where nome = &#63;.
	*
	* @param nome the nome
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento participante, or <code>null</code> if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchByNome_First(
		java.lang.String nome,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last evento participante in the ordered set where nome = &#63;.
	*
	* @param nome the nome
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante findByNome_Last(
		java.lang.String nome,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last evento participante in the ordered set where nome = &#63;.
	*
	* @param nome the nome
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento participante, or <code>null</code> if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchByNome_Last(
		java.lang.String nome,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento participantes before and after the current evento participante in the ordered set where nome = &#63;.
	*
	* @param eventoParticipanteId the primary key of the current evento participante
	* @param nome the nome
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a evento participante with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante[] findByNome_PrevAndNext(
		long eventoParticipanteId, java.lang.String nome,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the evento participantes where email = &#63;.
	*
	* @param email the email
	* @return the matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findByEmail(
		java.lang.String email)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the evento participantes where email = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param email the email
	* @param start the lower bound of the range of evento participantes
	* @param end the upper bound of the range of evento participantes (not inclusive)
	* @return the range of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findByEmail(
		java.lang.String email, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the evento participantes where email = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param email the email
	* @param start the lower bound of the range of evento participantes
	* @param end the upper bound of the range of evento participantes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findByEmail(
		java.lang.String email, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first evento participante in the ordered set where email = &#63;.
	*
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante findByEmail_First(
		java.lang.String email,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first evento participante in the ordered set where email = &#63;.
	*
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evento participante, or <code>null</code> if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchByEmail_First(
		java.lang.String email,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last evento participante in the ordered set where email = &#63;.
	*
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante findByEmail_Last(
		java.lang.String email,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last evento participante in the ordered set where email = &#63;.
	*
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evento participante, or <code>null</code> if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchByEmail_Last(
		java.lang.String email,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento participantes before and after the current evento participante in the ordered set where email = &#63;.
	*
	* @param eventoParticipanteId the primary key of the current evento participante
	* @param email the email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a evento participante with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante[] findByEmail_PrevAndNext(
		long eventoParticipanteId, java.lang.String email,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento participante where eventoId = &#63; and email = &#63; or throws a {@link br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException} if it could not be found.
	*
	* @param eventoId the evento ID
	* @param email the email
	* @return the matching evento participante
	* @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante findByEventoId_Email(
		long eventoId, java.lang.String email)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento participante where eventoId = &#63; and email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param eventoId the evento ID
	* @param email the email
	* @return the matching evento participante, or <code>null</code> if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchByEventoId_Email(
		long eventoId, java.lang.String email)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the evento participante where eventoId = &#63; and email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param eventoId the evento ID
	* @param email the email
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching evento participante, or <code>null</code> if a matching evento participante could not be found
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante fetchByEventoId_Email(
		long eventoId, java.lang.String email, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the evento participantes.
	*
	* @return the evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the evento participantes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of evento participantes
	* @param end the upper bound of the range of evento participantes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<br.gov.demoiselle.portal.evento.model.EventoParticipante> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the evento participantes where eventoId = &#63; from the database.
	*
	* @param eventoId the evento ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEventoId(long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the evento participantes where nome = &#63; from the database.
	*
	* @param nome the nome
	* @throws SystemException if a system exception occurred
	*/
	public void removeByNome(java.lang.String nome)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the evento participantes where email = &#63; from the database.
	*
	* @param email the email
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEmail(java.lang.String email)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the evento participante where eventoId = &#63; and email = &#63; from the database.
	*
	* @param eventoId the evento ID
	* @param email the email
	* @return the evento participante that was removed
	* @throws SystemException if a system exception occurred
	*/
	public br.gov.demoiselle.portal.evento.model.EventoParticipante removeByEventoId_Email(
		long eventoId, java.lang.String email)
		throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the evento participantes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of evento participantes where eventoId = &#63;.
	*
	* @param eventoId the evento ID
	* @return the number of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public int countByEventoId(long eventoId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of evento participantes where nome = &#63;.
	*
	* @param nome the nome
	* @return the number of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public int countByNome(java.lang.String nome)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of evento participantes where email = &#63;.
	*
	* @param email the email
	* @return the number of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public int countByEmail(java.lang.String email)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of evento participantes where eventoId = &#63; and email = &#63;.
	*
	* @param eventoId the evento ID
	* @param email the email
	* @return the number of matching evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public int countByEventoId_Email(long eventoId, java.lang.String email)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of evento participantes.
	*
	* @return the number of evento participantes
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}