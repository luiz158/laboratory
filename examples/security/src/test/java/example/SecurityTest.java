/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package example;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import javax.inject.Inject;

import junit.framework.Assert;

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

	public void setUp() {
		credential.setLogin("admin");
		credential.setPassword("admin");
		securityContext.login();
	}

	/* Verficar se o usu�rio est� logado */
	@Test
	public void isLogged() {
		setUp();
		assertTrue(securityContext.isLoggedIn());
	}

	/* Verifica��o de logout */
	@Test
	public void isNotLogged() {
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

	@Test(expected = AuthorizationException.class)
	public void hasPermitionMethodOne() {
		setUp();
		business.businessMethodOne();
		Assert.fail();
	}

	@Test
	public void hasPermitionMethodTwo() {
		setUp();
		business.businessMethodTwo();
		assertTrue(true);
	}

	@Test(expected = AuthorizationException.class)
	public void hasNoRoleMethodThree() {
		setUp();
		credential.setRole("user");
		business.businessMethodThree();
		Assert.fail();
	}

	@Test
	public void hasRoleMethodThree() {
		setUp();
		credential.setRole("admin");
		business.businessMethodThree();
		assertTrue(true);
	}
}
