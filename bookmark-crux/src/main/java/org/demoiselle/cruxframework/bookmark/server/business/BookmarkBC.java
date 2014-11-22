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
package org.demoiselle.cruxframework.bookmark.server.business;

import java.util.ArrayList;
import java.util.List;

import org.cruxframework.crux.core.server.rest.annotation.RestService;
import org.cruxframework.crux.core.shared.rest.annotation.DELETE;
import org.cruxframework.crux.core.shared.rest.annotation.GET;
import org.cruxframework.crux.core.shared.rest.annotation.POST;
import org.cruxframework.crux.core.shared.rest.annotation.PUT;
import org.cruxframework.crux.core.shared.rest.annotation.Path;
import org.cruxframework.crux.core.shared.rest.annotation.PathParam;
import org.demoiselle.cruxframework.bookmark.client.dto.BookmarkDTO;
import org.demoiselle.cruxframework.bookmark.server.domain.Bookmark;
import org.demoiselle.cruxframework.bookmark.server.persistence.BookmarkDAO;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

/**
 * Descrição da classe: Esta classe possui os métodos de acesso ao 
 * banco de dados que são acessados pelos serviços Rest.: 
 * @author alexandre.costa
 */
@BusinessController
@RestService("bookmarkService")
@Path("bookmarks")
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO>
{
	private static final long serialVersionUID = 1L;

	/**
	 * Povoa o banco de dados no início da aplicação.
	 * @see Startup
	 */
	@Startup
	@Transactional
	public void load()
	{
		if (findAll().isEmpty())
		{
			insert(new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Demoiselle SourceForge", "http://sf.net/projects/demoiselle"));
			insert(new Bookmark("Twitter", "http://twitter.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Blog", "http://blog.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Wiki", "http://wiki.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Forum", "http://forum.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("SVN", "http://svn.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Maven", "http://repository.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Downloads", "http://download.frameworkdemoiselle.gov.br"));
		}
	}
	
	/******************************************
	 * Métodos REST
	 ******************************************/
	
	@GET
	public List<BookmarkDTO> get()
	{
		List<Bookmark> list = findAll();
		List<BookmarkDTO> listDTO = parseDTO(list);
		return listDTO;
	}
	
	@DELETE
	@Path("batchdelete")
	@Transactional
	public void deleteBookmarks(List<Long> ids)
	{
		for (Long id : ids)
		{
			delete(id);
		}
	}
	
	@POST
	@Transactional
	public void add(BookmarkDTO bookmark)
	{
		insert(new Bookmark(bookmark.getDescription(), bookmark.getLink()));
	}
	
	@PUT
	@Path("{id}")
	@Transactional
	public void update(@PathParam("id")Long id, BookmarkDTO dto)
	{
		Bookmark bookmark = load(id);
		bookmark.setDescription(dto.getDescription());
		bookmark.setLink(dto.getLink());
		update(bookmark);
	}
	
	/******************************************
	 * Métodos auxiliares
	 ******************************************/

	private List<BookmarkDTO> parseDTO(List<Bookmark> list)
	{
		List<BookmarkDTO> listDTO = new ArrayList<BookmarkDTO>();
		for (Bookmark bookmark : list)
		{
			listDTO.add(bookmark.getDTO());
		}
		return listDTO;
	}
}
