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
package br.gov.demoiselle.escola.business;/* Imports list */


import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Professor;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.persistence.ProfessorDAO;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ProfessorBC extends DelegateCrud<Professor, Long, ProfessorDAO>{

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private MessageContext messageCtx;
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Professor professor) {
		insert(professor);
		messageCtx.add(InfoMessage.PROFESSOR_INSERIDO_OK);
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Professor professor) {
		update(professor);
		messageCtx.add(InfoMessage.PROFESSOR_ALTERADO_OK);
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Professor professor) {
		turmaBC.removerProfessor(professor);
		delete(professor.getId());
		messageCtx.add(InfoMessage.PROFESSOR_EXCLUIDO_OK);
	}
	
}