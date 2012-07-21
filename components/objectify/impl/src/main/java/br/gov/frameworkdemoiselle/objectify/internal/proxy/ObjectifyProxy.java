package br.gov.frameworkdemoiselle.objectify.internal.proxy;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.RequestScoped;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Transaction;
import com.googlecode.objectify.AsyncObjectify;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

@RequestScoped
public class ObjectifyProxy implements Objectify, Serializable {

	private static final long serialVersionUID = 1L;

	private transient Objectify delegate;

	private transient Objectify transaction;

	private Objectify getDelegate() {
		if (delegate == null) {
			delegate = ObjectifyService.begin();
		}

		return delegate;
	}

	public void newTransaction() {
		this.transaction = ObjectifyService.beginTransaction();
	}

	private Objectify getTransaction() {
		return this.transaction;
	}

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
		return getTransaction().put(obj);
	}

	@Override
	public <T> Map<Key<T>, T> put(Iterable<? extends T> objs) {
		return getTransaction().put(objs);
	}

	@Override
	public <T> Map<Key<T>, T> put(T... objs) {
		return getTransaction().put(objs);
	}

	@Override
	public void delete(Object... keysOrEntities) {
		getTransaction().delete(keysOrEntities);
	}

	@Override
	public void delete(Iterable<?> keysOrEntities) {
		getTransaction().delete(keysOrEntities);
	}

	@Override
	public <T> void delete(Class<T> clazz, long id) {
		getTransaction().delete(clazz, id);
	}

	@Override
	public <T> void delete(Class<T> clazz, String name) {
		getTransaction().delete(clazz, name);
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
		Transaction result = null;

		if (getTransaction() != null) {
			result = getTransaction().getTxn();
		}

		return result;
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
