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
package br.gov.serpro.auction5.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.auction5.domain.Category;
import br.gov.serpro.auction5.exception.ApplicationRuntimeException;
import br.gov.serpro.auction5.persistence.CategoryDAO;

/**
 * @author CETEC/CTJEE
 * @see IBusinessController
 */
public class AdminBC implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private CategoryDAO categoryDAO;

	public List<Category> listAllCategories() {
		try {
			return categoryDAO.findAll();
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("LIST_CATEGORY_NOK"));
		}
	}

	public void saveCategory(Category category) {
		try {

			if (category.getParentCategory() != null
					&& (category.getParentCategory().getId() == null || category.getParentCategory().getId() == 0)) {
				category.setParentCategory(null);
			}

			if (category.getId() != null) {
				categoryDAO.update(category);
			} else {
				categoryDAO.insert(category);
			}
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("ADM_CATEGORY_SAVE_NOK"));
		}
	}

	public void removeCategory(Category category) {
		try {
			// firstly, retrieve a category instance
			category = categoryDAO.load(category.getId());

			// check whether it has children categories
			if (!category.getChildrenCategories().isEmpty()) {
				throw new ApplicationRuntimeException(bundle.getString("ADM_CATEGORY_DELETE_NOK_CHILDREN"));
			}

			// check whether it has dependent items
			if (!category.getItems().isEmpty()) {
				throw new ApplicationRuntimeException(bundle.getString("ADM_CATEGORY_DELETE_NOK_ITEMS"));
			}

			// finish him!
			categoryDAO.delete(category.getId());

		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("ADM_CATEGORY_DELETE_NOK"));
		}
	}

}
