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
package example.view;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import example.business.AuditingBC;
import example.business.BookBC;
import example.domain.Auditing;
import example.domain.Book;

@ViewController
public class TwoPhaseMB {

	@Inject
	private BookBC bookBC;
	
	@Inject
	private AuditingBC auditingBC;
	
	@Inject
	private MessageContext messageContext;
	
	public List<Book> getBooks() {
		return this.bookBC.findAll();
	}
	
	public List<Auditing> getAuditings(){
		return this.auditingBC.findAll();
	}
	
	public void limparBase() {
		for (Book b : bookBC.findAll()){
			bookBC.delete(b.getId());
		}
		for (Auditing a : auditingBC.findAll()) {
			auditingBC.delete(a.getId());
		}
	}
	
	@Transactional
	public void addBookSuccessfully() {
		limparBase();
		try { 
			bookBC.insert(new Book("Título 1", "Autor 1"));
			auditingBC.insert(new Auditing("INSERT BOOK 1"));
			messageContext.add("Test 1 - OK - Book and Auditing must be inserted!", SeverityType.INFO);
		} catch (Exception e) {
			messageContext.add("Test 1 - ERROR - Book and Auditing should be inserted, but were not!", SeverityType.ERROR);
		}
	}
	
	@Transactional
	public void addBookUnsuccessfully() {
		Book b = new Book("Título 2","Autor 2");
		b.setId(1L);
		try {
			auditingBC.insert(new Auditing("INSERT BOOK 2"));
			bookBC.insert(b);
			messageContext.add("Test 2 - ERRO - Book and Auditing must not be inserted, but were!", SeverityType.ERROR);
		} catch (Exception e) {
			messageContext.add("Test 2 - OK - Book and Auditing must not be inserted and were not!", SeverityType.INFO);
		}
	}
	
}
