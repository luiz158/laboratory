package br.gov.frameworkdemoiselle.jmx.tests.internal;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.jmx.internal.MBeanManager;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.util.Beans;

@RunWith(DemoiselleRunner.class)
public class DynamicMBeanProxyTest {

	/**
	 * Testa se o bootstrap está corretamente carregando e registrando classes anotadas com {@link Managed} como MBeans.
	 */
	@Test
	public void testMBeanInitialization() {
		MBeanManager manager = Beans.getReference(MBeanManager.class);

		Assert.assertNotNull(manager);
		Assert.assertNotNull(manager.listRegisteredMBeans());
		Assert.assertEquals(1, manager.listRegisteredMBeans().size());
	}

	@Test
	public void testOperationInvocation() {
		
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		
		ObjectName name=null;
		try {
			name = new ObjectName("br.gov.frameworkdemoiselle.jmx.domain:name=ManagedTestClass");
		} catch (MalformedObjectNameException e) {
			Assert.fail();
		}
		
		try {
			server.invoke(name, "operacao",new Object[]{"Teste"},new String[]{"String"});
		} catch (Exception e) {
			Assert.fail();
		}
		
	}

}
