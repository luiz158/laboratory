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

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

/**
 * Class description: 
 * @author alexandre.costa
 */
@BusinessController
@RestService("bookmarkService")
@Path("bookmark")
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO>
{
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_SUCCESS_MESSAGE = "Successfully saved!";

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

	@PUT
	@Path("{bookmark}")
	@Transactional
	public String add(@FormParam("description") String description, @FormParam("link") String link)
	{
		insert(new Bookmark(description, link));
		return DEFAULT_SUCCESS_MESSAGE;
	}

	@POST
	@Path("{id}")
	@Transactional
	public String updateData(@FormParam("id") Long id, @FormParam("description") String description,
			@FormParam("link") String link)
	{
		Bookmark bookmark = load(id);
		bookmark.setDescription(description);
		bookmark.setLink(link);
		update(bookmark);
		return DEFAULT_SUCCESS_MESSAGE;
	}

	@DELETE
	@Transactional
	public String remove(List<BookmarkDTO> list)
	{
		for (BookmarkDTO bookmarkDTO : list)
		{
			delete(bookmarkDTO.getId());
		}

		return DEFAULT_SUCCESS_MESSAGE;
	}

	@GET
	public List<BookmarkDTO> list()
	{
		List<Bookmark> list = findAll();
		List<BookmarkDTO> listDTO = parseDTO(list);
		return listDTO;
	}

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
