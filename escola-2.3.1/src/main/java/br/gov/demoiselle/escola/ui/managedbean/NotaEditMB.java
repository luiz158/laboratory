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

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.bean.Nota;
import br.gov.demoiselle.escola.business.NotaBC;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("/private/pages/nota_listar.xhtml")
public class NotaEditMB extends AbstractEditPageBean<Nota, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NotaBC notaBC;
	
	@Inject
	private Aluno aluno;
	
	@Inject
	private Disciplina disciplina;
	
	@Override
	protected void handleLoad() {
		setBean(this.notaBC.load(getId()));
		setAluno(getBean().getAluno());
		setDisciplina(getBean().getDisciplina());
	}

	@Transactional
	public String insert() {
		getBean().setAluno(getAluno());
		getBean().setDisciplina(getDisciplina());
		notaBC.inserir(getBean());			
		return getPreviousView();
	}	
	
	@Transactional
	public String delete() {
		notaBC.remover(getBean());
		return getPreviousView();
	}
	
	@Transactional
	public String update() {
		getBean().setAluno(getAluno());
		getBean().setDisciplina(getDisciplina());
		notaBC.alterar(getBean());
		return getPreviousView();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
}