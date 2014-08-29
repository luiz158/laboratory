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

import org.apache.commons.lang3.StringUtils;
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
 * Class description: 
 * @author bruno.rafael
 */

@Controller("addBookmarkController")
public class AddBookmarkController
{
	private static final String DEFAULT_FILL_FIELDS_MESSAGE = "Fill all fields.";

	@Inject
	public BookmarkRestClient bookmarkRest;
	
	@Inject
	public CommonMessages message;

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

	@Expose
	public void save()
	{
		BindableView<BookmarkDTO> bindableview = View.of(this);
		BookmarkDTO dto = bindableview.getData();

		String description = dto.getDescription();
		String link = dto.getLink();
		Long id = dto.getId();

		if (!description.equals("") && !link.equals(""))
		{
			if (id != null)
			{
				bookmarkRest.updateData(id, description, link, new Callback<String>()
				{
					@Override
					public void onSuccess(String result)
					{
						FlatMessageBox.show(result, MessageType.SUCCESS);
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
				bookmarkRest.add(description, link, new Callback<String>()
				{
					@Override
					public void onSuccess(String result)
					{
						FlatMessageBox.show(result, MessageType.SUCCESS);
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
			FlatMessageBox.show(DEFAULT_FILL_FIELDS_MESSAGE, MessageType.ERROR);
		}
	}
}
