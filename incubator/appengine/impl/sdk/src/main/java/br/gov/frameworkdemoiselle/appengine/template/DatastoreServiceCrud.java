/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.appengine.template;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.template.Crud;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Reflections;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * AppEngine DatastoreService specific implementation for Crud interface.
 * 
 * @param <T>
 *            bean object type
 * @param <I>
 *            bean id type
 * @author SERPRO
 * @see Crud
 */
public class DatastoreServiceCrud<T, I> implements Crud<T, I> {

	private static final long serialVersionUID = 1L;

	private Class<T> beanClass;

	private transient Field idField;

	private DatastoreService datastoreService;

	protected DatastoreService getDatastoreService() {
		if (this.datastoreService == null) {
			this.datastoreService = Beans.getReference(DatastoreService.class);
		}

		return this.datastoreService;
	}

	protected Class<T> getBeanClass() {
		if (this.beanClass == null) {
			this.beanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}

		return this.beanClass;
	}

	protected T newBeanClassInstance() {
		T result = null;

		try {
			result = getBeanClass().newInstance();
		} catch (Exception cause) {
			throw new DemoiselleException(cause);
		}

		return result;
	}

	@Override
	public void delete(I id) {
		Key key = createKey(id);
		getDatastoreService().delete(key);
	}

	@Override
	public List<T> findAll() {
		List<T> result = new ArrayList<T>();

		Query query = new Query(getKind());
		PreparedQuery preparedQuery = getDatastoreService().prepare(query);

		for (Entity entity : preparedQuery.asIterable()) {
			result.add(parse(entity));
		}

		return result;
	}

	@Override
	public void insert(T bookmark) {
		Entity entity = new Entity(getKind());

		for (Field field : Reflections.getNonStaticDeclaredFields(bookmark.getClass())) {
			if (!field.equals(getIdField())) {
				setProperty(entity, field.getName(), Reflections.getFieldValue(field, bookmark));
			}
		}

		Key key = getDatastoreService().put(entity);
		Reflections.setFieldValue(getIdField(), bookmark, isStringId() ? key.getName() : key.getId());
	}

	@Override
	public T load(I id) {
		Entity entity = loadEntity(id);
		return parse(entity);
	}

	protected Entity loadEntity(I id) {
		Entity result;

		try {
			Key key = createKey(id);
			result = getDatastoreService().get(key);

		} catch (EntityNotFoundException e) {
			result = null;
		}

		return result;
	}

	@Override
	public void update(T bookmark) {
		@SuppressWarnings("unchecked")
		Entity entity = loadEntity((I) Reflections.getFieldValue(getIdField(), bookmark));

		boolean updated = false;
		for (Field field : Reflections.getNonStaticDeclaredFields(bookmark.getClass())) {
			if (!field.equals(getIdField())) {
				updated |= setProperty(entity, field.getName(), Reflections.getFieldValue(field, bookmark));
			}
		}

		if (updated) {
			getDatastoreService().put(entity);
		}
	}

	protected String getKind() {
		return getBeanClass().getSimpleName();
	}

	protected Key createKey(I id) {
		return KeyFactory.createKey(getKind(), (Long) id);
	}

	private Field getIdField() {
		if (this.idField == null) {
			for (Field field : Reflections.getNonStaticDeclaredFields(getBeanClass())) {
				if (field.getName().equals("id")) {
					this.idField = field;
					break;
				}
			}
			
			validateIdField(this.idField);
		}

		return this.idField;
	}
	
	private static void validateIdField(Field idField) {
		
	}

	protected T parse(Entity entity) {
		T result = null;

		if (entity != null) {
			result = newBeanClassInstance();
			Key key;

			for (Field field : Reflections.getNonStaticDeclaredFields(result.getClass())) {
				key = entity.getKey();

				if (field.equals(getIdField())) {
					Reflections.setFieldValue(field, result, isStringId() ? key.getName() : key.getId());

				} else {
					Reflections.setFieldValue(field, result, entity.getProperty(field.getName()));
				}
			}
		}

		return result;
	}

	private boolean isStringId() {
		return getIdField().getType() == String.class;
	}

	protected boolean setProperty(Entity entity, String property, Object value) {
		boolean updated = false;

		Object currentValue = entity.getProperty(property);

		if (value == null && currentValue != null) {
			entity.removeProperty(property);
			updated = true;

		} else if (value != null && !value.equals(currentValue)) {
			entity.setProperty(property, value);
			updated = true;
		}

		return updated;
	}
}
