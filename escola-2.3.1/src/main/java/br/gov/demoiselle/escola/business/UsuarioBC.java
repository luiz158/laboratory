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

import br.gov.demoiselle.escola.bean.PapelUsuario;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.exception.EscolaException;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO>{
	
	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	private MessageContext messageCtx;
	
	private static final long serialVersionUID = 1L;
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Usuario usuario) {
		try{
			insert(usuario);
			messageCtx.add(InfoMessage.USUARIO_INSERIDO_OK);
		}
		catch(Exception e){
			throw new EscolaException(ErrorMessage.USUARIO_001);
		}
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Usuario usuario) {
		update(usuario);
		messageCtx.add(InfoMessage.USUARIO_ALTERADO_OK);
	}
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Usuario usuario) {
		
		alunoBC.removerUsuario(usuario);
		delete(usuario.getId());
		
		messageCtx.add(InfoMessage.USUARIO_EXCLUIDO_OK);
		
	}

	public Usuario buscar(Usuario usuario) {
		return getDelegate().buscar(usuario);
	}

	/*---------------------- PAPEL ----------------------------------*/
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public Usuario inserirPapel(Usuario usuario, PapelUsuario detalhePapel){		
		usuario = buscar(usuario);
		
		detalhePapel.setUsuario(usuario);
		usuario.getPapeis().add(detalhePapel);
		update(usuario);
		
		return usuario;
	}	
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public Usuario alterarPapel(Usuario usuario, PapelUsuario detalhePapel){
		getDelegate().alterarDetalhe(detalhePapel);
		return buscar(usuario);
	}		
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public Usuario removerPapel(Usuario usuario, PapelUsuario detalhePapel){		
		usuario = buscar(usuario);
		usuario.getPapeis().remove(detalhePapel);
		update(usuario);
		
		return usuario;
	}

	public Usuario login(Usuario usuario) {
		return buscar(usuario);
	}
	
}