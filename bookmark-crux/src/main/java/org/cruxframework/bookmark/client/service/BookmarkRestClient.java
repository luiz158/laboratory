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
package org.cruxframework.bookmark.client.service;

import java.util.List;

import org.cruxframework.bookmark.client.dto.BookmarkDTO;
import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.core.client.rest.RestProxy;
import org.cruxframework.crux.core.client.rest.RestProxy.TargetRestService;

/**
 * Interface que define os métodos de serviço Rest.
 * @author bruno.rafael
 */
@TargetRestService("bookmarkService")
public interface BookmarkRestClient extends RestProxy
{
	void get(Callback<List<BookmarkDTO>> callback);
	void add(BookmarkDTO dto, Callback<Void> callback);
	void deleteBookmarks(List<Long> list, Callback<Void> callback);
	void update(Long id, BookmarkDTO dto, Callback<Void> callback);
}
