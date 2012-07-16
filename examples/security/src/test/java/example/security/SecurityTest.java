package example.security;

import static junit.framework.Assert.*;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.security.AuthorizationException;
import br.gov.frameworkdemoiselle.security.SecurityContext;

@RunWith(DemoiselleRunner.class)
public class SecurityTest {
	
	@Inject
	private Login credential;
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private Business business;
	
	public void setUp(){
		credential.setLogin("admin");
		credential.setPassword("admin");
		securityContext.login();
	}
	
	/* Verficar se o usuário está logado */
	@Test
	public void isLogged() {
		setUp();
		assertTrue(securityContext.isLoggedIn());
	}
	
	/* Verificação de logout */
	@Test
	public void isNotLogged(){
		setUp();
		securityContext.logout();
		assertFalse(securityContext.isLoggedIn());
	}
	
	/*	 */
	@Test
	public void isLoggedFail() {
		credential.setLogin("admin");
		credential.setPassword("123456");
		securityContext.login();
		assertFalse(securityContext.isLoggedIn());
	}
	
	@Test(expected=AuthorizationException.class)
	public void hasPermitionMethodOne(){
		setUp();
		business.businessMethodOne();
		Assert.fail();
	}
	
	@Test
	public void hasPermitionMethodTwo(){
		setUp();
		business.businessMethodTwo();
		assertTrue(true);
	}
	
	@Test(expected=AuthorizationException.class)
	public void hasNoRoleMethodThree(){
		setUp();
		credential.setRole("user");
		business.businessMethodThree();
		Assert.fail();
	}
	
	@Test
	public void hasRoleMethodThree(){
		setUp();
		credential.setRole("admin");
		business.businessMethodThree();
		assertTrue(true);
	}
}
