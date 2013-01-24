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

import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.bean.Professor;
import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.business.TurmaBC;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("/private/pages/turma_listar.xhtml")
public class TurmaEditMB extends AbstractEditPageBean<Turma, Long> {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private Professor professor;
	
	@Inject
	private Disciplina disciplina;
	
	@Override
	protected void handleLoad() {
		setBean(this.turmaBC.load(getId()));
		setDisciplina(getBean().getDisciplina() == null ? new Disciplina() : getBean().getDisciplina());
		setProfessor(getBean().getProfessor() == null ? new Professor() : getBean().getProfessor());
	}
	
	@Transactional
	public String insert() {
		getBean().setProfessor(getProfessor());
		getBean().setDisciplina(getDisciplina());
		turmaBC.inserir(getBean());
		return getPreviousView();
	}

	@Transactional
	public String update() {
		getBean().setProfessor(getProfessor());
		getBean().setDisciplina(getDisciplina());
		turmaBC.alterar(getBean());
		return getPreviousView();
	}

	@Transactional
	public String delete() {
		turmaBC.remover(getBean());
		return getPreviousView();
	}

	public Professor getProfessor() {
		return professor;
	}


	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	public Disciplina getDisciplina() {
		return disciplina;
	}


	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}	
	
}