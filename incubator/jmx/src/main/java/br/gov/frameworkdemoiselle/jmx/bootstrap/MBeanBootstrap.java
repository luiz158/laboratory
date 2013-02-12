package br.gov.frameworkdemoiselle.jmx.bootstrap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.management.ObjectInstance;

import br.gov.frameworkdemoiselle.internal.context.CustomContext;
import br.gov.frameworkdemoiselle.internal.context.ThreadLocalContext;
import br.gov.frameworkdemoiselle.jmx.internal.DynamicMBeanProxy;
import br.gov.frameworkdemoiselle.jmx.internal.MBeanHelper;
import br.gov.frameworkdemoiselle.jmx.internal.MBeanManager;
import br.gov.frameworkdemoiselle.lifecycle.AfterShutdownProccess;
import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.util.Beans;

public class MBeanBootstrap implements Extension {
	
	protected static List<AnnotatedType<?>> types = Collections.synchronizedList(new ArrayList<AnnotatedType<?>>());
	
	private CustomContext mbeanContext;
	
	public <T> void detectAnnotation(@Observes final ProcessAnnotatedType<T> event, final BeanManager beanManager) {
		if (event.getAnnotatedType().isAnnotationPresent(Managed.class)) {
			types.add(event.getAnnotatedType());
		}
	}
	
	public void createMBeanContext(@Observes final AfterBeanDiscovery event) {
		mbeanContext = new ThreadLocalContext(RequestScoped.class,false,this.getClass().getCanonicalName());
		event.addContext(mbeanContext);
	}

	public void registerAvailableMBeans(@Observes final AfterDeploymentValidation event) {
		MBeanManager manager = Beans.getReference(MBeanManager.class);
		
		for (AnnotatedType<?> type : types) {
			final Class<?> clazz = type.getJavaClass();
			DynamicMBeanProxy beanProxy = new DynamicMBeanProxy(clazz, this.mbeanContext);
			
			StringBuffer name = new StringBuffer()
				.append(clazz.getPackage().getName())
				.append(":name=")
				.append(clazz.getSimpleName());
			ObjectInstance instance = MBeanHelper.register(beanProxy, name.toString());
			manager.storeRegisteredMBean(instance);
		}
	}
	
	public void unregisterAvailableMBeans(@Observes final AfterShutdownProccess event){
		MBeanManager manager = Beans.getReference(MBeanManager.class);
		for (ObjectInstance instance : manager.listRegisteredMBeans()){
			MBeanHelper.unregister(instance.getObjectName());
		}
	}

}
