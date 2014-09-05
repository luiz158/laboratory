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

/* Descrição da classe: Esta classe é responsável pela integração dos
 * frameworks Demoiselle e Crux.  Através dela, o serviço Rest do Crux
 * captura o contexto do Demoiselle. Esta classe é uma extensão da 
 * classe RestServiceFactoryImpl do Crux, que é um scanner de serviço Rest.
 * Foi necessário configurar o Crux para usar essa classe a cada chamada 
 * Rest, acrescentando no arquivo Crux.properties o código.
 * restServiceFactory=org.cruxframework.bookmark.server.cdi.CdiServiceFactory 
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
