package br.gov.frameworkdemoiselle.jmx.internal;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.util.Beans;


@RunWith(DemoiselleRunner.class)
public class DynamicMBeanProxyTest {
	
	@Test
	public void testMBeanInitialization(){
		MBeanManager manager = Beans.getReference(MBeanManager.class);
		
		Assert.assertNotNull(manager);
		Assert.assertNotNull(manager.listRegisteredMBeans());
		Assert.assertEquals(1, manager.listRegisteredMBeans().size());
		
		System.out.println("Servidor iniciado, pressione alguma tecla para fechar");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
