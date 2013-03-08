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
package br.gov.frameworkdemoiselle.analyzer.internal.interceptor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang.ClassUtils;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.analyzer.Analyze;
import br.gov.frameworkdemoiselle.analyzer.internal.configuration.AnalyzeConf;
import br.gov.frameworkdemoiselle.util.Reflections;

@Interceptor
@Analyze
public class AnalyzeInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private AnalyzeConf conf;

	private StringBuffer buffer;

	@AroundInvoke
	public Object manage(final InvocationContext ic) throws Exception {
		Object result = ic.proceed();

		// buffer = new StringBuffer();
		// buffer.append("Verificando " + ic.getMethod() + "\n");
		long size = check(ic.getTarget());
		// buffer.append("Total: " + size + " bytes");

		// if (size >= conf.getMinSize()) {
		// logger.info(buffer.toString());
		// }

		return result;
	}

	private long check(Object target) throws Exception {
		return check(target.getClass().getCanonicalName(), target, target.getClass(), 0, 0);
	}

	private long check(String name, Object target, Class<?> type, int nestedCount, long size) throws Exception {
		long result = size;

		logger.info(getTabulation(nestedCount) + name);

		Object value;
		boolean found;

		for (Field field : getNonStaticDeclaredFields(type)) {
			value = getValue(field, target);

			found = false;
			if (value != null) {
				if (field.getType().isPrimitive()) {
					found = true;

				} else if (ClassUtils.wrapperToPrimitive(field.getType()) != null) {
					found = true;

				} else if (value instanceof String) {
					found = true;

				} else if (value instanceof Class) {
					found = true;
				}

				if (found) {
					logger.info(getTabulation(nestedCount + 1) + field.getName());

				} else {
					check(field.getName(), value, field.getType(), nestedCount + 1, size);
				}
			}
		}

		return result;
	}

	private List<Field> getNonStaticDeclaredFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();

		if (type != null) {
			fields.addAll(Arrays.asList(Reflections.getNonStaticDeclaredFields(type)));
			fields.addAll(getNonStaticDeclaredFields(type.getSuperclass()));
		}

		return fields;
	}

	private Object getValue(Field field, Object target) throws Exception {
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		Object fieldValue = field.get(target);
		field.setAccessible(accessible);

		return fieldValue;
	}

	private String getTabulation(int count) {
		String result = "";

		for (int i = 0; i < (count + 1) * 4; i++) {
			result += " ";
		}

		return result;
	}
}
