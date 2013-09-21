package br.gov.frameworkdemoiselle.snmp.bootstrap;

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

import br.gov.frameworkdemoiselle.internal.context.CustomContext;
import br.gov.frameworkdemoiselle.internal.context.ThreadLocalContext;
import br.gov.frameworkdemoiselle.lifecycle.AfterShutdownProccess;
import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.snmp.agent.ManagedClassMonitor;
import br.gov.frameworkdemoiselle.snmp.agent.SNMPAgentManager;
import br.gov.frameworkdemoiselle.util.Beans;


public class SNMPBootstrap implements Extension {
	
	protected static List<AnnotatedType<?>> types = Collections.synchronizedList(new ArrayList<AnnotatedType<?>>());
	
	private CustomContext snmpContext;
	
	public <T> void detectAnnotation(@Observes final ProcessAnnotatedType<T> event, final BeanManager beanManager) {
		if (event.getAnnotatedType().isAnnotationPresent(Managed.class)) {
			types.add(event.getAnnotatedType());
		}
	}
	
	public void createMBeanContext(@Observes final AfterBeanDiscovery event) {
		snmpContext = new ThreadLocalContext(RequestScoped.class,false);
		event.addContext(snmpContext);
	}
	
	public void registerAvailableMIBs(@Observes final AfterDeploymentValidation event) {
		SNMPAgentManager agentManager = Beans.getReference(SNMPAgentManager.class);
		
		for (AnnotatedType<?> type : types) {
			final Class<?> clazz = type.getJavaClass();
			ManagedClassMonitor monitor = new ManagedClassMonitor(clazz,snmpContext);
			agentManager.registerManagedClass(monitor);
		}
		
		agentManager.startAgent();
	}
	
	public void stopSNMPAgent(@Observes final AfterShutdownProccess event){
		SNMPAgentManager agentManager = Beans.getReference(SNMPAgentManager.class);
		agentManager.stopAgent();
	}

}
