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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.beanutils.PropertyUtils;
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

	private Set<Object> visitedInstances = new HashSet<Object>();

	@AroundInvoke
	public Object manage(final InvocationContext ic) throws Exception {
		Object result = ic.proceed();
		buffer = new StringBuffer();

		println("Verificando " + ic.getMethod());
		long size = check(ic.getTarget());
		println("Total: " + size + " bytes");

		if (size >= conf.getMinSize()) {
			logger.info(buffer.toString());
		}

		return result;
	}

	private long check(Object target) throws Exception {
		return check(target.getClass().getCanonicalName(), target, target.getClass(), 0, 0);
	}

	private long check(String name, Object target, Class<?> type, int nestedCount, long size) throws Exception {
		long result = size;
		visitedInstances.add(target);

		if (isPrimitiveOrWrapper(type)) {
			long objectSize = getSize(target);
			result += objectSize;

			println(getTabulation(nestedCount) + name + " (" + objectSize + ")");

		} else {
			println(getTabulation(nestedCount) + name);

			if (target instanceof Collection<?>) {
				result += checkCollection((Collection<?>) target, nestedCount, size);
			} else if (target instanceof Map<?, ?>) {
				result += checkMap((Map<?, ?>) target, nestedCount, size);
			} else {
				result += checkBean(target, type, nestedCount, size);
			}

			println(getTabulation(nestedCount) + "(" + result + ")");
		}

		return result;
	}

	private boolean isPrimitiveOrWrapper(Class<?> type) {
		boolean result = false;

		result |= type.isPrimitive();
		result |= ClassUtils.wrapperToPrimitive(type) != null;
		result |= type == String.class;
		result |= type == Class.class;

		return result;
	}

	private long checkCollection(Collection<?> target, int nestedCount, long size) throws Exception {
		long result = size;

		int i = 0;
		for (Object item : target) {
			if (item != null) {
				result += check("[" + i + "]", item, item.getClass(), nestedCount + 1, size);
			}
			i++;
		}

		return result;
	}

	private long checkMap(Map<?, ?> target, int nestedCount, long size) throws Exception {
		long result = size;

		for (Object key : target.keySet()) {
			if (target.get(key) != null) {
				result += check("[" + key.toString() + "]", target.get(key), target.get(key).getClass(),
						nestedCount + 1, size);
			}
		}

		return result;
	}

	private long checkBean(Object target, Class<?> type, int nestedCount, long size) throws Exception {
		long result = size;

		Object value;
		for (Field field : getNonStaticDeclaredFields(type)) {
			value = getValue(field, target);

			if (value != null && !visitedInstances.contains(value)) {
				result += check(field.getName(), value, field.getType(), nestedCount + 1, size);
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
		Object fieldValue;

		try {
			fieldValue = PropertyUtils.getProperty(target, field.getName());

		} catch (NoSuchMethodException cause) {
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			fieldValue = field.get(target);
			field.setAccessible(accessible);
		}

		return fieldValue;
	}

	private String getTabulation(int count) {
		String result = "";

		for (int i = 0; i < (count + 1) * 4; i++) {
			result += " ";
		}

		return result;
	}

	private int getSize(Object target) throws IOException {
		ByteArrayOutputStream byteObject = new ByteArrayOutputStream();

		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteObject);
			objectOutputStream.writeObject(target);
			objectOutputStream.flush();
			objectOutputStream.close();
			byteObject.close();

		} catch (NotSerializableException cause) {
			println("Falha ao tentar serializar " + cause.getMessage() + "\n");
		}

		return byteObject.size();
	}

	private void println(String message) {
		buffer.append(message + "\n");
	}
}
