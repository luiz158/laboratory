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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import javax.inject.Inject;
import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.transaction.Transaction;
import br.gov.frameworkdemoiselle.transaction.TransactionContext;

@RunWith(DemoiselleRunner.class)
public class JPATransactionTest {

	@Inject
	private PersonManager manager;

	@Inject
	private TransactionContext context;

	@Before
	public void before() {
		manager.clean();
	}

	@Test
	public void atomicInsertSuccessful() {
		Person person1 = new Person("Alberto Santos Dumont");
		Person person2 = new Person("Orville Wright");

		manager.insert(person1, person2);

		assertTrue(manager.contains("Alberto Santos Dumont"));
		assertTrue(manager.contains("Orville Wright"));
	}

	@Test
	public void automaticPartialRollback() {
		Person person1 = new Person("Wilbur Wright");
		Person person2 = new Person("Wilbur Wright");

		try {
			manager.insert(person1);
			manager.insert(person2);
			fail();

		} catch (ValidationException cause) {
			cause.printStackTrace();
		}

		assertEquals(Long.valueOf(1), manager.count());
	}

	@Test
	public void automaticFullRollback() {
		Person person1 = new Person("Orville Wright");
		Person person2 = new Person("Orville Wright");

		try {
			manager.insert(person1, person2);
			fail();

		} catch (ValidationException cause) {
			cause.printStackTrace();
		}

		assertEquals(Long.valueOf(0), manager.count());
	}

	@Test
	public void manualPartialRollback() {
		Person person1 = new Person("Alberto Santos Dumont");
		Person person2 = new Person("Orville Wright");

		Transaction transaction = context.getCurrentTransaction();

		transaction.begin();
		manager.insert(person1);
		transaction.rollback();

		manager.insert(person2);

		assertFalse(manager.contains("Alberto Santos Dumont"));
		assertTrue(manager.contains("Orville Wright"));
	}

	@Test
	public void manualFullRollback() {
		Person person1 = new Person("Alberto Santos Dumont");
		Person person2 = new Person("Orville Wright");

		Transaction transaction = context.getCurrentTransaction();

		transaction.begin();
		manager.insert(person1);
		manager.insert(person2);
		transaction.rollback();

		assertFalse(manager.contains("Alberto Santos Dumont"));
		assertFalse(manager.contains("Orville Wright"));
	}
}
