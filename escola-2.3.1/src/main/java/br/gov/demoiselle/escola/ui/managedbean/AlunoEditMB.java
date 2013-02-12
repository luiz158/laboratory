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


import java.io.IOException;

import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Email;
import br.gov.demoiselle.escola.bean.Endereco;
import br.gov.demoiselle.escola.bean.Foto;
import br.gov.demoiselle.escola.bean.Telefone;
import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.business.AlunoBC;
import br.gov.demoiselle.escola.config.EscolaConfig;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("/private/pages/aluno_listar.xhtml")
public class AlunoEditMB extends AbstractEditPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoBC alunoBC;

	@Inject
	private Usuario usuario;
	
	@Inject
	private Endereco detalheEndereco;
	
	@Inject
	private Email detalheEmail;
	
	@Inject
	private Telefone detalheTelefone;
	
	@Inject
	private Turma detalheTurma;
	
	@Inject
	private EscolaConfig escolaConfig;
	
	@Override
	protected void handleLoad() {
		setBean(this.alunoBC.load(getId()));
		setUsuario(getBean().getUsuario() == null ? new Usuario() : getBean().getUsuario());
	}

	public String insert() {
		getBean().setUsuario(getUsuario());
		alunoBC.inserir(getBean());
		return getPreviousView();
	}

	@Transactional
	public String delete() {
		alunoBC.remover(getBean());
		return getPreviousView();
	}

	@Transactional
	public String update() {
		getBean().setUsuario(getUsuario());
		this.alunoBC.alterar(getBean());
		handleLoad();
		return "";
	}

	@Transactional
	public void upload(FileUploadEvent event) {
		Foto foto = null;
		try {
			foto = new Foto(event.getFile().getFileName(), event.getFile().getInputstream());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if(isUpdateMode()){
			alunoBC.alterar(getBean(), foto);
		}
		else{
			alunoBC.inserir(getBean(), foto);
		}
		
	}

	/*--------------------------------- ENDERECO ---------------------------------*/
	
	public String incluirEndereco() {
		setBean(alunoBC.inserirEndereco(getBean(), detalheEndereco));
		setDetalheEndereco(new Endereco());
		return "";
	}

	public String alterarEndereco() {
		setBean(alunoBC.alterarEndereco(getBean(), detalheEndereco));
		setDetalheEndereco(new Endereco());
		return "";
	}

	public String removerEndereco(Endereco endereco) {
		setBean(alunoBC.removerEndereco(getBean(), endereco));
		setDetalheEndereco(new Endereco());
		return "";
	}

	/*--------------------------------- EMAIL ---------------------------------*/
	
	public String incluirEmail() {
		setBean(alunoBC.inserirEmail(getBean(), detalheEmail));
		setDetalheEmail(new Email());
		return "";
	}

	public String alterarEmail() {
		setBean(alunoBC.alterarEmail(getBean(), detalheEmail));
		setDetalheEmail(new Email());
		return "";
	}
	
	public String removerEmail(Email email){
		setBean(alunoBC.removerEmail(getBean(), email));
		setDetalheEmail(new Email());
		return "";
	}

	/*--------------------------------- TELEFONE ---------------------------------*/
	
	public String incluirTelefone() {
		setBean(alunoBC.inserirTelefone(getBean(), detalheTelefone));
		setDetalheTelefone(new Telefone());
		return "";
	}

	public String alterarTelefone() {
		setBean(alunoBC.alterarTelefone(getBean(), detalheTelefone));
		setDetalheTelefone(new Telefone());
		return "";
	}

	public String removerTelefone(Telefone telefone){
		setBean(alunoBC.removerTelefone(getBean(), telefone));
		setDetalheTelefone(new Telefone());
		return "";
	}

	/*--------------------------------- TURMA ---------------------------------*/
	
	public String incluirTurma() {
		setBean(alunoBC.incluirTurma(getBean(), detalheTurma));
		setDetalheTurma(new Turma());
		return "";
	}

	public String removerTurma(Turma turma){
		setBean(alunoBC.removerTurma(getBean(), turma));
		setDetalheTurma(new Turma());
		return "";
	}

	public Endereco getDetalheEndereco() {
		return detalheEndereco;
	}

	public void setDetalheEndereco(Endereco endereco) {
		this.detalheEndereco = endereco;
	}

	public Email getDetalheEmail() {
		return detalheEmail;
	}

	public void setDetalheEmail(Email detalheEmail) {
		this.detalheEmail = detalheEmail;
	}

	public Telefone getDetalheTelefone() {
		return detalheTelefone;
	}

	public void setDetalheTelefone(Telefone detalheTelefone) {
		this.detalheTelefone = detalheTelefone;
	}


	public String getFoto() {
		String foto = escolaConfig.getUploadPath() + ((getBean().getFoto() == null || getBean().getFoto().equals("")) ? "no_image.png"
				: getBean().getFoto());
		return foto;
	}

	public Turma getDetalheTurma() {
		return detalheTurma;
	}

	public void setDetalheTurma(Turma detalheTurma) {
		this.detalheTurma = detalheTurma;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
