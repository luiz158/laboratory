 /* 
 * Demoiselle Sample
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 * 
 * Demoiselle Sample is a set of open source Java EE project samples using
 * the Demoiselle Framework
 *   
 * Demoiselle Sample is released under the terms of the GPL license 3
 * http://www.gnu.org/licenses/gpl.html  GPL License 3
 *   
 * This file is part of Demoiselle Sample.
 * 
 * Demoiselle Sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3.
 * 
 * Demoiselle Sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Demoiselle - Sample.  If not, see <http://www.gnu.org/licenses/>
 */ 
package br.gov.demoiselle.escola.ui.managedbean;

import javax.inject.Inject;

import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

/**
 * @author Vanderson Botelho
 * 
 */
@ViewController
public class UsuarioMB {

	@Inject
	private SecurityContext securityCtx;

	/**
	 * Utiliza o contexto de mensagem para verificar se o usuário possui a role
	 * de administrador
	 * 
	 * @return true se for adminstrador, caso contrário false
	 */
	public boolean getIsAdministrador() {
		return securityCtx.hasRole(AliasRole.ROLE_ADMINISTRADOR);		
	}

	/**
	 * Informa se o usuário corrente tem o perfil de professor
	 * 
	 * @return
	 */
	public boolean getIsProfessor() {
		return securityCtx.hasRole(AliasRole.ROLE_PROFESSOR);		
	}

	/**
	 * Informa se o usuário corrente temo perfil de usuario
	 * 
	 * @return
	 */
	public boolean getIsAluno() {
		return securityCtx.hasRole(AliasRole.ROLE_ALUNO);		
	}

}
