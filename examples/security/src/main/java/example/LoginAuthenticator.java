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

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

@Alternative
public class LoginAuthenticator implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Inject
	private Login credential;

	private User user;

	@Override
	public boolean authenticate() {
		if ((credential.getLogin().equals("admin")) && (credential.getPassword().equals("admin"))) {
			user = new User() {

				@Override
				public void setAttribute(Object key, Object value) {
					// TODO Auto-generated method stub

				}

				@Override
				public String getId() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Object getAttribute(Object key) {
					// TODO Auto-generated method stub
					return null;
				}
			};

			return true;
		}

		// c�digo de autentica��o

		// credencial -> banco (verificar usuario e senha)

		// carregar um objeto do tipo User

		return false;
	}

	@Override
	public User getUser() {

		// retornar o objeto User carregado no authenticate

		// c�digo para retornar usu�rio logado
		return user;
	}

	@Override
	public void unAuthenticate() {
		user = null;

		// TODO Auto-generated method stub
	}

}
