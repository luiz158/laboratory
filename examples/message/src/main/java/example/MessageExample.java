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

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.Message;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;

public class MessageExample {

	@Inject
	private MessageContext messageContext;
	
	public void addSimpleMessage() {
		messageContext.add(" SIMPLE MESSAGE ADDED.");
	}
	
	public void addMessageWithSeverity() {
		messageContext.add(" AN INFO SEVERITY MESSAGE ADDED.", SeverityType.INFO);
		messageContext.add(" AN WARN SEVERITY MESSAGE ADDED." , SeverityType.WARN);
		messageContext.add(" AN ERROR SEVERITY MESSAGE ADDED." , SeverityType.ERROR);
		messageContext.add(" A FATAL SEVERITY MESSAGE ADDED." , SeverityType.FATAL);
	}
	
	public void addMessageWithParameters() {
		Message message = new DefaultMessage(" MESSAGE WITH A INTEGER PARAMETER OF VALUE: {0}, AND A STRING PARAMETER: {1}.");
		messageContext.add(message, new Integer(1), "HELLO WORLD");
	}
	
	public void addMessageWithProperties() {
		Message message = new DefaultMessage("{PROPERTIES_MESSAGE}"); 
		messageContext.add(message);
	}
}