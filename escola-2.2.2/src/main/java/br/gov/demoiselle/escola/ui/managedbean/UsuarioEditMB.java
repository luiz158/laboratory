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
package br.gov.demoiselle.escola.ui.managedbean;/* Imports list */


import java.util.List;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.PapelUsuario;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.business.UsuarioBC;
import br.gov.demoiselle.escola.config.PapelConfig;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

/**
 * @author Vanderson Botelho
 * 
 */
@ViewController
@PreviousView("/private/pages/usuario_listar.xhtml")
public class UsuarioEditMB extends AbstractEditPageBean<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC usuarioBC;
	
	@Inject
	private PapelConfig papelConfig;
	
	@Inject
	private PapelUsuario detalhePapel;


	@Override
	protected void handleLoad() {
		setBean(this.usuarioBC.load(getId()));
	}
	

	@Transactional
	public String insert() {
		usuarioBC.inserir(getBean());
		return getPreviousView();
	}

	@Transactional
	public String delete() {
		usuarioBC.remover(getBean());
		return getPreviousView();
	}
	
	@Transactional
	public String update() {
		usuarioBC.alterar(getBean());
		return "";
	}

	public PapelUsuario getDetalhePapel() {
		return detalhePapel;
	}

	public void setDetalhePapel(PapelUsuario detalhePapel) {
		this.detalhePapel = detalhePapel;
	}


//	--------------------------------- PAPEIS ---------------------------------
	
	public List<String> getListaPapeis(){
		return papelConfig.getPapeis();
	}
	
	public String incluirPapel() {
		setBean(usuarioBC.inserirPapel(getBean(), detalhePapel));
		setDetalhePapel(new PapelUsuario());
		return "";
	}

	public String removerPapel(PapelUsuario papel){
		setBean(usuarioBC.removerPapel(getBean(), papel));
		setDetalhePapel(new PapelUsuario());
		return "";
	}

}
