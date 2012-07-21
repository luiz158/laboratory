package br.gov.frameworkdemoiselle.objectify.transaction;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import br.gov.frameworkdemoiselle.objectify.internal.proxy.ObjectifyProxy;
import br.gov.frameworkdemoiselle.util.Beans;

import com.googlecode.objectify.Objectify;

@RequestScoped
public class ObjectifyTransaction implements br.gov.frameworkdemoiselle.transaction.Transaction, Serializable {

	private static final long serialVersionUID = 1L;

	private transient Objectify delegate;

	private boolean markedRollback = false;

	private Objectify getDelegate() {
		if (delegate == null) {
			delegate = Beans.getReference(Objectify.class);
		}

		return delegate;
	}

	@Override
	public boolean isActive() {
		return getDelegate().getTxn() != null && getDelegate().getTxn().isActive();
	}

	@Override
	public boolean isMarkedRollback() {
		return markedRollback;
	}

	@Override
	public void begin() {
		ObjectifyProxy proxy = Beans.getReference(ObjectifyProxy.class);
		proxy.newTransaction();
	}

	@Override
	public void commit() {
		getDelegate().getTxn().commit();
	}

	@Override
	public void rollback() {
		getDelegate().getTxn().rollback();
	}

	@Override
	public void setRollbackOnly() {
		markedRollback = true;
	}
}
