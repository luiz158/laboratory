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

import br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException;
import br.gov.demoiselle.portal.evento.model.EventoConfiguracao;
import br.gov.demoiselle.portal.evento.model.impl.EventoConfiguracaoImpl;
import br.gov.demoiselle.portal.evento.model.impl.EventoConfiguracaoModelImpl;

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
 * The persistence implementation for the evento configuracao service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoConfiguracaoPersistence
 * @see EventoConfiguracaoUtil
 * @generated
 */
public class EventoConfiguracaoPersistenceImpl extends BasePersistenceImpl<EventoConfiguracao>
	implements EventoConfiguracaoPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EventoConfiguracaoUtil} to access the evento configuracao persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EventoConfiguracaoImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTOID = new FinderPath(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
			EventoConfiguracaoModelImpl.FINDER_CACHE_ENABLED,
			EventoConfiguracaoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEventoId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID =
		new FinderPath(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
			EventoConfiguracaoModelImpl.FINDER_CACHE_ENABLED,
			EventoConfiguracaoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEventoId",
			new String[] { Long.class.getName() },
			EventoConfiguracaoModelImpl.EVENTOID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTOID = new FinderPath(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
			EventoConfiguracaoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventoId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
			EventoConfiguracaoModelImpl.FINDER_CACHE_ENABLED,
			EventoConfiguracaoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
			EventoConfiguracaoModelImpl.FINDER_CACHE_ENABLED,
			EventoConfiguracaoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
			EventoConfiguracaoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the evento configuracao in the entity cache if it is enabled.
	 *
	 * @param eventoConfiguracao the evento configuracao
	 */
	public void cacheResult(EventoConfiguracao eventoConfiguracao) {
		EntityCacheUtil.putResult(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
			EventoConfiguracaoImpl.class, eventoConfiguracao.getPrimaryKey(),
			eventoConfiguracao);

		eventoConfiguracao.resetOriginalValues();
	}

	/**
	 * Caches the evento configuracaos in the entity cache if it is enabled.
	 *
	 * @param eventoConfiguracaos the evento configuracaos
	 */
	public void cacheResult(List<EventoConfiguracao> eventoConfiguracaos) {
		for (EventoConfiguracao eventoConfiguracao : eventoConfiguracaos) {
			if (EntityCacheUtil.getResult(
						EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
						EventoConfiguracaoImpl.class,
						eventoConfiguracao.getPrimaryKey()) == null) {
				cacheResult(eventoConfiguracao);
			}
			else {
				eventoConfiguracao.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all evento configuracaos.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(EventoConfiguracaoImpl.class.getName());
		}

		EntityCacheUtil.clearCache(EventoConfiguracaoImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the evento configuracao.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EventoConfiguracao eventoConfiguracao) {
		EntityCacheUtil.removeResult(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
			EventoConfiguracaoImpl.class, eventoConfiguracao.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<EventoConfiguracao> eventoConfiguracaos) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EventoConfiguracao eventoConfiguracao : eventoConfiguracaos) {
			EntityCacheUtil.removeResult(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
				EventoConfiguracaoImpl.class, eventoConfiguracao.getPrimaryKey());
		}
	}

	/**
	 * Creates a new evento configuracao with the primary key. Does not add the evento configuracao to the database.
	 *
	 * @param eventoConfiguracaoId the primary key for the new evento configuracao
	 * @return the new evento configuracao
	 */
	public EventoConfiguracao create(long eventoConfiguracaoId) {
		EventoConfiguracao eventoConfiguracao = new EventoConfiguracaoImpl();

		eventoConfiguracao.setNew(true);
		eventoConfiguracao.setPrimaryKey(eventoConfiguracaoId);

		return eventoConfiguracao;
	}

	/**
	 * Removes the evento configuracao with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventoConfiguracaoId the primary key of the evento configuracao
	 * @return the evento configuracao that was removed
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a evento configuracao with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConfiguracao remove(long eventoConfiguracaoId)
		throws NoSuchEventoConfiguracaoException, SystemException {
		return remove(Long.valueOf(eventoConfiguracaoId));
	}

	/**
	 * Removes the evento configuracao with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the evento configuracao
	 * @return the evento configuracao that was removed
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a evento configuracao with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public EventoConfiguracao remove(Serializable primaryKey)
		throws NoSuchEventoConfiguracaoException, SystemException {
		Session session = null;

		try {
			session = openSession();

			EventoConfiguracao eventoConfiguracao = (EventoConfiguracao)session.get(EventoConfiguracaoImpl.class,
					primaryKey);

			if (eventoConfiguracao == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventoConfiguracaoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(eventoConfiguracao);
		}
		catch (NoSuchEventoConfiguracaoException nsee) {
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
	protected EventoConfiguracao removeImpl(
		EventoConfiguracao eventoConfiguracao) throws SystemException {
		eventoConfiguracao = toUnwrappedModel(eventoConfiguracao);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, eventoConfiguracao);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(eventoConfiguracao);

		return eventoConfiguracao;
	}

	@Override
	public EventoConfiguracao updateImpl(
		br.gov.demoiselle.portal.evento.model.EventoConfiguracao eventoConfiguracao,
		boolean merge) throws SystemException {
		eventoConfiguracao = toUnwrappedModel(eventoConfiguracao);

		boolean isNew = eventoConfiguracao.isNew();

		EventoConfiguracaoModelImpl eventoConfiguracaoModelImpl = (EventoConfiguracaoModelImpl)eventoConfiguracao;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, eventoConfiguracao, merge);

			eventoConfiguracao.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EventoConfiguracaoModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((eventoConfiguracaoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(eventoConfiguracaoModelImpl.getOriginalEventoId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTOID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID,
					args);

				args = new Object[] {
						Long.valueOf(eventoConfiguracaoModelImpl.getEventoId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTOID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID,
					args);
			}
		}

		EntityCacheUtil.putResult(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
			EventoConfiguracaoImpl.class, eventoConfiguracao.getPrimaryKey(),
			eventoConfiguracao);

		return eventoConfiguracao;
	}

	protected EventoConfiguracao toUnwrappedModel(
		EventoConfiguracao eventoConfiguracao) {
		if (eventoConfiguracao instanceof EventoConfiguracaoImpl) {
			return eventoConfiguracao;
		}

		EventoConfiguracaoImpl eventoConfiguracaoImpl = new EventoConfiguracaoImpl();

		eventoConfiguracaoImpl.setNew(eventoConfiguracao.isNew());
		eventoConfiguracaoImpl.setPrimaryKey(eventoConfiguracao.getPrimaryKey());

		eventoConfiguracaoImpl.setEventoConfiguracaoId(eventoConfiguracao.getEventoConfiguracaoId());
		eventoConfiguracaoImpl.setCompanyId(eventoConfiguracao.getCompanyId());
		eventoConfiguracaoImpl.setUserId(eventoConfiguracao.getUserId());
		eventoConfiguracaoImpl.setCreateDate(eventoConfiguracao.getCreateDate());
		eventoConfiguracaoImpl.setModifiedDate(eventoConfiguracao.getModifiedDate());
		eventoConfiguracaoImpl.setEventoId(eventoConfiguracao.getEventoId());
		eventoConfiguracaoImpl.setAbertoAoPublico(eventoConfiguracao.isAbertoAoPublico());
		eventoConfiguracaoImpl.setCidadeDoEvento(eventoConfiguracao.getCidadeDoEvento());

		return eventoConfiguracaoImpl;
	}

	/**
	 * Returns the evento configuracao with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the evento configuracao
	 * @return the evento configuracao
	 * @throws com.liferay.portal.NoSuchModelException if a evento configuracao with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public EventoConfiguracao findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the evento configuracao with the primary key or throws a {@link br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException} if it could not be found.
	 *
	 * @param eventoConfiguracaoId the primary key of the evento configuracao
	 * @return the evento configuracao
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a evento configuracao with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConfiguracao findByPrimaryKey(long eventoConfiguracaoId)
		throws NoSuchEventoConfiguracaoException, SystemException {
		EventoConfiguracao eventoConfiguracao = fetchByPrimaryKey(eventoConfiguracaoId);

		if (eventoConfiguracao == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					eventoConfiguracaoId);
			}

			throw new NoSuchEventoConfiguracaoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				eventoConfiguracaoId);
		}

		return eventoConfiguracao;
	}

	/**
	 * Returns the evento configuracao with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the evento configuracao
	 * @return the evento configuracao, or <code>null</code> if a evento configuracao with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public EventoConfiguracao fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the evento configuracao with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventoConfiguracaoId the primary key of the evento configuracao
	 * @return the evento configuracao, or <code>null</code> if a evento configuracao with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConfiguracao fetchByPrimaryKey(long eventoConfiguracaoId)
		throws SystemException {
		EventoConfiguracao eventoConfiguracao = (EventoConfiguracao)EntityCacheUtil.getResult(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
				EventoConfiguracaoImpl.class, eventoConfiguracaoId);

		if (eventoConfiguracao == _nullEventoConfiguracao) {
			return null;
		}

		if (eventoConfiguracao == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				eventoConfiguracao = (EventoConfiguracao)session.get(EventoConfiguracaoImpl.class,
						Long.valueOf(eventoConfiguracaoId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (eventoConfiguracao != null) {
					cacheResult(eventoConfiguracao);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(EventoConfiguracaoModelImpl.ENTITY_CACHE_ENABLED,
						EventoConfiguracaoImpl.class, eventoConfiguracaoId,
						_nullEventoConfiguracao);
				}

				closeSession(session);
			}
		}

		return eventoConfiguracao;
	}

	/**
	 * Returns all the evento configuracaos where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @return the matching evento configuracaos
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventoConfiguracao> findByEventoId(long eventoId)
		throws SystemException {
		return findByEventoId(eventoId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	public List<EventoConfiguracao> findByEventoId(long eventoId, int start,
		int end) throws SystemException {
		return findByEventoId(eventoId, start, end, null);
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
	public List<EventoConfiguracao> findByEventoId(long eventoId, int start,
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

		List<EventoConfiguracao> list = (List<EventoConfiguracao>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (EventoConfiguracao eventoConfiguracao : list) {
				if ((eventoId != eventoConfiguracao.getEventoId())) {
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

			query.append(_SQL_SELECT_EVENTOCONFIGURACAO_WHERE);

			query.append(_FINDER_COLUMN_EVENTOID_EVENTOID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(EventoConfiguracaoModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventoId);

				list = (List<EventoConfiguracao>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first evento configuracao in the ordered set where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evento configuracao
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoConfiguracaoException if a matching evento configuracao could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConfiguracao findByEventoId_First(long eventoId,
		OrderByComparator orderByComparator)
		throws NoSuchEventoConfiguracaoException, SystemException {
		EventoConfiguracao eventoConfiguracao = fetchByEventoId_First(eventoId,
				orderByComparator);

		if (eventoConfiguracao != null) {
			return eventoConfiguracao;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventoId=");
		msg.append(eventoId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoConfiguracaoException(msg.toString());
	}

	/**
	 * Returns the first evento configuracao in the ordered set where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evento configuracao, or <code>null</code> if a matching evento configuracao could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConfiguracao fetchByEventoId_First(long eventoId,
		OrderByComparator orderByComparator) throws SystemException {
		List<EventoConfiguracao> list = findByEventoId(eventoId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public EventoConfiguracao findByEventoId_Last(long eventoId,
		OrderByComparator orderByComparator)
		throws NoSuchEventoConfiguracaoException, SystemException {
		EventoConfiguracao eventoConfiguracao = fetchByEventoId_Last(eventoId,
				orderByComparator);

		if (eventoConfiguracao != null) {
			return eventoConfiguracao;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventoId=");
		msg.append(eventoId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoConfiguracaoException(msg.toString());
	}

	/**
	 * Returns the last evento configuracao in the ordered set where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evento configuracao, or <code>null</code> if a matching evento configuracao could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoConfiguracao fetchByEventoId_Last(long eventoId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEventoId(eventoId);

		List<EventoConfiguracao> list = findByEventoId(eventoId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public EventoConfiguracao[] findByEventoId_PrevAndNext(
		long eventoConfiguracaoId, long eventoId,
		OrderByComparator orderByComparator)
		throws NoSuchEventoConfiguracaoException, SystemException {
		EventoConfiguracao eventoConfiguracao = findByPrimaryKey(eventoConfiguracaoId);

		Session session = null;

		try {
			session = openSession();

			EventoConfiguracao[] array = new EventoConfiguracaoImpl[3];

			array[0] = getByEventoId_PrevAndNext(session, eventoConfiguracao,
					eventoId, orderByComparator, true);

			array[1] = eventoConfiguracao;

			array[2] = getByEventoId_PrevAndNext(session, eventoConfiguracao,
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

	protected EventoConfiguracao getByEventoId_PrevAndNext(Session session,
		EventoConfiguracao eventoConfiguracao, long eventoId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTOCONFIGURACAO_WHERE);

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
			query.append(EventoConfiguracaoModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(eventoId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eventoConfiguracao);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventoConfiguracao> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the evento configuracaos.
	 *
	 * @return the evento configuracaos
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventoConfiguracao> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<EventoConfiguracao> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<EventoConfiguracao> findAll(int start, int end,
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

		List<EventoConfiguracao> list = (List<EventoConfiguracao>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EVENTOCONFIGURACAO);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EVENTOCONFIGURACAO.concat(EventoConfiguracaoModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<EventoConfiguracao>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<EventoConfiguracao>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the evento configuracaos where eventoId = &#63; from the database.
	 *
	 * @param eventoId the evento ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventoId(long eventoId) throws SystemException {
		for (EventoConfiguracao eventoConfiguracao : findByEventoId(eventoId)) {
			remove(eventoConfiguracao);
		}
	}

	/**
	 * Removes all the evento configuracaos from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (EventoConfiguracao eventoConfiguracao : findAll()) {
			remove(eventoConfiguracao);
		}
	}

	/**
	 * Returns the number of evento configuracaos where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @return the number of matching evento configuracaos
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEventoId(long eventoId) throws SystemException {
		Object[] finderArgs = new Object[] { eventoId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTOID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTOCONFIGURACAO_WHERE);

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
	 * Returns the number of evento configuracaos.
	 *
	 * @return the number of evento configuracaos
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EVENTOCONFIGURACAO);

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
	 * Initializes the evento configuracao persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.br.gov.demoiselle.portal.evento.model.EventoConfiguracao")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<EventoConfiguracao>> listenersList = new ArrayList<ModelListener<EventoConfiguracao>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<EventoConfiguracao>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(EventoConfiguracaoImpl.class.getName());
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
	private static final String _SQL_SELECT_EVENTOCONFIGURACAO = "SELECT eventoConfiguracao FROM EventoConfiguracao eventoConfiguracao";
	private static final String _SQL_SELECT_EVENTOCONFIGURACAO_WHERE = "SELECT eventoConfiguracao FROM EventoConfiguracao eventoConfiguracao WHERE ";
	private static final String _SQL_COUNT_EVENTOCONFIGURACAO = "SELECT COUNT(eventoConfiguracao) FROM EventoConfiguracao eventoConfiguracao";
	private static final String _SQL_COUNT_EVENTOCONFIGURACAO_WHERE = "SELECT COUNT(eventoConfiguracao) FROM EventoConfiguracao eventoConfiguracao WHERE ";
	private static final String _FINDER_COLUMN_EVENTOID_EVENTOID_2 = "eventoConfiguracao.eventoId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "eventoConfiguracao.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EventoConfiguracao exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EventoConfiguracao exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(EventoConfiguracaoPersistenceImpl.class);
	private static EventoConfiguracao _nullEventoConfiguracao = new EventoConfiguracaoImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<EventoConfiguracao> toCacheModel() {
				return _nullEventoConfiguracaoCacheModel;
			}
		};

	private static CacheModel<EventoConfiguracao> _nullEventoConfiguracaoCacheModel =
		new CacheModel<EventoConfiguracao>() {
			public EventoConfiguracao toEntityModel() {
				return _nullEventoConfiguracao;
			}
		};
}