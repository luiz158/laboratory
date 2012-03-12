/*
 * Demoiselle Framework
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 *
 * Demoiselle Framework is an open source Java EE library designed to accelerate
 * the development of transactional database Web applications.
 *
 * Demoiselle Framework is released under the terms of the LGPL license 3
 * http://www.gnu.org/licenses/lgpl.html  LGPL License 3
 *
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License 3 as published by
 * the Free Software Foundation.
 *
 * Demoiselle Framework is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Demoiselle Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.gov.serpro.auction5.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.auction5.business.AdminBC;
import br.gov.serpro.auction5.constant.AliasNavigationRule;
import br.gov.serpro.auction5.domain.Category;
import br.gov.serpro.auction5.exception.ApplicationRuntimeException;

/**
 * @author CETEC/CTJEE
 * @see AbstractManagedBean
 */
@SessionScoped
@ViewController
public class CategoryMB implements Serializable, AliasNavigationRule {

	private static final long serialVersionUID = 1L;

	@Inject
	private AdminBC adminBC;

	private Category category;
	private List<Category> listCategories;
	
	@Inject
	private ResourceBundle bundle;
	
	@Inject
	private MessageContext messageContext;

	@PostConstruct
	public void init() {
		category = new Category();
		category.setParentCategory(new Category());
		updateCategoriesList();
	}

	public Category getCategory() {
		if(category.getParentCategory()==null){
			category.setParentCategory(new Category());
		}
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	private void updateCategoriesList() {
		try{
			this.listCategories = adminBC.listAllCategories();
		} catch (ApplicationRuntimeException e) {
			throw e;			
		}
	}
	
	public List<Category> getListCategories() {
		if (listCategories == null)
			updateCategoriesList();
		return listCategories;
	}

	public void setListCategories(List<Category> listCategories) {
		this.listCategories = listCategories;
	}

	public String list() {
		return ALIAS_LIST_CATEGORY;
	}

	public String edit() {
		return ALIAS_EDIT_CATEGORY;
	}

	public String delete() {
		messageContext.add(bundle.getString("ADM_CATEGORY_CONFIRM_DELETE"));
		return ALIAS_VIEW_CATEGORY;
	}

	public String cancel() {
		return ALIAS_LIST_CATEGORY;
	}

	public String newCategory() {
		category = new Category();
		category.setParentCategory(new Category());
		category.setActive(true);
		return ALIAS_EDIT_CATEGORY;
	}

	public String save() {
		try {
			adminBC.saveCategory(category);
			messageContext.add(bundle.getString("ADM_CATEGORY_SAVE_OK"));
			updateCategoriesList();			
		} catch (ApplicationRuntimeException e) {
			messageContext.add(e.getMessage());
			return ALIAS_STAY;
		}
		return list();
	}

	public String deleteConfirmed() {
		try {
			adminBC.removeCategory(category);
			messageContext.add(bundle.getString("ADM_CATEGORY_DELETE_OK"));
			updateCategoriesList();
		} catch (ApplicationRuntimeException e) {
			messageContext.add(e.getMessage());
			return ALIAS_STAY;
		}
		return list();
	}

}
