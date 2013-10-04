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

import br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException;
import br.gov.demoiselle.portal.evento.model.EventoParticipante;
import br.gov.demoiselle.portal.evento.model.impl.EventoParticipanteImpl;
import br.gov.demoiselle.portal.evento.model.impl.EventoParticipanteModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
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
 * The persistence implementation for the evento participante service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Clovis Lemes Ferreira Junior
 * @see EventoParticipantePersistence
 * @see EventoParticipanteUtil
 * @generated
 */
public class EventoParticipantePersistenceImpl extends BasePersistenceImpl<EventoParticipante>
	implements EventoParticipantePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EventoParticipanteUtil} to access the evento participante persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EventoParticipanteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EVENTOID = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED,
			EventoParticipanteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEventoId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID =
		new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED,
			EventoParticipanteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEventoId",
			new String[] { Long.class.getName() },
			EventoParticipanteModelImpl.EVENTOID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTOID = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventoId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NOME = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED,
			EventoParticipanteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNome",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NOME = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED,
			EventoParticipanteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByNome",
			new String[] { String.class.getName() },
			EventoParticipanteModelImpl.NOME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NOME = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNome",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAIL = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED,
			EventoParticipanteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEmail",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED,
			EventoParticipanteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEmail",
			new String[] { String.class.getName() },
			EventoParticipanteModelImpl.EMAIL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EMAIL = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmail",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_EVENTOID_EMAIL = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED,
			EventoParticipanteImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByEventoId_Email",
			new String[] { Long.class.getName(), String.class.getName() },
			EventoParticipanteModelImpl.EVENTOID_COLUMN_BITMASK |
			EventoParticipanteModelImpl.EMAIL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTOID_EMAIL = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventoId_Email",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED,
			EventoParticipanteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED,
			EventoParticipanteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the evento participante in the entity cache if it is enabled.
	 *
	 * @param eventoParticipante the evento participante
	 */
	public void cacheResult(EventoParticipante eventoParticipante) {
		EntityCacheUtil.putResult(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteImpl.class, eventoParticipante.getPrimaryKey(),
			eventoParticipante);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTOID_EMAIL,
			new Object[] {
				Long.valueOf(eventoParticipante.getEventoId()),
				
			eventoParticipante.getEmail()
			}, eventoParticipante);

		eventoParticipante.resetOriginalValues();
	}

	/**
	 * Caches the evento participantes in the entity cache if it is enabled.
	 *
	 * @param eventoParticipantes the evento participantes
	 */
	public void cacheResult(List<EventoParticipante> eventoParticipantes) {
		for (EventoParticipante eventoParticipante : eventoParticipantes) {
			if (EntityCacheUtil.getResult(
						EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
						EventoParticipanteImpl.class,
						eventoParticipante.getPrimaryKey()) == null) {
				cacheResult(eventoParticipante);
			}
			else {
				eventoParticipante.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all evento participantes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(EventoParticipanteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(EventoParticipanteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the evento participante.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EventoParticipante eventoParticipante) {
		EntityCacheUtil.removeResult(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteImpl.class, eventoParticipante.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(eventoParticipante);
	}

	@Override
	public void clearCache(List<EventoParticipante> eventoParticipantes) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EventoParticipante eventoParticipante : eventoParticipantes) {
			EntityCacheUtil.removeResult(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
				EventoParticipanteImpl.class, eventoParticipante.getPrimaryKey());

			clearUniqueFindersCache(eventoParticipante);
		}
	}

	protected void clearUniqueFindersCache(
		EventoParticipante eventoParticipante) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EVENTOID_EMAIL,
			new Object[] {
				Long.valueOf(eventoParticipante.getEventoId()),
				
			eventoParticipante.getEmail()
			});
	}

	/**
	 * Creates a new evento participante with the primary key. Does not add the evento participante to the database.
	 *
	 * @param eventoParticipanteId the primary key for the new evento participante
	 * @return the new evento participante
	 */
	public EventoParticipante create(long eventoParticipanteId) {
		EventoParticipante eventoParticipante = new EventoParticipanteImpl();

		eventoParticipante.setNew(true);
		eventoParticipante.setPrimaryKey(eventoParticipanteId);

		return eventoParticipante;
	}

	/**
	 * Removes the evento participante with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventoParticipanteId the primary key of the evento participante
	 * @return the evento participante that was removed
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a evento participante with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante remove(long eventoParticipanteId)
		throws NoSuchEventoParticipanteException, SystemException {
		return remove(Long.valueOf(eventoParticipanteId));
	}

	/**
	 * Removes the evento participante with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the evento participante
	 * @return the evento participante that was removed
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a evento participante with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public EventoParticipante remove(Serializable primaryKey)
		throws NoSuchEventoParticipanteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			EventoParticipante eventoParticipante = (EventoParticipante)session.get(EventoParticipanteImpl.class,
					primaryKey);

			if (eventoParticipante == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventoParticipanteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(eventoParticipante);
		}
		catch (NoSuchEventoParticipanteException nsee) {
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
	protected EventoParticipante removeImpl(
		EventoParticipante eventoParticipante) throws SystemException {
		eventoParticipante = toUnwrappedModel(eventoParticipante);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, eventoParticipante);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(eventoParticipante);

		return eventoParticipante;
	}

	@Override
	public EventoParticipante updateImpl(
		br.gov.demoiselle.portal.evento.model.EventoParticipante eventoParticipante,
		boolean merge) throws SystemException {
		eventoParticipante = toUnwrappedModel(eventoParticipante);

		boolean isNew = eventoParticipante.isNew();

		EventoParticipanteModelImpl eventoParticipanteModelImpl = (EventoParticipanteModelImpl)eventoParticipante;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, eventoParticipante, merge);

			eventoParticipante.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EventoParticipanteModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((eventoParticipanteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(eventoParticipanteModelImpl.getOriginalEventoId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTOID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID,
					args);

				args = new Object[] {
						Long.valueOf(eventoParticipanteModelImpl.getEventoId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTOID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EVENTOID,
					args);
			}

			if ((eventoParticipanteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NOME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventoParticipanteModelImpl.getOriginalNome()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NOME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NOME,
					args);

				args = new Object[] { eventoParticipanteModelImpl.getNome() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NOME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NOME,
					args);
			}

			if ((eventoParticipanteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventoParticipanteModelImpl.getOriginalEmail()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAIL, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL,
					args);

				args = new Object[] { eventoParticipanteModelImpl.getEmail() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAIL, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL,
					args);
			}
		}

		EntityCacheUtil.putResult(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
			EventoParticipanteImpl.class, eventoParticipante.getPrimaryKey(),
			eventoParticipante);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTOID_EMAIL,
				new Object[] {
					Long.valueOf(eventoParticipante.getEventoId()),
					
				eventoParticipante.getEmail()
				}, eventoParticipante);
		}
		else {
			if ((eventoParticipanteModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_EVENTOID_EMAIL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(eventoParticipanteModelImpl.getOriginalEventoId()),
						
						eventoParticipanteModelImpl.getOriginalEmail()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EVENTOID_EMAIL,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EVENTOID_EMAIL,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTOID_EMAIL,
					new Object[] {
						Long.valueOf(eventoParticipante.getEventoId()),
						
					eventoParticipante.getEmail()
					}, eventoParticipante);
			}
		}

		return eventoParticipante;
	}

	protected EventoParticipante toUnwrappedModel(
		EventoParticipante eventoParticipante) {
		if (eventoParticipante instanceof EventoParticipanteImpl) {
			return eventoParticipante;
		}

		EventoParticipanteImpl eventoParticipanteImpl = new EventoParticipanteImpl();

		eventoParticipanteImpl.setNew(eventoParticipante.isNew());
		eventoParticipanteImpl.setPrimaryKey(eventoParticipante.getPrimaryKey());

		eventoParticipanteImpl.setEventoParticipanteId(eventoParticipante.getEventoParticipanteId());
		eventoParticipanteImpl.setCompanyId(eventoParticipante.getCompanyId());
		eventoParticipanteImpl.setUserId(eventoParticipante.getUserId());
		eventoParticipanteImpl.setCreateDate(eventoParticipante.getCreateDate());
		eventoParticipanteImpl.setModifiedDate(eventoParticipante.getModifiedDate());
		eventoParticipanteImpl.setEventoId(eventoParticipante.getEventoId());
		eventoParticipanteImpl.setNome(eventoParticipante.getNome());
		eventoParticipanteImpl.setEmail(eventoParticipante.getEmail());
		eventoParticipanteImpl.setInstituicaoEmpresa(eventoParticipante.getInstituicaoEmpresa());
		eventoParticipanteImpl.setConviteEnviado(eventoParticipante.isConviteEnviado());
		eventoParticipanteImpl.setCertificadoImpresso(eventoParticipante.isCertificadoImpresso());
		eventoParticipanteImpl.setInscricaoConfirmada(eventoParticipante.isInscricaoConfirmada());
		eventoParticipanteImpl.setParticipacaoConfirmada(eventoParticipante.isParticipacaoConfirmada());

		return eventoParticipanteImpl;
	}

	/**
	 * Returns the evento participante with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the evento participante
	 * @return the evento participante
	 * @throws com.liferay.portal.NoSuchModelException if a evento participante with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public EventoParticipante findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the evento participante with the primary key or throws a {@link br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException} if it could not be found.
	 *
	 * @param eventoParticipanteId the primary key of the evento participante
	 * @return the evento participante
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a evento participante with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante findByPrimaryKey(long eventoParticipanteId)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = fetchByPrimaryKey(eventoParticipanteId);

		if (eventoParticipante == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					eventoParticipanteId);
			}

			throw new NoSuchEventoParticipanteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				eventoParticipanteId);
		}

		return eventoParticipante;
	}

	/**
	 * Returns the evento participante with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the evento participante
	 * @return the evento participante, or <code>null</code> if a evento participante with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public EventoParticipante fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the evento participante with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventoParticipanteId the primary key of the evento participante
	 * @return the evento participante, or <code>null</code> if a evento participante with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante fetchByPrimaryKey(long eventoParticipanteId)
		throws SystemException {
		EventoParticipante eventoParticipante = (EventoParticipante)EntityCacheUtil.getResult(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
				EventoParticipanteImpl.class, eventoParticipanteId);

		if (eventoParticipante == _nullEventoParticipante) {
			return null;
		}

		if (eventoParticipante == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				eventoParticipante = (EventoParticipante)session.get(EventoParticipanteImpl.class,
						Long.valueOf(eventoParticipanteId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (eventoParticipante != null) {
					cacheResult(eventoParticipante);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(EventoParticipanteModelImpl.ENTITY_CACHE_ENABLED,
						EventoParticipanteImpl.class, eventoParticipanteId,
						_nullEventoParticipante);
				}

				closeSession(session);
			}
		}

		return eventoParticipante;
	}

	/**
	 * Returns all the evento participantes where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @return the matching evento participantes
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventoParticipante> findByEventoId(long eventoId)
		throws SystemException {
		return findByEventoId(eventoId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	public List<EventoParticipante> findByEventoId(long eventoId, int start,
		int end) throws SystemException {
		return findByEventoId(eventoId, start, end, null);
	}

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
	public List<EventoParticipante> findByEventoId(long eventoId, int start,
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

		List<EventoParticipante> list = (List<EventoParticipante>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (EventoParticipante eventoParticipante : list) {
				if ((eventoId != eventoParticipante.getEventoId())) {
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

			query.append(_SQL_SELECT_EVENTOPARTICIPANTE_WHERE);

			query.append(_FINDER_COLUMN_EVENTOID_EVENTOID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(EventoParticipanteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventoId);

				list = (List<EventoParticipante>)QueryUtil.list(q,
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
	 * Returns the first evento participante in the ordered set where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evento participante
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante findByEventoId_First(long eventoId,
		OrderByComparator orderByComparator)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = fetchByEventoId_First(eventoId,
				orderByComparator);

		if (eventoParticipante != null) {
			return eventoParticipante;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventoId=");
		msg.append(eventoId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoParticipanteException(msg.toString());
	}

	/**
	 * Returns the first evento participante in the ordered set where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evento participante, or <code>null</code> if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante fetchByEventoId_First(long eventoId,
		OrderByComparator orderByComparator) throws SystemException {
		List<EventoParticipante> list = findByEventoId(eventoId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last evento participante in the ordered set where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evento participante
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante findByEventoId_Last(long eventoId,
		OrderByComparator orderByComparator)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = fetchByEventoId_Last(eventoId,
				orderByComparator);

		if (eventoParticipante != null) {
			return eventoParticipante;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventoId=");
		msg.append(eventoId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoParticipanteException(msg.toString());
	}

	/**
	 * Returns the last evento participante in the ordered set where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evento participante, or <code>null</code> if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante fetchByEventoId_Last(long eventoId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEventoId(eventoId);

		List<EventoParticipante> list = findByEventoId(eventoId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	public EventoParticipante[] findByEventoId_PrevAndNext(
		long eventoParticipanteId, long eventoId,
		OrderByComparator orderByComparator)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = findByPrimaryKey(eventoParticipanteId);

		Session session = null;

		try {
			session = openSession();

			EventoParticipante[] array = new EventoParticipanteImpl[3];

			array[0] = getByEventoId_PrevAndNext(session, eventoParticipante,
					eventoId, orderByComparator, true);

			array[1] = eventoParticipante;

			array[2] = getByEventoId_PrevAndNext(session, eventoParticipante,
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

	protected EventoParticipante getByEventoId_PrevAndNext(Session session,
		EventoParticipante eventoParticipante, long eventoId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTOPARTICIPANTE_WHERE);

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
			query.append(EventoParticipanteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(eventoId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eventoParticipante);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventoParticipante> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the evento participantes where nome = &#63;.
	 *
	 * @param nome the nome
	 * @return the matching evento participantes
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventoParticipante> findByNome(String nome)
		throws SystemException {
		return findByNome(nome, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	public List<EventoParticipante> findByNome(String nome, int start, int end)
		throws SystemException {
		return findByNome(nome, start, end, null);
	}

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
	public List<EventoParticipante> findByNome(String nome, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NOME;
			finderArgs = new Object[] { nome };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NOME;
			finderArgs = new Object[] { nome, start, end, orderByComparator };
		}

		List<EventoParticipante> list = (List<EventoParticipante>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (EventoParticipante eventoParticipante : list) {
				if (!Validator.equals(nome, eventoParticipante.getNome())) {
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

			query.append(_SQL_SELECT_EVENTOPARTICIPANTE_WHERE);

			if (nome == null) {
				query.append(_FINDER_COLUMN_NOME_NOME_1);
			}
			else {
				if (nome.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NOME_NOME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NOME_NOME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(EventoParticipanteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (nome != null) {
					qPos.add(nome);
				}

				list = (List<EventoParticipante>)QueryUtil.list(q,
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
	 * Returns the first evento participante in the ordered set where nome = &#63;.
	 *
	 * @param nome the nome
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evento participante
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante findByNome_First(String nome,
		OrderByComparator orderByComparator)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = fetchByNome_First(nome,
				orderByComparator);

		if (eventoParticipante != null) {
			return eventoParticipante;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("nome=");
		msg.append(nome);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoParticipanteException(msg.toString());
	}

	/**
	 * Returns the first evento participante in the ordered set where nome = &#63;.
	 *
	 * @param nome the nome
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evento participante, or <code>null</code> if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante fetchByNome_First(String nome,
		OrderByComparator orderByComparator) throws SystemException {
		List<EventoParticipante> list = findByNome(nome, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last evento participante in the ordered set where nome = &#63;.
	 *
	 * @param nome the nome
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evento participante
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante findByNome_Last(String nome,
		OrderByComparator orderByComparator)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = fetchByNome_Last(nome,
				orderByComparator);

		if (eventoParticipante != null) {
			return eventoParticipante;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("nome=");
		msg.append(nome);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoParticipanteException(msg.toString());
	}

	/**
	 * Returns the last evento participante in the ordered set where nome = &#63;.
	 *
	 * @param nome the nome
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evento participante, or <code>null</code> if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante fetchByNome_Last(String nome,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByNome(nome);

		List<EventoParticipante> list = findByNome(nome, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	public EventoParticipante[] findByNome_PrevAndNext(
		long eventoParticipanteId, String nome,
		OrderByComparator orderByComparator)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = findByPrimaryKey(eventoParticipanteId);

		Session session = null;

		try {
			session = openSession();

			EventoParticipante[] array = new EventoParticipanteImpl[3];

			array[0] = getByNome_PrevAndNext(session, eventoParticipante, nome,
					orderByComparator, true);

			array[1] = eventoParticipante;

			array[2] = getByNome_PrevAndNext(session, eventoParticipante, nome,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventoParticipante getByNome_PrevAndNext(Session session,
		EventoParticipante eventoParticipante, String nome,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTOPARTICIPANTE_WHERE);

		if (nome == null) {
			query.append(_FINDER_COLUMN_NOME_NOME_1);
		}
		else {
			if (nome.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NOME_NOME_3);
			}
			else {
				query.append(_FINDER_COLUMN_NOME_NOME_2);
			}
		}

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
			query.append(EventoParticipanteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (nome != null) {
			qPos.add(nome);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eventoParticipante);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventoParticipante> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the evento participantes where email = &#63;.
	 *
	 * @param email the email
	 * @return the matching evento participantes
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventoParticipante> findByEmail(String email)
		throws SystemException {
		return findByEmail(email, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	public List<EventoParticipante> findByEmail(String email, int start, int end)
		throws SystemException {
		return findByEmail(email, start, end, null);
	}

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
	public List<EventoParticipante> findByEmail(String email, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL;
			finderArgs = new Object[] { email };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAIL;
			finderArgs = new Object[] { email, start, end, orderByComparator };
		}

		List<EventoParticipante> list = (List<EventoParticipante>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (EventoParticipante eventoParticipante : list) {
				if (!Validator.equals(email, eventoParticipante.getEmail())) {
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

			query.append(_SQL_SELECT_EVENTOPARTICIPANTE_WHERE);

			if (email == null) {
				query.append(_FINDER_COLUMN_EMAIL_EMAIL_1);
			}
			else {
				if (email.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(EventoParticipanteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (email != null) {
					qPos.add(email);
				}

				list = (List<EventoParticipante>)QueryUtil.list(q,
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
	 * Returns the first evento participante in the ordered set where email = &#63;.
	 *
	 * @param email the email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evento participante
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante findByEmail_First(String email,
		OrderByComparator orderByComparator)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = fetchByEmail_First(email,
				orderByComparator);

		if (eventoParticipante != null) {
			return eventoParticipante;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("email=");
		msg.append(email);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoParticipanteException(msg.toString());
	}

	/**
	 * Returns the first evento participante in the ordered set where email = &#63;.
	 *
	 * @param email the email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evento participante, or <code>null</code> if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante fetchByEmail_First(String email,
		OrderByComparator orderByComparator) throws SystemException {
		List<EventoParticipante> list = findByEmail(email, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last evento participante in the ordered set where email = &#63;.
	 *
	 * @param email the email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evento participante
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante findByEmail_Last(String email,
		OrderByComparator orderByComparator)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = fetchByEmail_Last(email,
				orderByComparator);

		if (eventoParticipante != null) {
			return eventoParticipante;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("email=");
		msg.append(email);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventoParticipanteException(msg.toString());
	}

	/**
	 * Returns the last evento participante in the ordered set where email = &#63;.
	 *
	 * @param email the email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evento participante, or <code>null</code> if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante fetchByEmail_Last(String email,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEmail(email);

		List<EventoParticipante> list = findByEmail(email, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	public EventoParticipante[] findByEmail_PrevAndNext(
		long eventoParticipanteId, String email,
		OrderByComparator orderByComparator)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = findByPrimaryKey(eventoParticipanteId);

		Session session = null;

		try {
			session = openSession();

			EventoParticipante[] array = new EventoParticipanteImpl[3];

			array[0] = getByEmail_PrevAndNext(session, eventoParticipante,
					email, orderByComparator, true);

			array[1] = eventoParticipante;

			array[2] = getByEmail_PrevAndNext(session, eventoParticipante,
					email, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventoParticipante getByEmail_PrevAndNext(Session session,
		EventoParticipante eventoParticipante, String email,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTOPARTICIPANTE_WHERE);

		if (email == null) {
			query.append(_FINDER_COLUMN_EMAIL_EMAIL_1);
		}
		else {
			if (email.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
			}
			else {
				query.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
			}
		}

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
			query.append(EventoParticipanteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (email != null) {
			qPos.add(email);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eventoParticipante);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventoParticipante> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the evento participante where eventoId = &#63; and email = &#63; or throws a {@link br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException} if it could not be found.
	 *
	 * @param eventoId the evento ID
	 * @param email the email
	 * @return the matching evento participante
	 * @throws br.gov.demoiselle.portal.evento.NoSuchEventoParticipanteException if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante findByEventoId_Email(long eventoId, String email)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = fetchByEventoId_Email(eventoId,
				email);

		if (eventoParticipante == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventoId=");
			msg.append(eventoId);

			msg.append(", email=");
			msg.append(email);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchEventoParticipanteException(msg.toString());
		}

		return eventoParticipante;
	}

	/**
	 * Returns the evento participante where eventoId = &#63; and email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param eventoId the evento ID
	 * @param email the email
	 * @return the matching evento participante, or <code>null</code> if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante fetchByEventoId_Email(long eventoId, String email)
		throws SystemException {
		return fetchByEventoId_Email(eventoId, email, true);
	}

	/**
	 * Returns the evento participante where eventoId = &#63; and email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param eventoId the evento ID
	 * @param email the email
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching evento participante, or <code>null</code> if a matching evento participante could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante fetchByEventoId_Email(long eventoId,
		String email, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { eventoId, email };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_EVENTOID_EMAIL,
					finderArgs, this);
		}

		if (result instanceof EventoParticipante) {
			EventoParticipante eventoParticipante = (EventoParticipante)result;

			if ((eventoId != eventoParticipante.getEventoId()) ||
					!Validator.equals(email, eventoParticipante.getEmail())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EVENTOPARTICIPANTE_WHERE);

			query.append(_FINDER_COLUMN_EVENTOID_EMAIL_EVENTOID_2);

			if (email == null) {
				query.append(_FINDER_COLUMN_EVENTOID_EMAIL_EMAIL_1);
			}
			else {
				if (email.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EVENTOID_EMAIL_EMAIL_3);
				}
				else {
					query.append(_FINDER_COLUMN_EVENTOID_EMAIL_EMAIL_2);
				}
			}

			query.append(EventoParticipanteModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventoId);

				if (email != null) {
					qPos.add(email);
				}

				List<EventoParticipante> list = q.list();

				result = list;

				EventoParticipante eventoParticipante = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTOID_EMAIL,
						finderArgs, list);
				}
				else {
					eventoParticipante = list.get(0);

					cacheResult(eventoParticipante);

					if ((eventoParticipante.getEventoId() != eventoId) ||
							(eventoParticipante.getEmail() == null) ||
							!eventoParticipante.getEmail().equals(email)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTOID_EMAIL,
							finderArgs, eventoParticipante);
					}
				}

				return eventoParticipante;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EVENTOID_EMAIL,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (EventoParticipante)result;
			}
		}
	}

	/**
	 * Returns all the evento participantes.
	 *
	 * @return the evento participantes
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventoParticipante> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<EventoParticipante> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

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
	public List<EventoParticipante> findAll(int start, int end,
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

		List<EventoParticipante> list = (List<EventoParticipante>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EVENTOPARTICIPANTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EVENTOPARTICIPANTE.concat(EventoParticipanteModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<EventoParticipante>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<EventoParticipante>)QueryUtil.list(q,
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
	 * Removes all the evento participantes where eventoId = &#63; from the database.
	 *
	 * @param eventoId the evento ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventoId(long eventoId) throws SystemException {
		for (EventoParticipante eventoParticipante : findByEventoId(eventoId)) {
			remove(eventoParticipante);
		}
	}

	/**
	 * Removes all the evento participantes where nome = &#63; from the database.
	 *
	 * @param nome the nome
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByNome(String nome) throws SystemException {
		for (EventoParticipante eventoParticipante : findByNome(nome)) {
			remove(eventoParticipante);
		}
	}

	/**
	 * Removes all the evento participantes where email = &#63; from the database.
	 *
	 * @param email the email
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEmail(String email) throws SystemException {
		for (EventoParticipante eventoParticipante : findByEmail(email)) {
			remove(eventoParticipante);
		}
	}

	/**
	 * Removes the evento participante where eventoId = &#63; and email = &#63; from the database.
	 *
	 * @param eventoId the evento ID
	 * @param email the email
	 * @return the evento participante that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public EventoParticipante removeByEventoId_Email(long eventoId, String email)
		throws NoSuchEventoParticipanteException, SystemException {
		EventoParticipante eventoParticipante = findByEventoId_Email(eventoId,
				email);

		return remove(eventoParticipante);
	}

	/**
	 * Removes all the evento participantes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (EventoParticipante eventoParticipante : findAll()) {
			remove(eventoParticipante);
		}
	}

	/**
	 * Returns the number of evento participantes where eventoId = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @return the number of matching evento participantes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEventoId(long eventoId) throws SystemException {
		Object[] finderArgs = new Object[] { eventoId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTOID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTOPARTICIPANTE_WHERE);

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
	 * Returns the number of evento participantes where nome = &#63;.
	 *
	 * @param nome the nome
	 * @return the number of matching evento participantes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByNome(String nome) throws SystemException {
		Object[] finderArgs = new Object[] { nome };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NOME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTOPARTICIPANTE_WHERE);

			if (nome == null) {
				query.append(_FINDER_COLUMN_NOME_NOME_1);
			}
			else {
				if (nome.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NOME_NOME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NOME_NOME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (nome != null) {
					qPos.add(nome);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NOME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of evento participantes where email = &#63;.
	 *
	 * @param email the email
	 * @return the number of matching evento participantes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEmail(String email) throws SystemException {
		Object[] finderArgs = new Object[] { email };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EMAIL,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENTOPARTICIPANTE_WHERE);

			if (email == null) {
				query.append(_FINDER_COLUMN_EMAIL_EMAIL_1);
			}
			else {
				if (email.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (email != null) {
					qPos.add(email);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EMAIL,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of evento participantes where eventoId = &#63; and email = &#63;.
	 *
	 * @param eventoId the evento ID
	 * @param email the email
	 * @return the number of matching evento participantes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEventoId_Email(long eventoId, String email)
		throws SystemException {
		Object[] finderArgs = new Object[] { eventoId, email };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTOID_EMAIL,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EVENTOPARTICIPANTE_WHERE);

			query.append(_FINDER_COLUMN_EVENTOID_EMAIL_EVENTOID_2);

			if (email == null) {
				query.append(_FINDER_COLUMN_EVENTOID_EMAIL_EMAIL_1);
			}
			else {
				if (email.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EVENTOID_EMAIL_EMAIL_3);
				}
				else {
					query.append(_FINDER_COLUMN_EVENTOID_EMAIL_EMAIL_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventoId);

				if (email != null) {
					qPos.add(email);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EVENTOID_EMAIL,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of evento participantes.
	 *
	 * @return the number of evento participantes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EVENTOPARTICIPANTE);

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
	 * Initializes the evento participante persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.br.gov.demoiselle.portal.evento.model.EventoParticipante")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<EventoParticipante>> listenersList = new ArrayList<ModelListener<EventoParticipante>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<EventoParticipante>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(EventoParticipanteImpl.class.getName());
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
	private static final String _SQL_SELECT_EVENTOPARTICIPANTE = "SELECT eventoParticipante FROM EventoParticipante eventoParticipante";
	private static final String _SQL_SELECT_EVENTOPARTICIPANTE_WHERE = "SELECT eventoParticipante FROM EventoParticipante eventoParticipante WHERE ";
	private static final String _SQL_COUNT_EVENTOPARTICIPANTE = "SELECT COUNT(eventoParticipante) FROM EventoParticipante eventoParticipante";
	private static final String _SQL_COUNT_EVENTOPARTICIPANTE_WHERE = "SELECT COUNT(eventoParticipante) FROM EventoParticipante eventoParticipante WHERE ";
	private static final String _FINDER_COLUMN_EVENTOID_EVENTOID_2 = "eventoParticipante.eventoId = ?";
	private static final String _FINDER_COLUMN_NOME_NOME_1 = "eventoParticipante.nome IS NULL";
	private static final String _FINDER_COLUMN_NOME_NOME_2 = "eventoParticipante.nome = ?";
	private static final String _FINDER_COLUMN_NOME_NOME_3 = "(eventoParticipante.nome IS NULL OR eventoParticipante.nome = ?)";
	private static final String _FINDER_COLUMN_EMAIL_EMAIL_1 = "eventoParticipante.email IS NULL";
	private static final String _FINDER_COLUMN_EMAIL_EMAIL_2 = "eventoParticipante.email = ?";
	private static final String _FINDER_COLUMN_EMAIL_EMAIL_3 = "(eventoParticipante.email IS NULL OR eventoParticipante.email = ?)";
	private static final String _FINDER_COLUMN_EVENTOID_EMAIL_EVENTOID_2 = "eventoParticipante.eventoId = ? AND ";
	private static final String _FINDER_COLUMN_EVENTOID_EMAIL_EMAIL_1 = "eventoParticipante.email IS NULL";
	private static final String _FINDER_COLUMN_EVENTOID_EMAIL_EMAIL_2 = "eventoParticipante.email = ?";
	private static final String _FINDER_COLUMN_EVENTOID_EMAIL_EMAIL_3 = "(eventoParticipante.email IS NULL OR eventoParticipante.email = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "eventoParticipante.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EventoParticipante exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EventoParticipante exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(EventoParticipantePersistenceImpl.class);
	private static EventoParticipante _nullEventoParticipante = new EventoParticipanteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<EventoParticipante> toCacheModel() {
				return _nullEventoParticipanteCacheModel;
			}
		};

	private static CacheModel<EventoParticipante> _nullEventoParticipanteCacheModel =
		new CacheModel<EventoParticipante>() {
			public EventoParticipante toEntityModel() {
				return _nullEventoParticipante;
			}
		};
}