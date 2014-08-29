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

import org.cruxframework.bookmark.client.shared.messages.CommonMessages;
import org.cruxframework.crux.core.client.controller.Controller;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer;

/**
 * Class description:
 * @author bruno.rafael
 */
@Controller("menuController")
public class MenuController
{
	
	@Inject
	public CommonMessages message;
	
	@Expose
	public void add()
	{
		SimpleViewContainer container = (SimpleViewContainer) Screen.get("container");
		container.showView("addbookmark");
	}
	
	@Expose
	public void list()
	{
		SimpleViewContainer container = (SimpleViewContainer) Screen.get("container");
		container.showView("listbookmark");
	}
}
