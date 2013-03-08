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
		// buffer = new StringBuffer();

		// println("Verificando " + ic.getMethod());

		// Node root = new Node(ic.getMethod().toGenericString());
		Node node = check(ic.getTarget());

		print(node);

		// println("Total: " + size + " bytes");

		// if (size >= conf.getMinSize()) {
		// logger.info(buffer.toString());
		// }

		return result;
	}

	private Node check(Object target) throws Exception {
		return check(target.getClass().getName(), target, target.getClass(), null);
	}

	private Node check(String name, final Object target, final Class<?> type, Node parent) throws Exception {
		// long result = size;
		visitedInstances.add(target);
		Node node = new Node(name, type, parent);

		if (isPrimitiveOrWrapper(type)) {
			node.setSize(getSize(target));
			//
			// long objectSize = ;
			// result += objectSize;
			//
			// new Node(name, objectSize);

			// println(getTabulation(nestedCount) + name + " (" + objectSize + ")");

		} else {
			// println(getTabulation(nestedCount) + name);

			if (target instanceof Collection<?>) {
				checkCollection(name, (Collection<?>) target, node);
			} else if (target instanceof Map<?, ?>) {
				checkMap(name, (Map<?, ?>) target, node);
			} else {
				checkBean(target, type, node);
			}

			// println(getTabulation(nestedCount) + "(" + result + ")");
		}

		// return result;
		return node;
	}

	private boolean isPrimitiveOrWrapper(Class<?> type) {
		boolean result = false;

		result |= type.isPrimitive();
		result |= ClassUtils.wrapperToPrimitive(type) != null;
		result |= type == String.class;
		result |= type == Class.class;

		return result;
	}

	private void checkCollection(String name, Collection<?> target, Node parent) throws Exception {
		try {
			int i = 0;
			for (Object item : target) {
				if (item != null) {
					check("[" + i + "]", item, item.getClass(), parent);
				}
				i++;
			}

		} catch (Exception cause) {
			println("Falha ao tentar acessar a Collection com o seguinte erro: " + cause.getMessage());
		}
	}

	private void checkMap(String name, Map<?, ?> target, Node parent) throws Exception {
		try {
			for (Object key : target.keySet()) {
				if (target.get(key) != null) {
					check("[" + key + "]", target.get(key), target.get(key).getClass(), parent);
				}
			}

		} catch (Exception cause) {
			println("Falha ao tentar acessar o Map com o seguinte erro: " + cause.getMessage());
		}
	}

	private void checkBean(Object target, Class<?> type, Node parent) throws Exception {
		Object value;
		for (Field field : getNonStaticDeclaredFields(type)) {
			value = getValue(field, target);

			if (value != null && !visitedInstances.contains(value)) {
				check(field.getName(), value, field.getType(), parent);
			}
		}
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

		// try {
		// fieldValue = PropertyUtils.getProperty(target, field.getName());
		//
		// } catch (NoSuchMethodException cause) {
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		fieldValue = field.get(target);
		field.setAccessible(accessible);
		// }

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
			println("Falha ao tentar serializar " + cause.getMessage());
		}

		return byteObject.size();
	}

	private void println(String message) {
		if (buffer == null) {
			buffer = new StringBuffer();
		}

		buffer.append(message + "\n");
	}

	private void print(Node node) {
		println(getTabulation(node.getLevel()) + node.getName());

		for (Node child : node.getChildren()) {
			print(child);
		}

		if (node.getParent() == null) {
			logger.info(buffer.toString());
		}
	}

	// private interface Node {

	// String getName();
	// }

	private class Node {

		private final String name;

		private final Class<?> type;

		private final Node parent;

		private final List<Node> children = new ArrayList<AnalyzeInterceptor.Node>();

		private Long size;

		Node(String name, Class<?> type, Node parent) {
			this.name = name;
			this.type = type;
			this.parent = parent;

			if (this.parent != null) {
				this.parent.addChild(this);
			}
		}

		Node getParent() {
			return parent;
		}

		List<Node> getChildren() {
			return children;
		}

		String getName() {
			return name;
		}

		int getLevel() {
			int level = 0;

			Node node = this;
			while (node.parent != null) {
				level++;
				node = node.parent;
			}

			return level;
		}

		Class<?> getType() {
			return type;
		}

		void setSize(long size) {
			this.size = size;
		}

		void addChild(Node node) {
			children.add(node);
		}

		boolean isEnd() {
			return this.children.isEmpty() && this.size != null;
		}
	}
	//
	// private class NormalNode implements Node {
	//
	// private String name;
	//
	// private List<Node> children;
	//
	// NormalNode(String name) {
	// this.name = name;
	// }
	// }
	//
	//
	// private class FinalNode extends NormalNode {
	//
	// FinalNode(String name) {
	// super(name);
	// }
	//
	// private long size;
	//
	// }
}
