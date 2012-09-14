/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package example;

import java.util.Locale;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.ResourceBundleExample;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.util.Beans;

@RunWith(DemoiselleRunner.class)
public class ResourceBundleTest {
	
	@Test
	public void printPortugueseMessageLocaleDefault() {
		
		ResourceBundleExample resourceBundleExample = Beans.getReference(ResourceBundleExample.class);
		
		System.out.println("LOCALE: " + Locale.getDefault());
		System.out.println("MENSAGEM: " + resourceBundleExample.getMessage());
		
		Assert.assertEquals(resourceBundleExample.getMessage(), "mensagem em portugues");
	}
	
	@Test
	public void printEnglishMessage() {
	
		Locale.setDefault(Locale.ENGLISH);
		
		ResourceBundleExample resourceBundleExample = Beans.getReference(ResourceBundleExample.class);
		
		System.out.println("LOCALE: " + resourceBundleExample.getResourceBundle().getLocale());
		System.out.println("MENSAGEM: " + resourceBundleExample.getMessage());
		
		Assert.assertEquals(resourceBundleExample.getMessage(), "english message");
	}
	
	@Test
	public void printSpanishMessage() {
		
		Locale.setDefault(new Locale("es"));
		
		ResourceBundleExample resourceBundleExample = Beans.getReference(ResourceBundleExample.class);
		
		System.out.println("LOCALE: " + resourceBundleExample.getResourceBundle().getLocale());
		System.out.println("MENSAGEM: " + resourceBundleExample.getMessage());
		
		Assert.assertEquals(resourceBundleExample.getMessage(), "mensaje en espanol");
	}
	
	@Test
	public void printPortugueseMessage() {
		
		Locale.setDefault(new Locale("pt"));
		
		ResourceBundleExample resourceBundleExample = Beans.getReference(ResourceBundleExample.class);
		
		System.out.println("LOCALE: " + resourceBundleExample.getResourceBundle().getLocale());
		System.out.println("MENSAGEM: " + resourceBundleExample.getMessage());
		
		Assert.assertEquals(resourceBundleExample.getMessage(), "mensagem em portugues");
	}
	
}
