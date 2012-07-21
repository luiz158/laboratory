package br.gov.frameworkdemoiselle.objectify.template;

import java.util.List;

import br.gov.frameworkdemoiselle.template.Crud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Reflections;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class ObjectifyCrud<T, I> implements Crud<T, I> {

	private static final long serialVersionUID = 1L;

	private Objectify objectify;

	private Class<T> entityClass;

	private static Boolean registered = false;

	public ObjectifyCrud() {
		synchronized (registered) {
			if (!registered) {
				ObjectifyService.register(getEntityClass());
				registered = true;
			}
		}
	}

	protected Class<T> getEntityClass() {
		if (this.entityClass == null) {
			this.entityClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}
		return this.entityClass;
	}

	@Override
	@Transactional
	public void delete(I id) {
		getObjectify().delete(getEntityClass(), id);
	}

	@Override
	public List<T> findAll() {
		Query<T> query = getObjectify().query(getEntityClass());
		return query.list();
	}

	@Override
	@Transactional
	public void insert(T entity) {
		getObjectify().put(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T load(I id) {
		return (T) getObjectify().get(getEntityClass(), id);
	}

	@Override
	@Transactional
	public void update(T entity) {
		getObjectify().put(entity);
	}

	protected Objectify getObjectify() {
		if (this.objectify == null) {
			this.objectify = Beans.getReference(Objectify.class);
		}

		return this.objectify;
	}
}
