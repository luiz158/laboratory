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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Faces;
import br.gov.serpro.auction5.business.AuctionBC;
import br.gov.serpro.auction5.constant.AliasNavigationRule;
import br.gov.serpro.auction5.domain.Auction;
import br.gov.serpro.auction5.domain.Category;
import br.gov.serpro.auction5.exception.ApplicationRuntimeException;

/**
 * @author CETEC/CTJEE
 * @see AbstractManagedBean
 */
@ViewController
public class AuctionMB implements Serializable, AliasNavigationRule {

	private static final long serialVersionUID = 1L;

	@Inject
	private AuctionBC auctionBC;

	private Auction auction;

	private Category category;

	private List<Category> listCategories;

	private List<Auction> listAuctions;

	@PostConstruct
	public void init() {
		try {
			reloadCategories();
			if (!listCategories.isEmpty()) {
				category = (Category) listCategories.get(0);
				listAuctions = auctionBC.listOpenAuctionsByCategory(category);
			}
		} catch (ApplicationRuntimeException e) {
			throw e;
		}
	}

	private void reloadCategories() {
		listCategories = auctionBC.listAvailableCategories();
	}

	public List<SelectItem> getListCategories() {

		if (listCategories == null) {
			reloadCategories();
		}

		List<SelectItem> items = new ArrayList<SelectItem>(listCategories.size());
		for (Category cat : listCategories) {
			items.add(new SelectItem(cat.getId(), cat.getName()));
		}

		return items;
	}

	public List<Auction> getListAuctions() {
		return this.listAuctions;
	}

	public void setListAuctions(List<Auction> listAuctions) {
		this.listAuctions = listAuctions;
	}

	public void actionLoadListItemsByCategory(ValueChangeEvent event) {
		this.category = new Category();
		this.category.setId(Long.valueOf(event.getNewValue().toString()));
		this.updateData();
	}
	
	public String listOpenAuctions() {
		updateData();
		return ALIAS_AUCTION;
	}

	public void updateData() {
		if (this.category != null) {
			try {
				listAuctions = auctionBC.listOpenAuctionsByCategory(this.category);
				for (Auction auction : listAuctions) {
					// avoid Hibernate lazy exception
					auction.getBids().size();
				}
			} catch (ApplicationRuntimeException e) {
				throw e;
			}
		} else {
			listAuctions = null;
		}
	}

	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// TODO: ???
	public List<Auction> getList() {
		return null;
	}

	public Integer getRows() {
		return 10;
	}

}