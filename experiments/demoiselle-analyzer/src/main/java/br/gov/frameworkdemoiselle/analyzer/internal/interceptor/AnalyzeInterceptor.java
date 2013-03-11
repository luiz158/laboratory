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
package br.gov.frameworkdemoiselle.analyzer.internal.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.analyzer.Analyze;
import br.gov.frameworkdemoiselle.analyzer.util.MemoryAnalyzer;
import br.gov.frameworkdemoiselle.analyzer.util.Node;

@Analyze
@Interceptor
public class AnalyzeInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	// @Inject
	// private AnalyzeConf conf;

	private StringBuffer buffer;

	@AroundInvoke
	public Object manage(final InvocationContext ic) throws Exception {
		Object result = ic.proceed();

		Node node = MemoryAnalyzer.analyze(ic.getTarget());
		print(node);

		return result;
	}

	private static String getTabulation(int count) {
		String result = "";

		for (int i = 0; i < (count + 1) * 4; i++) {
			result += " ";
		}

		return result;
	}

	private void println(String message) {
		if (buffer == null) {
			buffer = new StringBuffer();
		}

		buffer.append(message + "\n");
	}

	private void print(Node node) {
		println(getTabulation(node.getLevel()) + node.getName() + " (" + node.getSize() + " bytes)");

		for (Node child : node.getChildren()) {
			print(child);
		}

		if (node.getParent() == null) {
			logger.info(buffer.toString());
		}
	}
}
