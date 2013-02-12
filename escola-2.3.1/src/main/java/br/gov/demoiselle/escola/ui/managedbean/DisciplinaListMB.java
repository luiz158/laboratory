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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.business.DisciplinaBC;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("/private/pages/disciplina_listar.xhtml")
@NextView("/private/pages/disciplina_editar.xhtml")
public class DisciplinaListMB extends AbstractListPageBean<Disciplina, Long> {
	
	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Disciplina> lazyDataModel = new LazyDataModel<Disciplina>() {
		 private static final long serialVersionUID = 1L;

	        @Override
	        public List<Disciplina> load(int first, int pageSize, String sortField,
	    			SortOrder sortOrder, Map<String, String> filters) {
	        	
	            Pagination pagination = getPagination();
	            pagination.setFirstResult(first);
	            pagination.setPageSize(pageSize);

	            List<Disciplina> list = handleResultList();

	            this.setRowCount(pagination.getTotalResults());
	            this.setPageSize(pageSize);
	            return list;
	        }

	};
	
	@Inject
	private DisciplinaBC disciplinaBC;
	
	@Override
	protected List<Disciplina> handleResultList() {
		return this.disciplinaBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				disciplinaBC.remover(disciplinaBC.load(id));
				iter.remove();
			}
		}
		return getPreviousView();
	}
	
	public List<Disciplina> getListaDisciplinas(){
		return handleResultList();
	}
	
	public LazyDataModel<Disciplina> getLazyModel() {
		return lazyDataModel;
    }
	
}