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

import java.util.ArrayList;
import java.util.List;

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
import ${package}.client.datasource.BookmarkDS;
import ${package}.client.dto.BookmarkDTO;
import ${package}.client.service.BookmarkRestClient;
import ${package}.client.shared.messages.CommonMessages;

import com.google.gwt.user.client.Window;
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
	public BookmarkRestClient BookmarkRest;

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

		BookmarkRest.deleteBookmarks(ids, new Callback<Void>()
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
		
		BookmarkRest.get(new Callback<List<BookmarkDTO>>()
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