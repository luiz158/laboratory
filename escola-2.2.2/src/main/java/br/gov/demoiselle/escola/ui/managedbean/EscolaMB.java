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

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.gov.demoiselle.escola.business.AlunoBC;
import br.gov.demoiselle.escola.business.ProfessorBC;
import br.gov.demoiselle.escola.business.TurmaBC;
import br.gov.demoiselle.escola.security.Credencial;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Faces;
import br.gov.frameworkdemoiselle.util.FileRenderer;
import br.gov.frameworkdemoiselle.util.FileRenderer.ContentType;

@ViewController
public class EscolaMB {
	
	@Inject
	@Path("reports/ReportAluno.jrxml")
	private Report alunoReport;
	
	@Inject
	@Path("reports/ReportTurma.jrxml")
	private Report turmaReport;
	
	@Inject
	@Path("reports/ReportProfessor.jrxml")
	private Report professorReport;
	
	@Inject
	private FileRenderer renderer;
	
	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private ProfessorBC professorBC;
	
	@Inject
	private Credencial credencial;
	
	@Inject
	private FacesContext facesContext;
	
	public void gerarRelatorio(){
		
		String parametro = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getParameter("ReportName");
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("USER_NAME", credencial.getUsuario().getLogin());
		param.put("SUBREPORT_DIR", "./reports/");

	    try {

	    	String extensao = ".pdf";
	    	Type tipoRelatorio = Type.PDF;
	    	ContentType tipoRelatorioExport = ContentType.PDF;
	    	
	        byte[] buffer = null;
	        
	        if(parametro.contains("odt")){
	        	tipoRelatorioExport = ContentType.ODT;
	        	tipoRelatorio = Type.ODT;
	        	extensao = ".odt";
	        }
	        
	        if(parametro.contains("aluno")){
	        	buffer = alunoReport.export(alunoBC.findAll(), param, tipoRelatorio);
	        }
	        else{
	        	if(parametro.contains("turma")){
	        		buffer = turmaReport.export(turmaBC.findAll(), param, tipoRelatorio);
	        	}
	        	else{
	        		if(parametro.contains("professor")){
	        			buffer = professorReport.export(professorBC.findAll(), param, tipoRelatorio);
	        		}
	        	}
	        }
	        
	        this.renderer.render(buffer, tipoRelatorioExport, "relatorio" + extensao);

	    } 
	    catch (Exception e) {
	        Faces.addMessage(e);
	    }
	}
	
}