#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 Demoiselle Framework
  ============================================================================
 This file is part of Demoiselle Framework.
 Demoiselle Framework is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License version 3
 as published by the Free Software Foundation.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 You should have received a copy of the GNU Lesser General Public License version 3
 along with this program; if not,  see <http://www.gnu.org/licenses/>
 or write to the Free Software Foundation, Inc., 51 Franklin Street,
 Fifth Floor, Boston, MA  02110-1301, USA.
 ============================================================================
 Este arquivo é parte do Framework Demoiselle.
 O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 do Software Livre (FSF).
 Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 para maiores detalhes.
 Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 ou escreva para a Fundação do Software Livre (FSF) Inc.,
 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package ${package}.client.shared.messages;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("commonMessages")
public interface CommonMessages extends Messages
{
	@DefaultMessage("Bookmark")
	String title();

	@DefaultMessage("Bookmark")
	String menuBookmark();
	
	@DefaultMessage("New")
	String menuNew();
	
	@DefaultMessage("List")
	String menuList();
	
	@DefaultMessage("Link: ")
	String addLink();
	
	@DefaultMessage("Description: ")
	String addDescription();
	
	@DefaultMessage("Save")
	String addSave();
	
	@DefaultMessage("Description")
	String listDescription();
	
	@DefaultMessage("Link")
	String listLink();
	
	@DefaultMessage("Action")
	String listAction();
	
	@DefaultMessage("Id")
	String listId();
	
	@DefaultMessage("Edit")
	String listEdit();
	
	@DefaultMessage("New")
	String listNew();
	
	@DefaultMessage("Delete")
	String listDelete();
	
	@DefaultMessage("Quit")
	String menuQuit();
	
	@DefaultMessage("Fill all fields.")
	String fillMessage();
	
	@DefaultMessage("Successfully saved!")
	String successfullyMessage();
	
	@DefaultMessage("Successfully removed!")
	String removeMessage();
	
	
	
	@DefaultMessage("Welcome to the example application Bookmark. This is your starting point, feel free to change this application.")
	String home();
	
}

