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
package br.gov.demoiselle.escola.init;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.bean.Funcionario;
import br.gov.demoiselle.escola.bean.Nota;
import br.gov.demoiselle.escola.bean.PapelUsuario;
import br.gov.demoiselle.escola.bean.Professor;
import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.persistence.AlunoDAO;
import br.gov.demoiselle.escola.persistence.DisciplinaDAO;
import br.gov.demoiselle.escola.persistence.IFuncionarioDAO;
import br.gov.demoiselle.escola.persistence.NotaDAO;
import br.gov.demoiselle.escola.persistence.ProfessorDAO;
import br.gov.demoiselle.escola.persistence.TurmaDAO;
import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Startup;


/**
 * @author SERPRO/CETEC/CTCTA
 */
public class DataSampleLoader {

	@Inject
	private AlunoDAO alunoDAO;
	
	@Inject
	private DisciplinaDAO disciplinaDAO;

	@Inject
	private IFuncionarioDAO funcionarioDAO;
	
	@Inject
	private ProfessorDAO professorDAO;
	
	@Inject
	private TurmaDAO turmaDAO;

	
	@Inject
	private NotaDAO notaDAO;
	

	/**
	 * Efetua a inclusão dos registros no banco de dados.
	 * 
	 * @return	boolean
	 */
	
	@Startup
	@Priority(Priority.MAX_PRIORITY)
	public boolean incluirDados() {

		final int QTDE_FUNCIONARIOS = 5;
		final int QTDE_ALUNOS = 100;
		final int QTDE_TURMAS = 15;
		
		// incluir funcionários
		Funcionario funcionario = null;
		for (int i = 0; i < QTDE_FUNCIONARIOS; i++) {
			funcionario = BeanFactory.createFuncionario();
			funcionarioDAO.insert(funcionario);
		}
		
		// incluir alunos
		Aluno aluno = null;
		for (int i = 0; i < QTDE_ALUNOS; i++) {
			aluno = BeanFactory.createAluno();
			alunoDAO.insert(aluno);
		}
		
		Usuario usuarioAluno = BeanFactory.createUsuario();
		usuarioAluno.setLogin("aluno");
		usuarioAluno.setSenha("aluno");
		usuarioAluno.setPapeis(new HashSet<PapelUsuario>());
		usuarioAluno.getPapeis().add(new PapelUsuario(AliasRole.ROLE_ALUNO));
		
		Usuario usuarioProfessor = BeanFactory.createUsuario();
		usuarioProfessor.setLogin("professor");
		usuarioProfessor.setSenha("professor");
		usuarioProfessor.setPapeis(new HashSet<PapelUsuario>());
		usuarioProfessor.getPapeis().add(new PapelUsuario(AliasRole.ROLE_PROFESSOR));
		
		Usuario usuarioAdmin = BeanFactory.createUsuario();
		usuarioAdmin.setLogin("admin");
		usuarioAdmin.setSenha("admin");
		usuarioAdmin.setPapeis(new HashSet<PapelUsuario>());
		usuarioAdmin.getPapeis().add(new PapelUsuario(AliasRole.ROLE_ADMINISTRADOR));
		
		aluno = BeanFactory.createAluno();
		aluno.setUsuario(usuarioAluno);
		
		alunoDAO.insert(aluno);
		
		aluno = BeanFactory.createAluno();
		aluno.setUsuario(usuarioProfessor);
		
		alunoDAO.insert(aluno);
		
		aluno = BeanFactory.createAluno();
		aluno.setUsuario(usuarioAdmin);
		
		alunoDAO.insert(aluno);
		
		// reordenar lista de alunos
		List<Aluno> alunos = alunoDAO.findAll();
		Collections.sort(alunos, new Comparator<Aluno>() {
			public int compare(Aluno a1, Aluno a2) {
				return a1.getId().compareTo(a2.getId());
			}
		});
		Iterator<Aluno> iterAlunos = alunos.iterator();
		
		// incluir turmas (cada uma com um professor e uma disciplina)
		Turma turma;
		
		Professor professor;
		Disciplina disciplina;
		
		for (int i = 0; i < QTDE_TURMAS; i++) {
			
			turma = BeanFactory.createTurma();
			
			// incluir professor
			professor = BeanFactory.createProfessor();
			professorDAO.insert(professor);
			
			// incluir disciplina
			disciplina = BeanFactory.createDisciplina();
			disciplinaDAO.insert(disciplina);
			
			turma.setProfessor(professor);
			turma.setDisciplina(disciplina);
			
			turmaDAO.insert(turma);
			
			// matricular alguns alunos na turma de acordo com a lotação máxima
			int ocupacao = (int) (turma.getLotacao() - 10 + Math.random() * 5);
			for (int j = 0; j < ocupacao; j++) {
				if (!iterAlunos.hasNext()) {
					iterAlunos = alunos.iterator();
				}
				aluno = iterAlunos.next();
				turma.getAlunos().add(aluno);
				aluno.getTurmas().add(turma);
			}
			
			// lançar notas dos alunos na turma
			Iterator<Aluno> it = turma.getAlunos().iterator();
			while (it.hasNext()) {
				Aluno al = (Aluno) it.next();
				Nota nota = new Nota();
				nota.setAluno(al);
				nota.setDisciplina(disciplina);
				nota.setDataRegistro(BeanFactory.createData());
				nota.setValor(BeanFactory.createFloatValue(0, 10));
				
				notaDAO.insert(nota);
				
			}
			
		}
	
		return true;
	}

}
