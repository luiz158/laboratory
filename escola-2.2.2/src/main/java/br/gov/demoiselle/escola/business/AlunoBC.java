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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.enterprise.inject.Instance;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Email;
import br.gov.demoiselle.escola.bean.Endereco;
import br.gov.demoiselle.escola.bean.Foto;
import br.gov.demoiselle.escola.bean.Telefone;
import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.config.EscolaConfig;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.exception.EscolaException;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.persistence.AlunoDAO;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO>{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Instance<UsuarioBC> usuarioBC;	
	
	@Inject
	private MessageContext messageCtx;
	
	@Inject
	private EscolaConfig escolaConfig;
	
	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private NotaBC notaBC;

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Aluno aluno, Foto foto) {
		try{
			Usuario usuario = aluno.getUsuario();
			usuarioBC.get().inserir(usuario);
			aluno.setUsuario(usuario);
			getDelegate().insert(aluno);
			salvarFoto(aluno, foto);
			
			messageCtx.add(InfoMessage.ALUNO_INSERIDO_OK);
			
		}
		catch(Exception e){
			throw new EscolaException(ErrorMessage.ALUNO_001, e);
		}
	}	
	
	public void inserir(Aluno aluno) {
		try{
			Usuario usuario = aluno.getUsuario();
			usuario.setNome(aluno.getNome());
			usuarioBC.get().inserir(usuario);
			aluno.setUsuario(usuario);
			
			getDelegate().insert(aluno);
			messageCtx.add(InfoMessage.ALUNO_INSERIDO_OK);
		}
		catch(Exception e){
			throw new EscolaException(ErrorMessage.ALUNO_001, e);
		}
		
	}

	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Aluno aluno, Foto foto) {// throws AuthorizationException{
		salvarFoto(aluno, foto);
		getDelegate().update(aluno);
		
		messageCtx.add(InfoMessage.ALUNO_ALTERADO_OK);
	}	
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Aluno aluno) {
		
		try{
			aluno.getUsuario().setNome(aluno.getNome());
			getDelegate().update(aluno);
			messageCtx.add(InfoMessage.ALUNO_ALTERADO_OK);
		}
		catch(Exception e){
			throw new EscolaException(ErrorMessage.ALUNO_001, e);
		}
		
	}
	
	@RequiredRole(value=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Aluno aluno) {
		
		notaBC.remover(aluno);
		delete(aluno.getId());
		messageCtx.add(InfoMessage.ALUNO_EXCLUIDO_OK);
		
	}

	public Aluno buscarAlunoUsuario(Usuario usuario){
		return getDelegate().buscarAluno(usuario);
	}
	
	/*---------------------- ENDERECO ----------------------------------*/
	public Aluno inserirEndereco(Aluno aluno, Endereco detalheEndereco){		
		aluno = load(aluno.getId());
		detalheEndereco.setAluno(aluno);
		aluno.getEnderecos().add(detalheEndereco);
		update(aluno);
		
		return aluno;
	}	
	
	public Aluno alterarEndereco(Aluno aluno, Endereco detalheEndereco){
		getDelegate().alterarDetalhe(detalheEndereco);
		update(aluno);
		
		return load(aluno.getId());
	}		
	
	public Aluno removerEndereco(Aluno aluno, Endereco detalheEndereco){		
		aluno.getEnderecos().remove(detalheEndereco);
		update(aluno);
		
		return aluno;
	}
	
	/*---------------------- EMAIL ----------------------------------*/
	
	public Aluno inserirEmail(Aluno aluno, Email detalheEmail){		
		aluno = load(aluno.getId());
		detalheEmail.setAluno(aluno);
		aluno.getEmails().add(detalheEmail);
		
		update(aluno);
		
		return aluno;
	}	
	
	public Aluno alterarEmail(Aluno aluno, Email detalheEmail){
		getDelegate().alterarDetalhe(detalheEmail);
		update(aluno);
		
		return load(aluno.getId());
	}		
	
	public Aluno removerEmail(Aluno aluno, Email detalheEmail){		
		aluno = load(aluno.getId());
		aluno.getEmails().remove(detalheEmail);
		update(aluno);
		
		return aluno;
	}	
	
	
	/*---------------------- TELEFONE ----------------------------------*/
	
	public Aluno inserirTelefone(Aluno aluno, Telefone detalheTelefone){		
		aluno = load(aluno.getId());
		detalheTelefone.setAluno(aluno);
		aluno.getTelefones().add(detalheTelefone);
		update(aluno);
		
		return aluno;
	}	
	
	public Aluno alterarTelefone(Aluno aluno, Telefone detalheTelefone){
		getDelegate().alterarDetalhe(detalheTelefone);
		update(aluno);
		
		return load(aluno.getId());
	}		
	
	public Aluno removerTelefone(Aluno aluno, Telefone detalheTelefone){		
		aluno = load(aluno.getId());
		aluno.getTelefones().remove(detalheTelefone);
		update(aluno);
		
		return aluno;
	}
	
	
	/*---------------------- TURMA ----------------------------------*/
	
	/**
	 * Incluir um aluno a uma turma
	 */
	public Aluno incluirTurma(Aluno aluno, Turma detalheTurma){
		aluno = load(aluno.getId());	
		detalheTurma = turmaBC.load(detalheTurma.getId());	
		
		/**Trata lotação da turma*/
		if (detalheTurma.getAlunos().size() == detalheTurma.getLotacao() ){
			throw new EscolaException(ErrorMessage.ALUNO_002_01);
		}
		
		/**Trata inclusão duplicada*/
		if (!aluno.getTurmas().contains(detalheTurma)){
			aluno.getTurmas().add(detalheTurma);	
		}else{			
			throw new EscolaException(ErrorMessage.ALUNO_002_02);
		}
		
		detalheTurma.getAlunos().add(aluno);
		messageCtx.add(InfoMessage.ALUNO_MATRICULADO_OK);
		update(aluno);
		
		return aluno;
	}	
	
	public Aluno removerTurma(Aluno aluno, Turma detalheTurma){
		aluno.getTurmas().remove(detalheTurma);
		detalheTurma.getAlunos().remove(aluno);
		update(aluno);
		
		return aluno;
	}		
	
	/*---------------------- UPLOAD -----------------------------*/	

	
	public void salvarFoto(Aluno aluno, Foto foto) {
		if (foto != null){
			try {			
				
				aluno.setFoto(aluno.getId() + "." + FilenameUtils.getExtension(foto.getNome()));
				FacesContext aFacesContext = FacesContext.getCurrentInstance();
				ServletContext context = (ServletContext)aFacesContext.getExternalContext().getContext();

				String path = context.getRealPath(escolaConfig.getUploadPath() + aluno.getFoto());
				
				File file = new File(path);				
		        BufferedInputStream bufferedInputStream = new BufferedInputStream(foto.getInputStream());  
		        FileOutputStream fileOutputStream = new FileOutputStream(file);
		      try {  
		          byte[] buffer = new byte[1024];  
		          int count;  
		          while ((count = bufferedInputStream.read(buffer)) > 0)  
		              fileOutputStream.write(buffer, 0, count);  
		      } finally {  
		          bufferedInputStream.close();  
		          fileOutputStream.close();  
		      }  
		  } catch (IOException exception) {			  
		      exception.printStackTrace();
		  }		
		}
	}

	public Aluno buscarPorUsuario(Usuario usuario) {
		return getDelegate().buscarPorUsuario(usuario);
	}

	public void removerUsuario(Usuario usuario) {
		
		Aluno aluno = buscarAlunoUsuario(usuario);
		
		if(aluno != null){
			aluno.setUsuario(null);
			update(aluno);
		}
	}

}