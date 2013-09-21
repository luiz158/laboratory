package br.gov.frameworkdemoiselle.management.internal;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.TreeMap;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.internal.producer.ResourceBundleProducer;
import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.management.annotation.Operation;
import br.gov.frameworkdemoiselle.management.annotation.OperationParameter;
import br.gov.frameworkdemoiselle.management.annotation.Property;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

/**
 * Represents a detected managed type, a Java class annotated with {@link Managed}.
 * 
 * @author serpro
 */
public class ManagedType {

	private Class<?> type;

	private TreeMap<String, FieldDetail> fields;

	private TreeMap<String, MethodDetail> operationMethods;

	private ResourceBundle bundle;
	
	private String description;

	public ManagedType(Class<?> type) {
		bundle = ResourceBundleProducer.create("demoiselle-management-bundle");

		if (type == null) {
			throw new DemoiselleException(bundle.getString("management-null-class-defined"));
		}
		if (!type.isAnnotationPresent(Managed.class)) {
			throw new DemoiselleException(bundle.getString("management-no-annotation-found", type.getCanonicalName()));
		}

		this.type = type;
		fields = new TreeMap<String, FieldDetail>();
		operationMethods = new TreeMap<String, MethodDetail>();
		this.description = type.getAnnotation(Managed.class).description();

		initialize();
	}

	public Class<?> getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public TreeMap<String, FieldDetail> getFields() {
		return fields;
	}

	public TreeMap<String, MethodDetail> getOperationMethods() {
		return operationMethods;
	}

	private void initialize() {
		// Para cada atributo verifica se ele está anotado com Property e extrai as informações dele (método get, set e
		// descrição do atributo).
		Field[] fields = type.getDeclaredFields();
		if (fields != null) {
			for (Field field : fields) {
				if (field.isAnnotationPresent(Property.class)) {
					// Obtém os métodos GET e SET para esta propriedade
					Method getterMethod = getGetterMethod(field);
					Method setterMethod = getSetterMethod(field);
					if (getterMethod == null && setterMethod == null) {
						throw new DemoiselleException(bundle.getString("management-invalid-property-no-getter-setter",
								type.getSimpleName(), field.getName()));
					} else if ((getterMethod != null && getterMethod.isAnnotationPresent(Operation.class))
							|| (setterMethod != null && setterMethod.isAnnotationPresent(Operation.class))) {
						throw new DemoiselleException(bundle.getString("management-invalid-property-as-operation",
								type.getSimpleName()));
					}

					String propertyDescription = field.getAnnotation(Property.class).description();

					this.fields.put(field.getName(), new FieldDetail(field, propertyDescription, getterMethod,
							setterMethod));
				}
			}
		}

		// Para cada metodo verifica se ele está anotado com Operation e cria um MBeanOperationInfo para ele.
		Method[] methodList = type.getMethods();
		if (methodList != null) {
			for (Method method : methodList) {
				Operation opAnnotation = method.getAnnotation(Operation.class);

				if (opAnnotation != null) {
					// Lemos as informações sobre o método e criamos uma instância
					// de MethodDetail para representar este método como uma
					// operação.

					Class<?>[] parameterTypes = method.getParameterTypes();
					Annotation[][] parameterAnnotations = method.getParameterAnnotations();
					ParameterDetail[] parameterDetails = new ParameterDetail[parameterTypes.length];

					for (int i = 0; i < parameterTypes.length; i++) {
						OperationParameter paramAnnotation = null;
						for (Annotation annotation : parameterAnnotations[i]) {
							if (annotation.annotationType() == OperationParameter.class) {
								paramAnnotation = (OperationParameter) annotation;
								break;
							}
						}

						String name = paramAnnotation != null ? paramAnnotation.name() : ("arg" + i);
						String description = paramAnnotation != null ? paramAnnotation.description() : null;

						parameterDetails[i] = new ParameterDetail(parameterTypes[i], name, description);
					}

					// Com todas as informações, criamos nossa instância de MethodDetail e
					// acrescentamos na lista de todas as operações.
					MethodDetail detail = new MethodDetail(method, opAnnotation.description(), parameterDetails);
					operationMethods.put(method.getName(), detail);
				}
			}
		}
	}

	/**
	 * Returns the public getter method for a given field, or <code>null</code> if no getter method can be found.
	 */
	private Method getGetterMethod(Field field) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
			return pd.getReadMethod();
		} catch (IntrospectionException e) {
			return null;
		}
	}

	/**
	 * Returns the public setter method for a given field, or <code>null</code> if no setter method can be found.
	 */
	private Method getSetterMethod(Field field) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
			return pd.getWriteMethod();
		} catch (IntrospectionException e) {
			return null;
		}
	}

	public final class FieldDetail {

		private final Field field;

		private final String description;

		private Method getterMethod;

		private Method setterMethod;

		public FieldDetail(Field field, String description, Method getterMethod, Method setterMethod) {
			super();
			this.field = field;
			this.description = description;
			this.getterMethod = getterMethod;
			this.setterMethod = setterMethod;
		}

		public Field getField() {
			return field;
		}

		public String getDescription() {
			return description;
		}

		public Method getGetterMethod() {
			return getterMethod;
		}

		public Method getSetterMethod() {
			return setterMethod;
		}

	}

	public final class MethodDetail {

		private final Method method;

		private final ParameterDetail[] parameterTypers;
		
		private String description;
		
		public MethodDetail(Method method, String description, ParameterDetail[] parameterTypers) {
			super();
			this.method = method;
			this.description = description;
			this.parameterTypers = parameterTypers;
		}

		public Method getMethod() {
			return method;
		}

		public ParameterDetail[] getParameterTypers() {
			return parameterTypers;
		}
		
		public String getDescription() {
			return description;
		}
		
	}

	public final class ParameterDetail {

		private final Class<?> parameterType;

		private final String parameterName;

		private final String parameterDescription;

		public ParameterDetail(Class<?> parameterType, String parameterName, String parameterDescription) {
			super();
			this.parameterType = parameterType;
			this.parameterName = parameterName;
			this.parameterDescription = parameterDescription;
		}

		public Class<?> getParameterType() {
			return parameterType;
		}

		public String getParameterName() {
			return parameterName;
		}

		public String getParameterDescription() {
			return parameterDescription;
		}
	}

	/**
	 * Indicates another {@link ManagedType} represents the same {@link Class} as this one. This method also supports a
	 * {@link Class} as a parameter, in this case it will return <code>true</code> if the passed class is exactly the
	 * same Java class represented by this {@link ManagedType}.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}

		return ((ManagedType) other).getType().getCanonicalName().equals(this.getType().getCanonicalName());
	}
}
