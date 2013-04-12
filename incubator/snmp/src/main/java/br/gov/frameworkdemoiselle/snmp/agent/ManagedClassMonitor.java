package br.gov.frameworkdemoiselle.snmp.agent;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.spi.Context;

import org.slf4j.Logger;
import org.snmp4j.agent.mo.MOAccessImpl;
import org.snmp4j.agent.mo.MOChangeEvent;
import org.snmp4j.agent.mo.MOChangeListener;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.MOValueValidationEvent;
import org.snmp4j.agent.mo.MOValueValidationListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.internal.context.CustomContext;
import br.gov.frameworkdemoiselle.internal.producer.ResourceBundleProducer;
import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.management.annotation.Operation;
import br.gov.frameworkdemoiselle.management.annotation.Property;
import br.gov.frameworkdemoiselle.snmp.config.SNMPAgentConfig;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class ManagedClassMonitor implements MOChangeListener,MOValueValidationListener{
	
	private TreeMap<String,Field> snmpProperties;
	
	private TreeMap<String , Method> getterMethods;
	
	private TreeMap<String , Method> setterMethods;
	
	private ArrayList<MOScalar> scalarObjects;
	
	private Class<?> managedClass;
	
	private final ResourceBundle bundle = ResourceBundleProducer.create("demoiselle-snmp-bundle");
	
	private SNMPAgentConfig config;
	
	private CustomContext snmpContext;
	
	private Logger logger;
	
	public ManagedClassMonitor (Class<?> managedClass , CustomContext snmpContext){
		config = Beans.getReference(SNMPAgentConfig.class);
		
		snmpProperties = new TreeMap<String, Field>();
		getterMethods = new TreeMap<String, Method>();
		setterMethods = new TreeMap<String, Method>();
		scalarObjects = new ArrayList<MOScalar>();
		
		this.managedClass = managedClass;
		this.snmpContext = snmpContext;
		this.logger = Beans.getReference(Logger.class);
		
		if (!this.managedClass.isAnnotationPresent(Managed.class)){
			throw new DemoiselleException(bundle.getString("agent-snmp-not-managed-class"));
		}
		
		initializeMonitor();
	}
	
	
	public MOScalar[] getMOScalarList(){
		if (scalarObjects.size()==0){
			return null;
		}
		
		MOScalar[] scalarList = new MOScalar[scalarObjects.size()];
		scalarList = scalarObjects.toArray(scalarList);
		
		return scalarList;
	}
	
	public Class<?> getManagedClass(){
		return this.managedClass;
	}
	
	private void initializeMonitor(){
		Field[] fields = managedClass.getDeclaredFields();
		if (fields!=null){
			for (Field field : fields){
				if (field.isAnnotationPresent(Property.class)){
					//Obtém os métodos GET e SET para esta propriedade
					Method getterMethod = getGetterMethod(field);
					Method setterMethod = getSetterMethod(field);
					if (getterMethod==null && setterMethod==null){
						throw new DemoiselleException(bundle.getString("snmp-invalid-property-no-getter-setter",managedClass.getSimpleName(),field.getName()));
					}
					else if ( (getterMethod!=null && getterMethod.isAnnotationPresent(Operation.class))
							|| (setterMethod!=null && setterMethod.isAnnotationPresent(Operation.class)) ){
						throw new DemoiselleException(bundle.getString("snmp-invalid-property-as-operation",managedClass.getSimpleName()));
					}

					if (getterMethod!=null){
						getterMethods.put(field.toGenericString(), getterMethod);
					}

					if (setterMethod!=null){
						setterMethods.put(field.toGenericString(), setterMethod);
					}
					
					MOScalar scalarObject = createScalarForProperty(field);
					snmpProperties.put(scalarObject.getOid().toString() , field);
					scalarObjects.add(scalarObject);
				}
			}
		}
	}
	
	/**
	 * Returns the public getter method for a given field, or <code>null</code> if no getter method can be found. 
	 */
	private Method getGetterMethod(Field field){
		try {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), managedClass);
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
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), managedClass);
			return pd.getWriteMethod();
		} catch (IntrospectionException e) {
			return null;
		}
	}
	
	private MOScalar createScalarForProperty(Field property){
		OID oid = new OID(config.getMibRoot() + ".1.1.0");
		Variable v = createVariableForField(property);
		MOScalar scalar = new MOScalar(oid, MOAccessImpl.ACCESS_READ_WRITE, v);
		
		scalar.addMOChangeListener(this);
		scalar.addMOValueValidationListener(this);
		
		return scalar;
	}
	
	private Variable createVariableForField(Field property){
		Class<?> propertyType = property.getType();
		Variable v = null;
		if (Number.class.isAssignableFrom(propertyType)){
			v = new Integer32();
		}
		else{
			v = new OctetString();
		}
		
		return v;
	}
	
	private void activateContexts(){
		try{
			Beans.getBeanManager().getContext(RequestScoped.class);
			logger.debug(bundle.getString("snmp-debug-starting-custom-context",snmpContext.getScope().getCanonicalName(),managedClass.getCanonicalName()));
		}
		catch(ContextNotActiveException e){
			snmpContext.setActive(true);
		}
	}
	
	private void deactivateContexts(){
		try{
			Context ctx = Beans.getBeanManager().getContext(RequestScoped.class);
			if (ctx == snmpContext){
				snmpContext.setActive(false);
				logger.debug(bundle.getString("snmp-debug-stoping-custom-context",snmpContext.getScope().getCanonicalName(),managedClass.getCanonicalName()));
			}
		}
		catch(Exception e){
			//NOOP
		}
	}

	@Override
	public void validate(MOValueValidationEvent validationEvent) {
		validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_SUCCESS);
	}

	@Override
	public void beforePrepareMOChange(MOChangeEvent changeEvent) {
		try{
			activateContexts();
			
			Object managedObject = Beans.getReference(managedClass);
			//changeEvent.
			
		}
		finally{
			deactivateContexts();
		}
	}

	@Override
	public void afterPrepareMOChange(MOChangeEvent changeEvent) {
		changeEvent.setDenyReason(SnmpConstants.SNMP_ERROR_SUCCESS);
	}

	@Override
	public void beforeMOChange(MOChangeEvent changeEvent) {
		changeEvent.setDenyReason(SnmpConstants.SNMP_ERROR_SUCCESS);
	}

	@Override
	public void afterMOChange(MOChangeEvent changeEvent) {
		changeEvent.setDenyReason(SnmpConstants.SNMP_ERROR_SUCCESS);
	}

}
