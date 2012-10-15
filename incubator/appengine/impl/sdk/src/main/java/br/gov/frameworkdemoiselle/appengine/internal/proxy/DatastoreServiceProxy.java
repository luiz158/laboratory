/*
 * EncomendaZ
 * 
 * Copyright (c) 2011, EncomendaZ <http://encomendaz.net>.
 * All rights reserved.
 * 
 * EncomendaZ is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 3 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, see <http://gnu.org/licenses>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 */
package br.gov.frameworkdemoiselle.appengine.internal.proxy;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreAttributes;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Index;
import com.google.appengine.api.datastore.Index.IndexState;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;

public class DatastoreServiceProxy implements DatastoreService, Serializable {

	private static final long serialVersionUID = 1L;

	private transient DatastoreService delegate;

	protected DatastoreService getDelegate() {
		if (this.delegate == null) {
			this.delegate = DatastoreServiceFactory.getDatastoreService();
		}

		return this.delegate;
	}

	@Override
	public Collection<Transaction> getActiveTransactions() {
		return getDelegate().getActiveTransactions();
	}

	@Override
	public Transaction getCurrentTransaction() {
		return getDelegate().getCurrentTransaction();
	}

	@Override
	public Transaction getCurrentTransaction(Transaction transaction) {
		return getDelegate().getCurrentTransaction(transaction);
	}

	@Override
	public PreparedQuery prepare(Query query) {
		return getDelegate().prepare(query);
	}

	@Override
	public PreparedQuery prepare(Transaction transaction, Query query) {
		return getDelegate().prepare(transaction, query);
	}

	@Override
	public KeyRangeState allocateIdRange(KeyRange keyRange) {
		return getDelegate().allocateIdRange(keyRange);
	}

	@Override
	public KeyRange allocateIds(String arg0, long arg1) {
		return getDelegate().allocateIds(arg0, arg1);
	}

	@Override
	public KeyRange allocateIds(Key key, String arg1, long arg2) {
		return getDelegate().allocateIds(key, arg1, arg2);
	}

	@Override
	public Transaction beginTransaction() {
		return getDelegate().beginTransaction();
	}

	@Override
	public Transaction beginTransaction(TransactionOptions options) {
		return getDelegate().beginTransaction(options);
	}

	@Override
	public void delete(Key... keys) {
		getDelegate().delete(keys);
	}

	@Override
	public void delete(Iterable<Key> keys) {
		getDelegate().delete(keys);
	}

	@Override
	public void delete(Transaction transaction, Key... keys) {
		getDelegate().delete(transaction, keys);
	}

	@Override
	public void delete(Transaction transaction, Iterable<Key> keys) {
		getDelegate().delete(transaction, keys);
	}

	@Override
	public Entity get(Key key) throws EntityNotFoundException {
		return getDelegate().get(key);
	}

	@Override
	public Map<Key, Entity> get(Iterable<Key> keys) {
		return getDelegate().get(keys);
	}

	@Override
	public Entity get(Transaction transaction, Key key) throws EntityNotFoundException {
		return getDelegate().get(transaction, key);
	}

	@Override
	public Map<Key, Entity> get(Transaction transaction, Iterable<Key> keys) {
		return getDelegate().get(transaction, keys);
	}

	@Override
	public DatastoreAttributes getDatastoreAttributes() {
		return getDelegate().getDatastoreAttributes();
	}

	@Override
	public Map<Index, IndexState> getIndexes() {
		return getDelegate().getIndexes();
	}

	@Override
	public Key put(Entity entity) {
		return getDelegate().put(entity);
	}

	@Override
	public List<Key> put(Iterable<Entity> entities) {
		return getDelegate().put(entities);
	}

	@Override
	public Key put(Transaction transaction, Entity entity) {
		return getDelegate().put(transaction, entity);
	}

	@Override
	public List<Key> put(Transaction transaction, Iterable<Entity> entities) {
		return getDelegate().put(transaction, entities);
	}

}
