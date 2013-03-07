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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

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

		buffer = new StringBuffer();
		buffer.append("Verificando " + ic.getMethod() + "\n");
		long size = check(ic.getTarget(), ic.getTarget().getClass());
		buffer.append("Total: " + size + " bytes");

		if (size >= conf.getMinSize()) {
			logger.info(buffer.toString());
		}

		return result;
	}

	private long check(Object target, Class<?> type) throws Exception {
		return check(target, type, 0, 0);
	}

	private long check(Object target, Class<?> type, int nestedCount, long size) throws Exception {
		long result = size;

		if (target != null && nestedCount <= conf.getMaxNestedInvocation()) {
			if (type.getSuperclass() != null && !type.getSuperclass().equals(Object.class)) {
				result += check(target, type.getSuperclass(), nestedCount, size);
			}

			buffer.append(createTab(nestedCount) + type.getCanonicalName() + "\n");

			Object fieldValue;
			for (Field field : Reflections.getNonStaticDeclaredFields(type)) {
				fieldValue = getValue(field, target);

				if (field.getType().isPrimitive()) {
					result += print(field, target, nestedCount);

				} else if (field.getType().isArray()) {
					result += print(field, target, nestedCount);

				} else if (fieldValue instanceof Collection<?>) {
					result += print(field, target, nestedCount);

				} else if (fieldValue instanceof Map<?, ?>) {
					result += print(field, target, nestedCount);

				} else if (field.getType().getPackage().getName().indexOf("java.lang") > -1
						|| field.getType().getPackage().getName().indexOf("java.util") > -1) {
					result += print(field, target, nestedCount);

				} else if (fieldValue != null && field.getType().getPackage().getName().indexOf("weld") == -1) {
					result += check(fieldValue, fieldValue.getClass(), nestedCount + 1, size);
				}
			}

			// if (size >= conf.getMinSize()) {
			buffer.append(createTab(nestedCount) + "(" + result + " bytes)\n");
			// }
		}

		return result;
	}

	private Object getValue(Field field, Object target) throws Exception {
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		Object fieldValue = field.get(target);
		field.setAccessible(accessible);

		return fieldValue;
	}

	private long print(Field field, Object target, int tabCount) throws Exception {
		long size = sizeOf(getValue(field, target));

		// if (size >= conf.getMinSize()) {
		buffer.append(createTab(tabCount + 1) + field.getName() + " (" + size + " bytes)\n");
		// }

		return size;
	}

	private String createTab(int count) {
		String result = "";

		for (int i = 0; i < (count + 1) * 4; i++) {
			result += " ";
		}

		return result;
	}

	private int sizeOf(Object obj) throws IOException {
		ByteArrayOutputStream byteObject = new ByteArrayOutputStream();

		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteObject);
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			objectOutputStream.close();
			byteObject.close();

		} catch (NotSerializableException cause) {
			buffer.append("Falha ao tentar serializar " + cause.getMessage() + "\n");
		}

		return byteObject.size();
	}
}
