/*
 * Copyright 2013 cruxframework.org.
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
package org.cruxframework.bookmark.server.business;

import java.util.ArrayList;
import java.util.List;

import org.cruxframework.bookmark.client.dto.BookmarkDTO;
import org.cruxframework.bookmark.server.domain.Bookmark;
import org.cruxframework.bookmark.server.persistence.BookmarkDAO;
import org.cruxframework.crux.core.server.rest.annotation.RestService;
import org.cruxframework.crux.core.shared.rest.annotation.DELETE;
import org.cruxframework.crux.core.shared.rest.annotation.FormParam;
import org.cruxframework.crux.core.shared.rest.annotation.GET;
import org.cruxframework.crux.core.shared.rest.annotation.POST;
import org.cruxframework.crux.core.shared.rest.annotation.PUT;
import org.cruxframework.crux.core.shared.rest.annotation.Path;
import org.cruxframework.crux.core.shared.rest.annotation.PathParam;

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
