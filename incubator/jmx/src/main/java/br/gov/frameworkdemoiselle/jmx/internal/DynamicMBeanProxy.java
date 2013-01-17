package br.gov.frameworkdemoiselle.jmx.internal;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeMap;

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

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.internal.producer.ResourceBundleProducer;
import br.gov.frameworkdemoiselle.jmx.annotation.Managed;
import br.gov.frameworkdemoiselle.jmx.annotation.Operation;
import br.gov.frameworkdemoiselle.jmx.annotation.OperationParameter;
import br.gov.frameworkdemoiselle.jmx.annotation.Property;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;


/**
 * 
 * <p>This class is a MBean that gets registered everytime you mark a class with {@link Managed}. It dynamicaly
 * reads the fields and operations contained in a {@link Managed} class and exposes them to the MBean server. Everytime a client
 * tries to call an operation or read/write a property inside a Managed class, this class will call the appropriate method and pass the result to the MBean client.</p>
 * 
 * @author SERPRO
 *
 */
public class DynamicMBeanProxy implements DynamicMBean {

	/*private Object delegate;*/
	private Class<?> delegateClass;
	private MBeanInfo delegateInfo;
	private Managed managedAnnotation;
	
	/*
	 * Usamos treemaps aqui numa tentativa de otimizar o acesso aos nossos métodos quando
	 * uma operação for chamada.
	 */
	private TreeMap<String, Method> getterMethods;
	private TreeMap<String, Method> setterMethods;
	private TreeMap<String, Method> operationMethods;
	
	private ResourceBundle bundle = ResourceBundleProducer.create("demoiselle-jmx-bundle", Locale.getDefault());
	
	public DynamicMBeanProxy(Class<?> delegateClass){
		if (delegateClass == null){
			throw new NullPointerException(bundle.getString("mbean-null-class-defined"));
		}
		
		if (delegateClass.getAnnotation(Managed.class)==null){
			throw new DemoiselleException( bundle.getString("mbean-no-annotation-found") );
		}
		
		this.delegateClass = delegateClass;
		managedAnnotation = this.delegateClass.getAnnotation(Managed.class);
		
		getterMethods = new TreeMap<String, Method>();
		setterMethods = new TreeMap<String, Method>();
		operationMethods = new TreeMap<String, Method>();
	}

	@Override
	public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
		//Se o bean ainda não foi lido para determinar seus atributos, o faz agora.
		if (delegateInfo==null){
			initializeMBeanInfo();
		}
		
		//Procura o método get do atributo em questão
		Method method = getterMethods.get(attribute);
		if (method!=null){
			
			//Obtém uma instância da classe gerenciada, lembrando que classes
			//anotadas com @Managed são sempre Singletons.
			Object delegate = Beans.getReference(delegateClass);
			
			try{
				return method.invoke(delegate, (Object[])null);
			}
			catch(Exception e){
				throw new MBeanException(e);
			}
		}
		else{
			throw new AttributeNotFoundException();
		}
	}

	@Override
	public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException,
			MBeanException, ReflectionException {
		
		//Se o bean ainda não foi lido para determinar seus atributos, o faz agora.
		if (delegateInfo==null){
			initializeMBeanInfo();
		}
		
		//Procura o método get do atributo em questão
		Method method = setterMethods.get(attribute.getName());
		if (method!=null){
			
			//Obtém uma instância da classe gerenciada, lembrando que classes
			//anotadas com @Managed são sempre Singletons.
			Object delegate = Beans.getReference(delegateClass);
			
			try{
				method.invoke(delegate, new Object[]{attribute.getValue()} );
			}
			catch(Exception e){
				throw new MBeanException(e);
			}
		}
		else{
			throw new AttributeNotFoundException();
		}

	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		if (attributes!=null){
			AttributeList list = new AttributeList();
			for (String attribute : attributes){
				try{
					Object value = getAttribute(attribute);
					list.add( new Attribute(attribute, value));
				}
				catch(Throwable t){}
			}
			
			return list;
		}
		
		return null;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		AttributeList settedAttributes = new AttributeList();
		if (attributes!=null){
			for (Attribute attribute : attributes.asList()){
				try {
					setAttribute(attribute);
					
					//A razão para separarmos a criação do atributo de sua adição na lista é que
					//caso a obtenção do novo valor do atributo dispare uma exceção então o atributo não será
					//adicionado na lista de atributos que foram afetados.
					Attribute attributeWithNewValue = new Attribute(attribute.getName(), getAttribute(attribute.getName()));
					settedAttributes.add( attributeWithNewValue );
				} catch (Throwable t) {}
			}
		}
		
		return settedAttributes;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException,
			ReflectionException {
		//Se o bean ainda não foi lido para determinar seus atributos, o faz agora.
		if (this.delegateInfo==null){
			initializeMBeanInfo();
		}
		
		Method method = operationMethods.get(actionName);
		if (method!=null){
			Object delegate = Beans.getReference(delegateClass);
			try {
				return method.invoke(delegate, params);
			} catch (Exception e) {
				throw new MBeanException(e);
			}
		}
		else{
			throw new MBeanException( new OperationsException( bundle.getString("mbean-invoke-error", actionName) ) );
		}
	}
	
	/**
	 * Returns the public getter method for a given field, or <code>null</code> if no getter method can be found. 
	 */
	private Method getGetterMethod(Field field){
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
	private Method getSetterMethod(Field field){
		try {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), delegateClass);
			return pd.getWriteMethod();
		} catch (IntrospectionException e) {
			return null;
		}
	}
	
	/**
	 * Initialize the Managed information for this instance of Managed, by reading the delegate class methods
	 * and identifying the ones annotated with {@link Operation} or {@link Property}. 
	 */
	private void initializeMBeanInfo(){
		if (delegateClass!=null){
			//Aqui vamos armazenar nossos atributos
			ArrayList<MBeanAttributeInfo> attributes = new ArrayList<MBeanAttributeInfo>();
			
			//Aqui vamos armazenar nossas operações
			ArrayList<MBeanOperationInfo> operations = new ArrayList<MBeanOperationInfo>();
			
			//Para cada atributo verifica se ele está anotado com Property e cria um MBeanAttributeInfo para ele.
			Field[] fields = delegateClass.getDeclaredFields();
			if (fields!=null){
				for (Field field : fields){
					if (field.isAnnotationPresent(Property.class)){
						//Obtém os métodos GET e SET para esta propriedade
						Method getterMethod = getGetterMethod(field);
						Method setterMethod = getSetterMethod(field);
						if (getterMethod==null && setterMethod==null){
							throw new DemoiselleException(bundle.getString("mbean-invalid-property-no-getter-setter",delegateClass.getSimpleName(),field.getName()));
						}
						else if ( (getterMethod!=null && getterMethod.isAnnotationPresent(Operation.class))
								|| (setterMethod!=null && setterMethod.isAnnotationPresent(Operation.class)) ){
							throw new DemoiselleException(bundle.getString("mbean-invalid-property-as-operation",delegateClass.getSimpleName()));
						}
						
						try {
							String propertyDescription = field.getAnnotation(Property.class).description();
							MBeanAttributeInfo attributeInfo = new MBeanAttributeInfo(field.getName(), propertyDescription, getterMethod, setterMethod);
							attributes.add(attributeInfo);
							
							if (getterMethod!=null){
								getterMethods.put(attributeInfo.getName(), getterMethod);
							}
							if (setterMethod!=null){
								setterMethods.put(attributeInfo.getName(), setterMethod);
							}
						} catch (javax.management.IntrospectionException e) {
							throw new DemoiselleException(bundle.getString("mbean-introspection-error",delegateClass.getSimpleName()));
						}
					}
				}
			}
			
			//Para cada metodo verifica se ele está anotado com Operation e cria um MBeanOperationInfo para ele.
			Method[] methodList = delegateClass.getMethods();
			if (methodList!=null){
				for (Method method : methodList){
					Operation opAnnotation = method.getAnnotation(Operation.class);

					if (opAnnotation!=null){
						//Lemos as informações sobre o método e criamos uma instância
						//de MBeanOperationInfo para representar este método como uma
						//operação para o Managed Server.
						
						Class<?>[] parameterTypes = method.getParameterTypes();
						MBeanParameterInfo[] parameters = parameterTypes.length>0 ? new MBeanParameterInfo[parameterTypes.length] : null;
						Annotation[][] parameterAnnotations = method.getParameterAnnotations();
						
						if (parameters!=null){
							for (int i=0; i<parameterTypes.length; i++){
								OperationParameter paramAnnotation = null;
								for (Annotation annotation : parameterAnnotations[i]){
									if (annotation.annotationType() == OperationParameter.class){
										paramAnnotation = (OperationParameter)annotation;
										break;
									}
								}
								String name = paramAnnotation!=null ? paramAnnotation.name() : ("arg" + i);
								String description = paramAnnotation!=null ? paramAnnotation.description() : null;
								
								parameters[i] = new MBeanParameterInfo(name,parameterTypes[i].getName(),description);
							}
						}

						//Com todas as informações, criamos nossa instância de MBeanOperationInfo e
						//acrescentamos na lista de todas as operações.
						MBeanOperationInfo operation = new MBeanOperationInfo(method.getName()
								,opAnnotation.description()
								,parameters
								,method.getReturnType().getName()
								,opAnnotation.type().getValue());

						operations.add(operation);
						
						operationMethods.put(operation.getName(), method);
					}
				}
			}
			
			//Por fim criamos nosso bean info.
			delegateInfo = new MBeanInfo(delegateClass.getCanonicalName()
					, managedAnnotation.description()
					, attributes.toArray(new MBeanAttributeInfo[0])
					, null
					, operations.toArray(new MBeanOperationInfo[0])
					, null);
			
		}
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		if (delegateInfo==null){
			initializeMBeanInfo();
		}

		return delegateInfo;
	}
	
}
