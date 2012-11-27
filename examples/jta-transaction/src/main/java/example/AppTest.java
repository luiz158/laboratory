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

import javax.inject.Inject;
import javax.validation.ValidationException;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transaction;
import br.gov.frameworkdemoiselle.transaction.TransactionContext;

public class AppTest {

	@Inject
	private PersonManager manager;

	@Inject
	private TransactionContext context;
	
	@Inject
	Logger logger;
	

	@Startup
	public void atomicInsertSuccessful() {
		manager.clean();
		
		Person person1 = new Person("Alberto Santos Dumont");
		Person person2 = new Person("Orville Wright");

		manager.insert(person1, person2);

		if (!manager.contains("Alberto Santos Dumont") || !(manager.contains("Orville Wright"))) {
			logger.info("atomicInsertSuccessful() failed");
		}
	}

	@Startup
	public void automaticPartialRollback() {
		manager.clean();
		
		Person person1 = new Person("Wilbur Wright");
		Person person2 = new Person("Wilbur Wright");

		try {
			manager.insert(person1);
			manager.insert(person2);
			
		} catch (ValidationException cause) {
			cause.printStackTrace();
		}

		if (!Long.valueOf(1).equals(manager.count())) {
			logger.info("automaticPartialRollback() failed");
		}
		
	}	

	@Startup
	public void automaticFullRollback() {
		manager.clean();
		
		Person person1 = new Person("Orville Wright");
		Person person2 = new Person("Orville Wright");

		try {
			manager.insert(person1, person2);

		} catch (ValidationException cause) {
			cause.printStackTrace();
		}

		if (!Long.valueOf(0).equals(manager.count())) {
			logger.info("automaticFullRollback() failed");
		}
	}

	@Startup
	public void manualPartialRollback() {
		manager.clean();
		
		Person person1 = new Person("Alberto Santos Dumont");
		Person person2 = new Person("Orville Wright");

		Transaction transaction = context.getCurrentTransaction();

		transaction.begin();
		manager.insert(person1);
		transaction.rollback();

		manager.insert(person2);

		if (manager.contains("Alberto Santos Dumont") || !manager.contains("Orville Wright")) {
			//System.out.println("manualPartialRollback() failed");
			logger.info("manualPartialRollback() failed");
		}
	}
	
	@Startup
	public void manualFullRollback() {
		manager.clean();
		
		Person person1 = new Person("Alberto Santos Dumont");
		Person person2 = new Person("Orville Wright");

		Transaction transaction = context.getCurrentTransaction();

		transaction.begin();
		manager.insert(person1);
		manager.insert(person2);
		transaction.rollback();

		if (manager.contains("Alberto Santos Dumont") || manager.contains("Orville Wright")) {
			//System.out.println("manualPartialRollback() failed");
			logger.info("manualFullRollback() failed");
		}		
		
	}	

	
	
}
