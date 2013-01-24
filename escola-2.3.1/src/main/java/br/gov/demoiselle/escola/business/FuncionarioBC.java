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

import java.util.List;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Funcionario;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.exception.EscolaException;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.persistence.IFuncionarioDAO;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class FuncionarioBC extends DelegateCrud<Funcionario, Long, IFuncionarioDAO>{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MessageContext messageCtx;

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Funcionario funcionario) {
		try{
			insert(funcionario);
			
			messageCtx.add(InfoMessage.FUNCIONARIO_INSERIDO_OK);
		}
		catch(Exception e){
			throw new EscolaException(ErrorMessage.FUNCIONARIO_001, e);
		}	
	}	
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Funcionario funcionario) {
		try{
			update(funcionario);
			
			messageCtx.add(InfoMessage.FUNCIONARIO_ALTERADO_OK);
		}
		catch(Exception e){
			throw new EscolaException(ErrorMessage.FUNCIONARIO_001, e);
		}
	}	
	
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public Funcionario buscar(Funcionario funcionario) {
		try{
			return load(funcionario.getId());
		}
		catch(Exception e){
			throw new EscolaException(ErrorMessage.FUNCIONARIO_001, e);
		}
	}	

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Funcionario acesso) {
		delete(acesso.getId());
		
		messageCtx.add(InfoMessage.FUNCIONARIO_EXCLUIDO_OK);
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public List<Funcionario> listar() {
		return getDelegate().findAll();
	}
	
}