package br.gov.frameworkdemoiselle.jmx.internal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.management.ObjectInstance;

@Singleton
public class MBeanManager {
	
	private ArrayList<ObjectInstance> registeredMBeans = new ArrayList<ObjectInstance>();
	
	public void storeRegisteredMBean(ObjectInstance instance){
		registeredMBeans.add(instance);
	}
	
	@SuppressWarnings("unchecked")
	public List<ObjectInstance> listRegisteredMBeans(){
		return (List<ObjectInstance>) registeredMBeans.clone();
	}

}
