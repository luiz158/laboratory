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
package br.gov.frameworkdemoiselle.objectify.internal.proxy;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Transaction;
import com.googlecode.objectify.AsyncObjectify;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

@Default
@RequestScoped
public class ObjectifyProxy implements Objectify, Serializable {

	private static final long serialVersionUID = 1L;

	private transient Objectify delegate;

	// private transient Objectify transaction;

	private Objectify getDelegate() {
		if (delegate == null) {
			delegate = ObjectifyService.begin();
		}

		return delegate;
	}

	// public void newTransaction() {
	// this.transaction = ObjectifyService.beginTransaction();
	// }
	//
	// private Objectify getTransaction() {
	// return this.transaction;
	// }

	public void setDelegate(Objectify delegate) {
		this.delegate = delegate;
	}

	@Override
	public <T> Map<Key<T>, T> get(Iterable<? extends Key<? extends T>> keys) {
		return getDelegate().get(keys);
	}

	@Override
	public <T> Map<Key<T>, T> get(Key<? extends T>... keys) {
		return getDelegate().get(keys);
	}

	@Override
	public <T> T get(Key<? extends T> key) throws NotFoundException {
		return getDelegate().get(key);
	}

	@Override
	public <T> T get(Class<? extends T> clazz, long id) throws NotFoundException {
		return getDelegate().get(clazz, id);
	}

	@Override
	public <T> T get(Class<? extends T> clazz, String name) throws NotFoundException {
		return getDelegate().get(clazz, name);
	}

	@Override
	public <S, T> Map<S, T> get(Class<? extends T> clazz, Iterable<S> idsOrNames) {
		return getDelegate().get(clazz, idsOrNames);
	}

	@Override
	public <S, T> Map<S, T> get(Class<? extends T> clazz, S... idsOrNames) {
		return getDelegate().get(clazz, idsOrNames);
	}

	@Override
	public <T> T find(Key<? extends T> key) {
		return getDelegate().find(key);
	}

	@Override
	public <T> T find(Class<? extends T> clazz, long id) {
		return getDelegate().find(clazz, id);
	}

	@Override
	public <T> T find(Class<? extends T> clazz, String name) {
		return getDelegate().find(clazz, name);
	}

	@Override
	public <T> Key<T> put(T obj) {
		return getDelegate().put(obj);
	}

	@Override
	public <T> Map<Key<T>, T> put(Iterable<? extends T> objs) {
		return getDelegate().put(objs);
	}

	@Override
	public <T> Map<Key<T>, T> put(T... objs) {
		return getDelegate().put(objs);
	}

	@Override
	public void delete(Object... keysOrEntities) {
		getDelegate().delete(keysOrEntities);
	}

	@Override
	public void delete(Iterable<?> keysOrEntities) {
		getDelegate().delete(keysOrEntities);
	}

	@Override
	public <T> void delete(Class<T> clazz, long id) {
		getDelegate().delete(clazz, id);
	}

	@Override
	public <T> void delete(Class<T> clazz, String name) {
		getDelegate().delete(clazz, name);
	}

	@Override
	public <T> Query<T> query() {
		return getDelegate().query();
	}

	@Override
	public <T> Query<T> query(Class<T> clazz) {
		return getDelegate().query(clazz);
	}

	@Override
	public Transaction getTxn() {
		return getDelegate().getTxn();
	}

	@Override
	public DatastoreService getDatastore() {
		return getDelegate().getDatastore();
	}

	@Override
	public ObjectifyFactory getFactory() {
		return getDelegate().getFactory();
	}

	@Override
	public AsyncObjectify async() {
		return getDelegate().async();
	}
}
