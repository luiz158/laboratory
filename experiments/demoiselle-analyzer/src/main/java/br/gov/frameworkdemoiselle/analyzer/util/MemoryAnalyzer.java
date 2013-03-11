package br.gov.frameworkdemoiselle.analyzer.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Reflections;

public class MemoryAnalyzer {

	private MemoryAnalyzer() {
	}

	public static Node analyze(Object instance) throws Exception {
		return check(instance);
	}

	private static Node check(Object target) throws Exception {
		return check(target.getClass().getName(), target, target.getClass(), null, new HashSet<Object>());
	}

	private static Node check(String name, final Object target, final Class<?> type, Node parent,
			Set<Object> visitedInstances) throws Exception {
		visitedInstances.add(target);
		Node node = new Node(name, type, parent);

		if (isPrimitiveOrWrapper(type)) {
			node.setSize(getSize(target));

		} else {
			if (target instanceof Collection<?>) {
				checkCollection(name, (Collection<?>) target, node, visitedInstances);
			} else if (target instanceof Map<?, ?>) {
				checkMap(name, (Map<?, ?>) target, node, visitedInstances);
			} else {
				checkBean(target, type, node, visitedInstances);
			}
		}

		return node;
	}

	private static boolean isPrimitiveOrWrapper(Class<?> type) {
		boolean result = false;

		result |= type.isPrimitive();
		result |= ClassUtils.wrapperToPrimitive(type) != null;
		result |= type == String.class;
		result |= type == Class.class;

		return result;
	}

	private static void checkCollection(String name, Collection<?> target, Node parent, Set<Object> visitedInstances)
			throws Exception {
		try {
			int i = 0;
			for (Object item : target) {
				if (item != null) {
					check("[" + i + "]", item, item.getClass(), parent, visitedInstances);
				}
				i++;
			}

		} catch (Exception cause) {
			// println("Falha ao tentar acessar a Collection com o seguinte erro: " + cause.getMessage());
		}
	}

	private static void checkMap(String name, Map<?, ?> target, Node parent, Set<Object> visitedInstances)
			throws Exception {
		try {
			for (Object key : target.keySet()) {
				if (target.get(key) != null) {
					check("[" + key + "]", target.get(key), target.get(key).getClass(), parent, visitedInstances);
				}
			}

		} catch (Exception cause) {
			// println("Falha ao tentar acessar o Map com o seguinte erro: " + cause.getMessage());
		}
	}

	private static void checkBean(Object target, Class<?> type, Node parent, Set<Object> visitedInstances)
			throws Exception {
		Object value;
		for (Field field : getNonStaticDeclaredFields(type)) {
			value = getValue(field, target);

			if (value != null && !visitedInstances.contains(value)) {
				check(field.getName(), value, field.getType(), parent, visitedInstances);
			}
		}
	}

	private static List<Field> getNonStaticDeclaredFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();

		if (type != null) {
			fields.addAll(Arrays.asList(Reflections.getNonStaticDeclaredFields(type)));
			fields.addAll(getNonStaticDeclaredFields(type.getSuperclass()));
		}

		return fields;
	}

	private static Object getValue(Field field, Object target) throws Exception {
		Object fieldValue;

		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		fieldValue = field.get(target);
		field.setAccessible(accessible);

		return fieldValue;
	}

	private static long getSize(Object target) throws IOException {
		long result = 0;

		try {
			result = getSizeByInstrumentation(target);

		} catch (IllegalStateException cause) {
			System.err.println("Estimando o tamanho do objeto com serialização.");

			result = getSizeBySerialization(target);
		}

		return result;
	}

	private static long getSizeByInstrumentation(Object target) {
		Instrumentation instrumentation = Beans.getReference(Instrumentation.class);
		return instrumentation.getObjectSize(target);
	}

	private static long getSizeBySerialization(Object target) {
		ByteArrayOutputStream byteObject = new ByteArrayOutputStream();

		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteObject);
			objectOutputStream.writeObject(target);
			objectOutputStream.flush();
			objectOutputStream.close();
			byteObject.close();

		} catch (Exception cause) {
			// println("Falha ao tentar serializar " + cause.getMessage());
		}

		return byteObject.size();
	}
}
