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

import java.util.Iterator;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.bean.Professor;
import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.persistence.TurmaDAO;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class TurmaBC extends DelegateCrud<Turma, Long, TurmaDAO>{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Instance<ProfessorBC> professorBC;
	
	@Inject
	private Instance<DisciplinaBC> disciplinaBC;
	
	@Inject
	private Instance<AlunoBC> alunoBC;
	
	@Inject
	private MessageContext messageCtx;
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Turma turma) {
		insert(turma);
		messageCtx.add(InfoMessage.TURMA_INSERIDA_OK);
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Turma turma) {
		
		turma.setProfessor(professorBC.get().load(turma.getProfessor().getId()));
		turma.setDisciplina(disciplinaBC.get().load(turma.getDisciplina().getId()));
		update(turma);
		
		messageCtx.add(InfoMessage.TURMA_ALTERADA_OK);
		
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Turma turma) {
		
		AlunoBC alunoBC = this.alunoBC.get();
		
		turma = load(turma.getId());
		
		Iterator<Aluno> it = turma.getAlunos().iterator();
		while(it.hasNext()){
			Aluno aluno = it.next();
			aluno = alunoBC.load(aluno.getId());
			aluno.getTurmas().remove(turma);
			alunoBC.update(aluno);
		}
		
		turma.getAlunos().clear();
		
		delete(turma.getId());
		
		messageCtx.add(InfoMessage.TURMA_EXCLUIDA_OK);
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void removerProfessor(Professor professor) {
		
		List<Turma> turmas = buscarTurmas(professor); 
		
		for(Turma turma : turmas){
			turma.setProfessor(null);
			update(turma);
		}
	}

	private List<Turma> buscarTurmas(Professor professor) {
		return getDelegate().buscarPorProfessor(professor);
	}

	public void removerDisciplina(Disciplina disciplina) {
		
		List<Turma> turmas = buscarTurmas(disciplina); 
		
		for(Turma turma : turmas){
			turma.setDisciplina(null);
			update(turma);
		}
		
	}

	private List<Turma> buscarTurmas(Disciplina disciplina) {
		return getDelegate().buscarPorDisciplina(disciplina);
	}

	
}