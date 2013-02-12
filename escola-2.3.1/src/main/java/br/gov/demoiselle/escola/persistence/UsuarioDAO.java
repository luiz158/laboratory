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
package br.gov.demoiselle.escola.persistence;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.demoiselle.escola.bean.PapelUsuario;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class UsuarioDAO extends JPACrud<Usuario, Long>{
		
	private static final long serialVersionUID = 1L;

	public Usuario buscar(Usuario usuario) {				
		CriteriaBuilder builder = getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		
		Root<Usuario> from = query.from(Usuario.class);
		
		Predicate predicate = builder.and();
		
		if (usuario.getId() 	!= null) 
			predicate = builder.and(predicate, builder.equal(from.get("id"), usuario.getId()));
		if (usuario.getLogin() != null) 
			predicate = builder.and(predicate, builder.equal(from.get("login"), usuario.getLogin()));
		if (usuario.getSenha() != null) 
			predicate = builder.and(predicate, builder.equal(from.get("senha"), usuario.getSenha()));
		if (usuario.getNome() 	!= null) 
			predicate = builder.and(predicate, builder.equal(from.get("nome"), usuario.getNome()));
		if (usuario.getCpf() 	!= null) 
			predicate = builder.and(predicate, builder.equal(from.get("cpf"), usuario.getCpf()));
				
		TypedQuery<Usuario> typedQuery =  getEntityManager().createQuery(
			    query.select(from).where(predicate).orderBy(builder.asc(from.get("nome")))
		);
		
		Usuario retorno = null;
		
		try{
			retorno = typedQuery.getSingleResult(); 
		}
		catch(NoResultException e){
			
		}
		
		return retorno;
		
	}
	
	public void alterarDetalhe(PapelUsuario detalhePapel) {
		getEntityManager().persist(detalhePapel);
	}
}