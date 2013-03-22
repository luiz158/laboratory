package br.gov.frameworkdemoiselle.jmx.internal;

import java.util.Collection;
import java.util.HashMap;

import javax.inject.Singleton;
import javax.management.ObjectInstance;

@Singleton
public class MBeanManager {
	
	private HashMap<String,ObjectInstance> registeredMBeans = new HashMap<String,ObjectInstance>();
	
	public void storeRegisteredMBean(ObjectInstance instance){
		registeredMBeans.put(instance.getObjectName().getCanonicalName(),instance);
	}
	
	public Collection<ObjectInstance> listRegisteredMBeans(){
		return registeredMBeans.values();
	}
	
	public ObjectInstance findMBeanInstance(String name){
		ObjectInstance instance = registeredMBeans.get(name);
		return instance;
	}
	
	public void cleanRegisteredMBeans(){
		registeredMBeans.clear();
	}

}
