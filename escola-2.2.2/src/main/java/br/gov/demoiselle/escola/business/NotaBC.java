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
import br.gov.demoiselle.escola.bean.Email;
import br.gov.demoiselle.escola.bean.Nota;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.persistence.NotaDAO;
import br.gov.frameworkdemoiselle.mail.Mail;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class NotaBC extends DelegateCrud<Nota, Long, NotaDAO>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MessageContext messageCtx;
	
	@Inject
	private Mail mail;
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private Instance<AlunoBC> alunoBC;

	@RequiredRole(value=AliasRole.ROLE_PROFESSOR)
	public void inserir(Nota nota) {
		insert(nota);
		enviarEmail(nota);
		messageCtx.add(InfoMessage.NOTA_INSERIDA_OK);
	}

	@RequiredRole(value=AliasRole.ROLE_PROFESSOR)
	public void alterar(Nota nota) {
		update(nota);
		messageCtx.add(InfoMessage.NOTA_ALTERADA_OK);
	}

	@RequiredRole(value=AliasRole.ROLE_PROFESSOR)
	public void remover(Nota nota) {
		delete(nota.getId());
		messageCtx.add(InfoMessage.NOTA_EXCLUIDA_OK);
	}

	public List<Nota> listar(Aluno aluno) {
		return getDelegate().listar(aluno);
	}

	public List<Nota> listar(Disciplina disciplina) {
		return getDelegate().listar(disciplina);
	}

	@RequiredRole(value=AliasRole.ROLE_PROFESSOR)
	private void enviarEmail(Nota nota) {
		try {
			
			for(Email email : nota.getAluno().getEmails()){
				mail
		            .to(email.getEndereco())
		            .from("to@server.com")
		            .body().text("Nota Lançada no sistema")
		            .subject("Assunto")
		            .importance().high()
		            .send();
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Disciplina disciplina) {
		
		List<Nota> notas = listar(disciplina);
		
		Iterator<Nota> it = notas.iterator();
		
		while(it.hasNext()){
			delete(it.next().getId());
		}
		
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Aluno aluno) {
		
		List<Nota> notas = listar(aluno);
		
		Iterator<Nota> it = notas.iterator();
		while(it.hasNext()){
			delete(it.next().getId());
		}
		
	}

	public List<Nota> listar(Usuario usuario) {
		
		if(securityContext.hasRole(AliasRole.ROLE_ADMINISTRADOR) || securityContext.hasRole(AliasRole.ROLE_PROFESSOR)){
			return findAll();
		}
		else{
			if(securityContext.hasRole(AliasRole.ROLE_ALUNO)){
				Aluno aluno = alunoBC.get().buscarAlunoUsuario(usuario);
				return listar(aluno);
			}
					
		}
		
		return null;
	}

}