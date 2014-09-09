/*
 * Copyright 2011 cruxframework.org.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cruxframework.bookmark.client.controller;

import org.cruxframework.bookmark.client.dto.BookmarkDTO;
import org.cruxframework.bookmark.client.service.BookmarkRestClient;
import org.cruxframework.bookmark.client.shared.messages.CommonMessages;
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

/**
 * Descrição da classe: Está classe possui os métodos de controle da ação de adicionar um bookmark.
 *  
 * @author bruno.rafael
 */
@Controller("addBookmarkController")
public class AddBookmarkController
{
	@Inject
	public BookmarkRestClient bookmarkRest;
	
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
				bookmarkRest.update(dto.getId(), dto, new Callback<Void>()
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
				bookmarkRest.add(dto, new Callback<Void>()
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
