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
package br.gov.demoiselle.escola.persistence;/* Imports list */

import java.util.List;

import javax.persistence.Query;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.bean.Nota;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;


@PersistenceController
public class NotaDAO extends JPACrud<Nota, Long>{

	private static final long serialVersionUID = 1L;

	
	public List<Nota> listar(Disciplina disciplina) {		
		Query query = createQuery("from Nota nota where nota.disciplina.id = ?");
		query.setParameter(1, disciplina.getId());
		
		return query.getResultList();
	}

	public List<Nota> listar(Aluno aluno) {		
		Query query = createQuery("from Nota nota where nota.aluno.id = ?");
		query.setParameter(1, aluno.getId());
		
		return query.getResultList();
	}
	
	public Nota buscar(Aluno aluno, Disciplina disciplina) {	
		Query query = createQuery("from Nota nota where nota.aluno.id = ? and nota.disciplina.id = ?");
		query.setParameter(1, aluno.getId());
		query.setParameter(2, disciplina.getId());
		
		return (Nota) query.getSingleResult();
	}

	public List<Nota> buscar(Aluno aluno) {			
		
		Query query = createQuery("from Nota as nota where nota.aluno.id = " + aluno.getId());
		List<Nota> retorno = query.getResultList();
		
		if (retorno != null && retorno.size() > 0 )
			return retorno;
		return null;		
	}

}