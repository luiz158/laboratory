package br.gov.frameworkdemoiselle.jmx.internal;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.spi.Context;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.OperationsException;
import javax.management.ReflectionException;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.internal.context.CustomContext;
import br.gov.frameworkdemoiselle.internal.producer.ResourceBundleProducer;
import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.management.annotation.Operation;
import br.gov.frameworkdemoiselle.management.annotation.OperationParameter;
import br.gov.frameworkdemoiselle.management.annotation.Property;
import br.gov.frameworkdemoiselle.management.internal.ManagedType;
import br.gov.frameworkdemoiselle.management.internal.ManagedType.FieldDetail;
import br.gov.frameworkdemoiselle.management.internal.ManagedType.MethodDetail;
import br.gov.frameworkdemoiselle.management.internal.ManagedType.ParameterDetail;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

/**
 * <p>
 * This class is a MBean that gets registered everytime you mark a class with {@link Managed}. It dynamicaly reads the
 * fields and operations contained in a {@link Managed} class and exposes them to the MBean server. Everytime a client
 * tries to call an operation or read/write a property inside a Managed class, this class will call the appropriate
 * method and pass the result to the MBean client.
 * </p>
 * 
 * @author SERPRO
 */
public class DynamicMBeanProxy implements DynamicMBean {

	private MBeanInfo delegateInfo;

	private Logger logger;

	private ResourceBundle bundle = ResourceBundleProducer.create("demoiselle-jmx-bundle", Locale.getDefault());

	private CustomContext mbeanContext;

	public DynamicMBeanProxy(Class<?> delegateClass, CustomContext mbeanContext) {
		if (delegateClass == null) {
			throw new NullPointerException(bundle.getString("mbean-null-class-defined"));
		}

		if (delegateClass.getAnnotation(Managed.class) == null) {
			throw new DemoiselleException(bundle.getString("mbean-no-annotation-found"));
		}

		this.delegateClass = delegateClass;
		this.mbeanContext = mbeanContext;
		managedAnnotation = this.delegateClass.getAnnotation(Managed.class);

		getterMethods = new TreeMap<String, Method>();
		setterMethods = new TreeMap<String, Method>();
		operationMethods = new TreeMap<String, Method>();

		logger = Beans.getReference(Logger.class);
	}

	@Override
	public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
		// Se o bean ainda não foi lido para determinar seus atributos, o faz agora.
		if (delegateInfo == null) {
			initializeMBeanInfo();
		}

		// Procura o método get do atributo em questão
		Method method = getterMethods.get(attribute);
		if (method != null) {
			logger.debug(bundle.getString("mbean-debug-acessing-property", method.getName(),
					delegateClass.getCanonicalName()));

			// Obtém uma instância da classe gerenciada, lembrando que classes
			// anotadas com @Managed são sempre Singletons.
			Object delegate = Beans.getReference(delegateClass);

			try {
				return method.invoke(delegate, (Object[]) null);
			} catch (Exception e) {
				throw new MBeanException(e);
			}
		} else {
			throw new AttributeNotFoundException();
		}
	}

	@Override
	public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException,
			MBeanException, ReflectionException {

		// Se o bean ainda não foi lido para determinar seus atributos, o faz agora.
		if (delegateInfo == null) {
			initializeMBeanInfo();
		}

		// Procura o método get do atributo em questão
		Method method = setterMethods.get(attribute.getName());
		if (method != null) {
			logger.debug(bundle.getString("mbean-debug-setting-property", method.getName(),
					delegateClass.getCanonicalName()));

			// Obtém uma instância da classe gerenciada, lembrando que classes
			// anotadas com @Managed são sempre Singletons.
			Object delegate = Beans.getReference(delegateClass);

			try {
				method.invoke(delegate, new Object[] { attribute.getValue() });
			} catch (Exception e) {
				throw new MBeanException(e);
			}
		} else {
			throw new AttributeNotFoundException();
		}

	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		if (attributes != null) {
			AttributeList list = new AttributeList();
			for (String attribute : attributes) {
				try {
					Object value = getAttribute(attribute);
					list.add(new Attribute(attribute, value));
				} catch (Throwable t) {
				}
			}

			return list;
		}

		return null;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		AttributeList settedAttributes = new AttributeList();
		if (attributes != null) {
			for (Attribute attribute : attributes.asList()) {
				try {
					setAttribute(attribute);

					// A razão para separarmos a criação do atributo de sua adição na lista é que
					// caso a obtenção do novo valor do atributo dispare uma exceção então o atributo não será
					// adicionado na lista de atributos que foram afetados.
					Attribute attributeWithNewValue = new Attribute(attribute.getName(),
							getAttribute(attribute.getName()));
					settedAttributes.add(attributeWithNewValue);
				} catch (Throwable t) {
				}
			}
		}

		return settedAttributes;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException,
			ReflectionException {

		// Se o bean ainda não foi lido para determinar seus atributos, o faz agora.
		if (this.delegateInfo == null) {
			initializeMBeanInfo();
		}

		Method method = operationMethods.get(actionName);
		if (method != null) {
			logger.debug(bundle.getString("mbean-debug-invoking-operation", method.getName(),
					delegateClass.getCanonicalName()));

			// CustomContext context = new ThreadLocalContext(RequestScoped.class);
			// Contexts.add(context, this.afterBeanDiscoveryEvent);

			activateContexts();

			Object delegate = Beans.getReference(delegateClass);
			try {
				return method.invoke(delegate, params);
			} catch (Exception e) {
				throw new MBeanException(e);
			} finally {
				deactivateContexts();
			}
		} else {
			throw new MBeanException(new OperationsException(bundle.getString("mbean-invoke-error", actionName)));
		}
	}

	private void activateContexts() {
		try {
			Beans.getBeanManager().getContext(RequestScoped.class);
			logger.debug(bundle.getString("mbean-debug-starting-custom-context", mbeanContext.getScope()
					.getCanonicalName(), delegateClass.getCanonicalName()));
		} catch (ContextNotActiveException e) {
			mbeanContext.setActive(true);
		}
	}

	private void deactivateContexts() {
		try {
			Context ctx = Beans.getBeanManager().getContext(RequestScoped.class);
			if (ctx == mbeanContext) {
				mbeanContext.setActive(false);
				logger.debug(bundle.getString("mbean-debug-stoping-custom-context", mbeanContext.getScope()
						.getCanonicalName(), delegateClass.getCanonicalName()));
			}
		} catch (Exception e) {
			// NOOP
		}
	}

	/**
	 * Returns the public getter method for a given field, or <code>null</code> if no getter method can be found.
	 */
	private Method getGetterMethod(Field field) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), delegateClass);
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
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), delegateClass);
			return pd.getWriteMethod();
		} catch (IntrospectionException e) {
			return null;
		}
	}

	/**
	 * Initialize the Managed information for this instance of Managed, by reading the delegate class methods and
	 * identifying the ones annotated with {@link Operation} or {@link Property}.
	 */
	private void initializeMBeanInfo(ManagedType type) {
		// Aqui vamos armazenar nossos atributos
		ArrayList<MBeanAttributeInfo> attributes = new ArrayList<MBeanAttributeInfo>();

		// Aqui vamos armazenar nossas operações
		ArrayList<MBeanOperationInfo> operations = new ArrayList<MBeanOperationInfo>();

		// Oterndo fields com seus respectivos acessores
		for (Entry<String, FieldDetail> fieldEntry : type.getFields().entrySet()) {

			try {

				MBeanAttributeInfo attributeInfo = new MBeanAttributeInfo(fieldEntry.getKey(), fieldEntry.getValue()
						.getDescription(), fieldEntry.getValue().getGetterMethod(), fieldEntry.getValue()
						.getSetterMethod());
				attributes.add(attributeInfo);

			} catch (javax.management.IntrospectionException e) {
				throw new DemoiselleException(bundle.getString("mbean-introspection-error", type.getType()
						.getSimpleName()));
			}
		}

		// Para cada metodo verifica se ele está anotado com Operation e cria um MBeanOperationInfo para ele.
		for (Entry<String, MethodDetail> methodEntry : type.getOperationMethods().entrySet()) {

			MethodDetail methodDetail = methodEntry.getValue();

			ParameterDetail[] parameterTypes = methodDetail.getParameterTypers();

			MBeanParameterInfo[] parameters = parameterTypes.length > 0 ? new MBeanParameterInfo[parameterTypes.length]
					: null;

			if (parameters != null) {

				for (int i = 0; i < parameterTypes.length; i++) {

					parameters[i] = new MBeanParameterInfo(parameterTypes[i].getParameterName(), parameterTypes[i]
							.getParameterType().getCanonicalName(), parameterTypes[i].getParameterDescription());
				}
			}

			// Com todas as informações, criamos nossa instância de MBeanOperationInfo e
			// acrescentamos na lista de todas as operações.
			MBeanOperationInfo operation = new MBeanOperationInfo(methodDetail.getMethod().getName(),
					methodDetail.getDescription(), parameters, methodDetail.getMethod().getReturnType().getName(),
					MBeanOperationInfo.ACTION_INFO);

			operations.add(operation);

		}

		// Por fim criamos nosso bean info.
		delegateInfo = new MBeanInfo(type.getType().getCanonicalName(), type.getDescription(),
				attributes.toArray(new MBeanAttributeInfo[0]), null, operations.toArray(new MBeanOperationInfo[0]),
				null);

	}

	@Override
	public MBeanInfo getMBeanInfo() {
		if (delegateInfo == null) {
			initializeMBeanInfo();
		}

		return delegateInfo;
	}

}
