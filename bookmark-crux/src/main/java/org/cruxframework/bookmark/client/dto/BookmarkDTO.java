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
package org.cruxframework.bookmark.client.dto;

import java.io.Serializable;

import org.cruxframework.crux.core.client.dto.DataObject;

/**
 * Descrição da classe: Classe de transferência de dado entre camadas da aplicação. 
 * @author bruno.rafael
 */
@DataObject("bookmarkDTO")
public class BookmarkDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String description;
	private String link;

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the link
	 */
	public String getLink()
	{
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link)
	{
		this.link = link;
	}

	public void getDTO(Long id, String description, String link)
	{
		setId(id);
		setDescription(description);
		setLink(link);
	}
	
	public boolean isValid()
	{
		return description != null && !description.trim().equals("") && link != null && !link.trim().equals("");
	}
}
