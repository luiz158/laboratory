package br.gov.frameworkdemoiselle.jmx.bootstrap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.management.ObjectInstance;

import br.gov.frameworkdemoiselle.internal.context.CustomContext;
import br.gov.frameworkdemoiselle.jmx.configuration.JMXConfig;
import br.gov.frameworkdemoiselle.jmx.internal.DynamicMBeanProxy;
import br.gov.frameworkdemoiselle.jmx.internal.MBeanHelper;
import br.gov.frameworkdemoiselle.jmx.internal.MBeanManager;
import br.gov.frameworkdemoiselle.jmx.internal.NotificationEventListener;
import br.gov.frameworkdemoiselle.lifecycle.AfterShutdownProccess;
import br.gov.frameworkdemoiselle.management.internal.ManagedType;
import br.gov.frameworkdemoiselle.management.internal.ManagementExtension;
import br.gov.frameworkdemoiselle.util.Beans;

public class JMXManagementExtension implements ManagementExtension {
	
	public void registerNotificationMBean(){
		MBeanManager mbeanManager = Beans.getReference(MBeanManager.class);
		JMXConfig configuration = Beans.getReference(JMXConfig.class);

		StringBuffer notificationMBeanName = new StringBuffer()
			.append( configuration.getNotificationDomain()!=null ? configuration.getNotificationDomain() : "br.gov.frameworkdemoiselle.jmx" )
			.append(":name=")
			.append(configuration.getNotificationMBeanName());
		
		if (mbeanManager.findMBeanInstance(notificationMBeanName.toString()) == null){
			NotificationEventListener listener = Beans.getReference(NotificationEventListener.class);

			ObjectInstance instance = MBeanHelper.register(listener.createNotificationBroadcaster(), notificationMBeanName.toString());
			mbeanManager.storeRegisteredMBean(instance);
		}
	}
	
	public void unregisterAvailableMBeans(@Observes final AfterShutdownProccess event){
		MBeanManager manager = Beans.getReference(MBeanManager.class);
		for (ObjectInstance instance : manager.listRegisteredMBeans()){
			MBeanHelper.unregister(instance.getObjectName());
		}
		
		manager.cleanRegisteredMBeans();
	}

	@Override
	public void initialize(List<ManagedType> managedTypes) {
		MBeanManager manager = Beans.getReference(MBeanManager.class);
		JMXConfig configuration = Beans.getReference(JMXConfig.class);
		
		for (ManagedType type : managedTypes) {
			DynamicMBeanProxy beanProxy = new DynamicMBeanProxy(type.getType());

			StringBuffer name = new StringBuffer()
				.append( configuration.getMbeanDomain()!=null ? configuration.getMbeanDomain() : clazz.getPackage().getName() )
				.append(":name=")
				.append( configuration.getMbeanDomain()!=null ? configuration.getMbeanDomain() : clazz.getSimpleName());

			if (manager.findMBeanInstance(name.toString()) == null){
				ObjectInstance instance = MBeanHelper.register(beanProxy, name.toString());
				manager.storeRegisteredMBean(instance);
			}
		}

		registerNotificationMBean();
	}

	@Override
	public void shutdown(List<ManagedType> managedTypes) {
		// TODO Auto-generated method stub
		
	}

}
