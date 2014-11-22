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

import br.gov.demoiselle.portal.evento.NoSuchEventoConviteException;
import br.gov.demoiselle.portal.evento.model.EventoConvite;
import br.gov.demoiselle.portal.evento.model.impl.EventoConviteImpl;
import br.gov.demoiselle.portal.evento.model.impl.EventoConviteModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the evento convite service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoConvitePersistence
 * @see EventoConviteUtil
 * @generated
 */
public class EventoConvitePersistenceImpl extends BasePersistenceImpl<EventoConvite>
	implements EventoConvitePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EventoConviteUtil} to access the evento convite persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EventoConviteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTOID = new FinderPath(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
			EventoConviteModelImpl.FINDER_CACHE_ENABLED,
			EventoConviteImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEventoId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID =
		new FinderPath(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
			EventoConviteModelImpl.FINDER_CACHE_ENABLED,
			EventoConviteImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByEventoId", new String[] { Long.class.getName() },
			EventoConviteModelImpl.EVENTOID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTOID = new FinderPath(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
			EventoConviteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventoId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
			EventoConviteModelImpl.FINDER_CACHE_ENABLED,
			EventoConviteImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
			EventoConviteModelImpl.FINDER_CACHE_ENABLED,
			EventoConviteImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
			EventoConviteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the evento convite in the entity cache if it is enabled.
	 *
	 * @param eventoConvite the evento convite
	 */
	public void cacheResult(EventoConvite eventoConvite) {
		EntityCacheUtil.putResult(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
			EventoConviteImpl.class, eventoConvite.getPrimaryKey(),
			eventoConvite);

		eventoConvite.resetOriginalValues();
	}

	/**
	 * Caches the evento convites in the entity cache if it is enabled.
	 *
	 * @param eventoConvites the evento convites
	 */
	public void cacheResult(List<EventoConvite> eventoConvites) {
		for (EventoConvite eventoConvite : eventoConvites) {
			if (EntityCacheUtil.getResult(
						EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
						EventoConviteImpl.class, eventoConvite.getPrimaryKey()) == null) {
				cacheResult(eventoConvite);
			}
			else {
				eventoConvite.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all evento convites.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(EventoConviteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(EventoConviteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the evento convite.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EventoConvite eventoConvite) {
		EntityCacheUtil.removeResult(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
			EventoConviteImpl.class, eventoConvite.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<EventoConvite> eventoConvites) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EventoConvite eventoConvite : eventoConvites) {
			EntityCacheUtil.removeResult(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
				EventoConviteImpl.class, eventoConvite.getPrimaryKey());
		}
	}

	/**
	 * Creates a new evento convite with the primary key. Does not add the evento convite to the database.
	 *
	 * @param eventoConviteId the primary key for the new evento convite
	 * @return the new evento convite
	 */
	public EventoConvite create(long eventoConviteId) {
		EventoConvite eventoConvite = new EventoConviteImpl();

		eventoConvite.setNew(true);
		eventoConvite.setPrimaryKey(eventoConviteId);

		return eventoConvite;
	}

	/**
	 * Removes the evento convite with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventoConviteId the primary key of the evento convite
	 * @return the evento convite that was removed
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException if a evento convite with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConvite remove(long eventoConviteId)
		throws NoSuchEventoConviteException, SystemException {
		return remove(Long.valueOf(eventoConviteId));
	}

	/**
	 * Removes the evento convite with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the evento convite
	 * @return the evento convite that was removed
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException if a evento convite with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public EventoConvite remove(Serializable primaryKey)
		throws NoSuchEventoConviteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			EventoConvite eventoConvite = (EventoConvite)session.get(EventoConviteImpl.class,
					primaryKey);

			if (eventoConvite == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventoConviteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(eventoConvite);
		}
		catch (NoSuchEventoConviteException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected EventoConvite removeImpl(EventoConvite eventoConvite)
		throws SystemException {
		eventoConvite = toUnwrappedModel(eventoConvite);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, eventoConvite);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(eventoConvite);

		return eventoConvite;
	}

	@Override
	public EventoConvite updateImpl(
		br.gov.demoiselle.portal.evento.model.EventoConvite eventoConvite,
		boolean merge) throws SystemException {
		eventoConvite = toUnwrappedModel(eventoConvite);

		boolean isNew = eventoConvite.isNew();

		EventoConviteModelImpl eventoConviteModelImpl = (EventoConviteModelImpl)eventoConvite;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, eventoConvite, merge);

			eventoConvite.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EventoConviteModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((eventoConviteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(eventoConviteModelImpl.getOriginalEventoId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTOID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID,
					args);

				args = new Object[] {
						Long.valueOf(eventoConviteModelImpl.getEventoId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTOID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID,
					args);
			}
		}

		EntityCacheUtil.putResult(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
			EventoConviteImpl.class, eventoConvite.getPrimaryKey(),
			eventoConvite);

		return eventoConvite;
	}

	protected EventoConvite toUnwrappedModel(EventoConvite eventoConvite) {
		if (eventoConvite instanceof EventoConviteImpl) {
			return eventoConvite;
		}

		EventoConviteImpl eventoConviteImpl = new EventoConviteImpl();

		eventoConviteImpl.setNew(eventoConvite.isNew());
		eventoConviteImpl.setPrimaryKey(eventoConvite.getPrimaryKey());

		eventoConviteImpl.setEventoConviteId(eventoConvite.getEventoConviteId());
		eventoConviteImpl.setCompanyId(eventoConvite.getCompanyId());
		eventoConviteImpl.setUserId(eventoConvite.getUserId());
		eventoConviteImpl.setCreateDate(eventoConvite.getCreateDate());
		eventoConviteImpl.setModifiedDate(eventoConvite.getModifiedDate());
		eventoConviteImpl.setEventoId(eventoConvite.getEventoId());
		eventoConviteImpl.setTextoConvite(eventoConvite.getTextoConvite());

		return eventoConviteImpl;
	}

	/**
	 * Returns the evento convite with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the evento convite
	 * @return the evento convite
	 * @throws com.liferay.portal.NoSuchModelException if a evento convite with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public EventoConvite findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the evento convite with the primary key or throws a {@link br.gov.demoiselle.portal.evento.NoSuchEventoConviteException} if it could not be found.
	 *
	 * @param eventoConviteId the primary key of the evento convite
	 * @return the evento convite
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoConviteException if a evento convite with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConvite findByPrimaryKey(long eventoConviteId)
		throws NoSuchEventoConviteException, SystemException {
		EventoConvite eventoConvite = fetchByPrimaryKey(eventoConviteId);

		if (eventoConvite == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + eventoConviteId);
			}

			throw new NoSuchEventoConviteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				eventoConviteId);
		}

		return eventoConvite;
	}

	/**
	 * Returns the evento convite with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the evento convite
	 * @return the evento convite, or <code>null</code> if a evento convite with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public EventoConvite fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the evento convite with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventoConviteId the primary key of the evento convite
	 * @return the evento convite, or <code>null</code> if a evento convite with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConvite fetchByPrimaryKey(long eventoConviteId)
		throws SystemException {
		EventoConvite eventoConvite = (EventoConvite)EntityCacheUtil.getResult(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
				EventoConviteImpl.class, eventoConviteId);

		if (eventoConvite == _nullEventoConvite) {
			return null;
		}

		if (eventoConvite == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				eventoConvite = (EventoConvite)session.get(EventoConviteImpl.class,
						Long.valueOf(eventoConviteId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (eventoConvite != null) {
					cacheResult(eventoConvite);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(EventoConviteModelImpl.ENTITY_CACHE_ENABLED,
						EventoConviteImpl.class, eventoConviteId,
						_nullEventoConvite);
				}

				closeSession(session);
			}
		}

		return eventoConvite;
	}

	/**
	 * Returns all the evento convites where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @return the matching evento convites
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventoConvite> findByEventoId(long eventoId)
		throws SystemException {
		return findByEventoId(eventoId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	public List<EventoConvite> findByEventoId(long eventoId, int start, int end)
		throws SystemException {
		return findByEventoId(eventoId, start, end, null);
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
	public List<EventoConvite> findByEventoId(long eventoId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID;
			finderArgs = new Object[] { eventoId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTOID;
			finderArgs = new Object[] { eventoId, start, end, orderByComparator };
		}

		List<EventoConvite> list = (List<EventoConvite>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (EventoConvite eventoConvite : list) {
				if ((eventoId != eventoConvite.getEventoId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_EVENTOCONVITE_WHERE);

			query.append(_FINDER_COLUMN_EVENTOID_EVENTOID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(EventoConviteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventoId);

				list = (List<EventoConvite>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
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
	public EventoConvite findByEventoId_First(long eventoId,
		OrderByComparator orderByComparator)
		throws NoSuchEventoConviteException, SystemException {
		EventoConvite eventoConvite = fetchByEventoId_First(eventoId,
				orderByComparator);

		if (eventoConvite != null) {
			return eventoConvite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventoId=");
		msg.append(eventoId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoConviteException(msg.toString());
	}

	/**
	 * Returns the first evento convite in the ordered set where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evento convite, or <code>null</code> if a matching evento convite could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConvite fetchByEventoId_First(long eventoId,
		OrderByComparator orderByComparator) throws SystemException {
		List<EventoConvite> list = findByEventoId(eventoId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public EventoConvite findByEventoId_Last(long eventoId,
		OrderByComparator orderByComparator)
		throws NoSuchEventoConviteException, SystemException {
		EventoConvite eventoConvite = fetchByEventoId_Last(eventoId,
				orderByComparator);

		if (eventoConvite != null) {
			return eventoConvite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventoId=");
		msg.append(eventoId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoConviteException(msg.toString());
	}

	/**
	 * Returns the last evento convite in the ordered set where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evento convite, or <code>null</code> if a matching evento convite could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConvite fetchByEventoId_Last(long eventoId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEventoId(eventoId);

		List<EventoConvite> list = findByEventoId(eventoId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public EventoConvite[] findByEventoId_PrevAndNext(long eventoConviteId,
		long eventoId, OrderByComparator orderByComparator)
		throws NoSuchEventoConviteException, SystemException {
		EventoConvite eventoConvite = findByPrimaryKey(eventoConviteId);

		Session session = null;

		try {
			session = openSession();

			EventoConvite[] array = new EventoConviteImpl[3];

			array[0] = getByEventoId_PrevAndNext(session, eventoConvite,
					eventoId, orderByComparator, true);

			array[1] = eventoConvite;

			array[2] = getByEventoId_PrevAndNext(session, eventoConvite,
					eventoId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventoConvite getByEventoId_PrevAndNext(Session session,
		EventoConvite eventoConvite, long eventoId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTOCONVITE_WHERE);

		query.append(_FINDER_COLUMN_EVENTOID_EVENTOID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(EventoConviteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(eventoId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eventoConvite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventoConvite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the evento convites.
	 *
	 * @return the evento convites
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventoConvite> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<EventoConvite> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<EventoConvite> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<EventoConvite> list = (List<EventoConvite>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EVENTOCONVITE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EVENTOCONVITE.concat(EventoConviteModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<EventoConvite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<EventoConvite>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the evento convites where eventoId = &#63; from the database.
	 *
	 * @param eventoId the evento ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventoId(long eventoId) throws SystemException {
		for (EventoConvite eventoConvite : findByEventoId(eventoId)) {
			remove(eventoConvite);
		}
	}

	/**
	 * Removes all the evento convites from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (EventoConvite eventoConvite : findAll()) {
			remove(eventoConvite);
		}
	}

	/**
	 * Returns the number of evento convites where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @return the number of matching evento convites
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEventoId(long eventoId) throws SystemException {
		Object[] finderArgs = new Object[] { eventoId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTOID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTOCONVITE_WHERE);

			query.append(_FINDER_COLUMN_EVENTOID_EVENTOID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventoId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EVENTOID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of evento convites.
	 *
	 * @return the number of evento convites
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EVENTOCONVITE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the evento convite persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.br.gov.demoiselle.portal.evento.model.EventoConvite")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<EventoConvite>> listenersList = new ArrayList<ModelListener<EventoConvite>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<EventoConvite>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(EventoConviteImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = EventoConfiguracaoPersistence.class)
	protected EventoConfiguracaoPersistence eventoConfiguracaoPersistence;
	@BeanReference(type = EventoConvitePersistence.class)
	protected EventoConvitePersistence eventoConvitePersistence;
	@BeanReference(type = EventoParticipantePersistence.class)
	protected EventoParticipantePersistence eventoParticipantePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_EVENTOCONVITE = "SELECT eventoConvite FROM EventoConvite eventoConvite";
	private static final String _SQL_SELECT_EVENTOCONVITE_WHERE = "SELECT eventoConvite FROM EventoConvite eventoConvite WHERE ";
	private static final String _SQL_COUNT_EVENTOCONVITE = "SELECT COUNT(eventoConvite) FROM EventoConvite eventoConvite";
	private static final String _SQL_COUNT_EVENTOCONVITE_WHERE = "SELECT COUNT(eventoConvite) FROM EventoConvite eventoConvite WHERE ";
	private static final String _FINDER_COLUMN_EVENTOID_EVENTOID_2 = "eventoConvite.eventoId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "eventoConvite.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EventoConvite exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EventoConvite exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(EventoConvitePersistenceImpl.class);
	private static EventoConvite _nullEventoConvite = new EventoConviteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<EventoConvite> toCacheModel() {
				return _nullEventoConviteCacheModel;
			}
		};

	private static CacheModel<EventoConvite> _nullEventoConviteCacheModel = new CacheModel<EventoConvite>() {
			public EventoConvite toEntityModel() {
				return _nullEventoConvite;
			}
		};
}