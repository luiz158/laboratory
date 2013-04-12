package br.gov.frameworkdemoiselle.snmp.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class SNMPAgentTest {
	
	@Test
	public void testAgentBootstrap(){
		try {
			System.in.read();
		} catch (IOException e) {
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

}
