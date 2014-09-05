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

import java.util.ArrayList;
import java.util.List;

import org.cruxframework.bookmark.client.datasource.BookmarkDS;
import org.cruxframework.bookmark.client.dto.BookmarkDTO;
import org.cruxframework.bookmark.client.service.BookmarkRestClient;
import org.cruxframework.bookmark.client.shared.messages.CommonMessages;
import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.controller.Controller;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.core.client.screen.views.BindView;
import org.cruxframework.crux.core.client.screen.views.WidgetAccessor;
import org.cruxframework.crux.widgets.client.deviceadaptivegrid.DeviceAdaptiveGrid;
import org.cruxframework.crux.widgets.client.dialog.FlatMessageBox;
import org.cruxframework.crux.widgets.client.dialog.FlatMessageBox.MessageType;
import org.cruxframework.crux.widgets.client.event.SelectEvent;
import org.cruxframework.crux.widgets.client.grid.DataRow;
import org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer;

import com.google.gwt.user.client.ui.Widget;

/**
 * Descrição da classe: Está classe possui os métodos de controle da tela de listagem de bookmark.
 * 
 * @author bruno.rafael
 */
@Controller("listBookmarkController")
public class ListBookmarkController
{
	@Inject
	public GridView gridView;

	@Inject
	public BookmarkRestClient bookmarkRest;

	@Inject
	public CommonMessages message;
	
	@Expose
	public void onActivate()
	{
		loadData();
	}
	
	/**
	 * Este método substitui a view que está na SimpleViewContainer container pela view addbookmark.
	 */
	@Expose
	public void add()
	{
		SimpleViewContainer container = (SimpleViewContainer) Screen.get("container");
		container.showView("addbookmark");
	}
	
	/**
	 * Este método processa quais são os registros marcados no checkbox da grid e 
	 * executa o serviço rest para deletar os registros selecionados.
	 */
	@Expose
	public void delete()
	{
		final DeviceAdaptiveGrid grid = gridView.gridSample();
		List<DataRow> list = grid.getSelectedRows();
		List<Long> ids = new ArrayList<Long>();

		for (DataRow dataRow : list)
		{
			BookmarkDTO dto = (BookmarkDTO) dataRow.getDataSourceRecord().getRecordObject();
			ids.add(dto.getId());
		}

		bookmarkRest.deleteBookmarks(ids, new Callback<Void>()
		{
			@Override
			public void onSuccess(Void result)
			{
				loadData();
				FlatMessageBox.show(message.removeMessage(), MessageType.SUCCESS);
			}

			@Override
			public void onError(Exception e)
			{
				Crux.getErrorHandler().handleError(e);
			}
		});
	}

	@Expose
	public void update(SelectEvent selectEvent)
	{
		DeviceAdaptiveGrid grid = gridView.gridSample();
		DataRow row = grid.getRow((Widget) selectEvent.getSource());
		BookmarkDTO dto = (BookmarkDTO) row.getBoundObject();
		SimpleViewContainer views = (SimpleViewContainer) Screen.get("container");
		views.showView("addbookmark", "addbookmark", dto);
	}

	private void loadData()
	{
		final DeviceAdaptiveGrid grid = gridView.gridSample();
		final BookmarkDS bookmarkDS = (BookmarkDS) grid.getDataSource();

		bookmarkRest.get(new Callback<List<BookmarkDTO>>()
		{
			@Override
			public void onSuccess(List<BookmarkDTO> result)
			{
				bookmarkDS.setBookmark(result);
				grid.clear();
				grid.loadData();
				grid.refresh();
			}

			@Override
			public void onError(Exception e)
			{
				Crux.getErrorHandler().handleError(e);
			}
		});
	}
	
	/**
	 * Class description: 
	 * @author bruno.rafael
	 */
	@BindView("listbookmark")
	public static interface GridView extends WidgetAccessor
	{
		DeviceAdaptiveGrid gridSample();
	}
}