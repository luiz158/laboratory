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
package br.gov.frameworkdemoiselle.objectify.template;

import java.util.List;

import br.gov.frameworkdemoiselle.template.Crud;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Reflections;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class ObjectifyCrud<T> implements Crud<T, Long> {

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
	public void delete(Long id) {
		getObjectify().delete(getEntityClass(), id);
	}

	@Override
	public List<T> findAll() {
		Query<T> query = getObjectify().query(getEntityClass());
		return query.list();
	}

	@Override
	public void insert(T entity) {
		getObjectify().put(entity);
	}

	@Override
	public T load(Long id) {
		return (T) getObjectify().get(getEntityClass(), id);
	}

	@Override
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
