package br.gov.frameworkdemoiselle.management.bootstrap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

import br.gov.frameworkdemoiselle.internal.context.Contexts;
import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.management.internal.ManagedContext;
import br.gov.frameworkdemoiselle.management.internal.ManagedType;
import br.gov.frameworkdemoiselle.management.internal.MonitoringManager;
import br.gov.frameworkdemoiselle.util.Beans;


public class ManagementBootstrap implements Extension {
	
	protected static List<AnnotatedType<?>> types = Collections.synchronizedList(new ArrayList<AnnotatedType<?>>());
	
	private ManagedContext managementContext;
	
	public <T> void detectAnnotation(@Observes final ProcessAnnotatedType<T> event, final BeanManager beanManager) {
		if (event.getAnnotatedType().isAnnotationPresent(Managed.class)) {
			types.add(event.getAnnotatedType());
		}
	}
	
	public void activateContexts(@Observes final AfterBeanDiscovery event) {
		managementContext = new ManagedContext();
		managementContext.setActive(false);
		Contexts.add(managementContext, event);
	}
	
	public void registerAvailableManagedTypes(@Observes final AfterDeploymentValidation event) {

		MonitoringManager manager = Beans.getReference(MonitoringManager.class);
		
		for (AnnotatedType<?> type : types) {
			ManagedType managedType = new ManagedType(type.getJavaClass());
			manager.addManagedType(managedType);
		}
	}

}
