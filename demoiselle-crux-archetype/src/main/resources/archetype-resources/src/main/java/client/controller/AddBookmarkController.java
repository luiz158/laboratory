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
package ${package}.client.controller;

import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.controller.Controller;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.core.client.screen.views.BindableView;
import org.cruxframework.crux.core.client.screen.views.View;
import org.cruxframework.crux.core.client.screen.views.ViewActivateEvent;
import org.cruxframework.crux.widgets.client.dialog.FlatMessageBox;
import org.cruxframework.crux.widgets.client.dialog.FlatMessageBox.MessageType;
import ${package}.client.dto.BookmarkDTO;
import ${package}.client.service.BookmarkRestClient;
import ${package}.client.shared.messages.CommonMessages;

/**
 * Descrição da classe: Está classe possui os métodos de controle da ação de adicionar um Bookmark.
 *  
 * @author bruno.rafael
 */
@Controller("addBookmarkController")
public class AddBookmarkController
{
	@Inject
	public BookmarkRestClient BookmarkRest;
	
	@Inject
	public CommonMessages message;

	/**Este método recebe um evento de ativação de uma view. Se o event possuir
	 * algum objeto como parâmetro, este método irar passar para a view as 
	 * informações do objeto. 
	 * 
	 * @param event
	 */
	@Expose
	public void onActivate(ViewActivateEvent event) 
	{
		BookmarkDTO dto = event.getParameterObject();
		if (dto != null)
		{
			BindableView<BookmarkDTO> bindableview = View.of(this);
			bindableview.setData(dto);
		}
	}

	/**
	 * Este método processa as informações de um view extraindo um DTO. Se o 
	 * DTO possuir um Id, então o método executa um serviço rest de update. Se
	 * o DTO não possuir um id, então o método faz uma chamada rest para inserir
	 * um novo registro no banco. 
	 */
	@Expose
	public void save()
	{
		BindableView<BookmarkDTO> bindableview = View.of(this);
		BookmarkDTO dto = bindableview.getData();

		if (dto.isValid())
		{
			if (dto.getId() != null)
			{
				BookmarkRest.update(dto.getId(), dto, new Callback<Void>()
				{
					@Override
					public void onSuccess(Void result)
					{
						FlatMessageBox.show(message.successfullyMessage(), MessageType.SUCCESS);
					}

					@Override
					public void onError(Exception e)
					{
						Crux.getErrorHandler().handleError(e);
					}
				});
			}
			else
			{
				BookmarkRest.add(dto, new Callback<Void>()
				{
					@Override
					public void onSuccess(Void result)
					{
						FlatMessageBox.show(message.successfullyMessage(), MessageType.SUCCESS);
					}

					@Override
					public void onError(Exception e)
					{
						Crux.getErrorHandler().handleError(e);
					}
				});
			}
		}
		else
		{
			FlatMessageBox.show(message.fillMessage(), MessageType.ERROR);
		}
	}
}
