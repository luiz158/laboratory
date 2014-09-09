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
package org.cruxframework.bookmark.client.datasource;

import java.util.List;

import org.cruxframework.bookmark.client.dto.BookmarkDTO;
import org.cruxframework.crux.core.client.datasource.LocalPagedDataSource;
import org.cruxframework.crux.core.client.datasource.annotation.DataSource;
import org.cruxframework.crux.core.client.datasource.annotation.DataSourceRecordIdentifier;

/**
 * Descrição da classe: Este DataSoucer provê a estrutura de um bookmarkDTO. 
 * É utilizado pela view listbookmark para fornecer dados para a Grid.
 * @author bruno.rafael
 */
@DataSource("bookmarkDS")
@DataSourceRecordIdentifier("id")
public class BookmarkDS extends LocalPagedDataSource<BookmarkDTO>
{
	private List<BookmarkDTO> bookmark;

	@Override
	public void load()
	{
		updateData(getBookmark());
	}

	/**
	 * @return the bookmark
	 */
	public List<BookmarkDTO> getBookmark()
	{
		return bookmark;
	}

	/**
	 * @param bookmark the bookmark to set
	 */
	public void setBookmark(List<BookmarkDTO> bookmark)
	{
		this.bookmark = bookmark;
	}
}
