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
package br.gov.demoiselle.escola.business;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.persistence.DisciplinaDAO;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class DisciplinaBC extends DelegateCrud<Disciplina, Long, DisciplinaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MessageContext messageCtx;
	
	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private NotaBC notaBC;
	
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Disciplina disciplina) {
		getDelegate().insert(disciplina);
		messageCtx.add(InfoMessage.DISCIPLINA_INSERIDA_OK);
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Disciplina disciplina) {
		getDelegate().update(disciplina);
		messageCtx.add(InfoMessage.DISCIPLINA_ALTERADA_OK);
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Disciplina disciplina) {
		turmaBC.removerDisciplina(disciplina);
		notaBC.remover(disciplina);
		delete(disciplina.getId());
		messageCtx.add(InfoMessage.DISCIPLINA_EXCLUIDA_OK);
	}
	
}