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
package org.cruxframework.bookmark.server.cdi;

import javax.servlet.ServletContext;

import org.cruxframework.crux.core.server.rest.core.registry.RestServiceFactoryImpl;

import br.gov.frameworkdemoiselle.util.Beans;

/**
 * Class description: 
 * @author bruno.rafael
 */
public class CdiServiceFactory extends RestServiceFactoryImpl
{
	@Override
	public Object getService(Class<?> serviceClass)
	{
		return Beans.getReference(serviceClass);
	}

	@Override
	public void initialize(ServletContext context)
	{
		super.initialize(context);
	}
}
