/*
 * Demoiselle Sample
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 *
 * Demoiselle Sample is a set of open source Java EE project samples using
 * the Demoiselle Framework intented to exemplify its usage.
 *
 * Demoiselle Framework is released under the terms of the LGPL license 3
 * http://www.gnu.org/licenses/lgpl.html  LGPL License 3
 *
 * This file is part of Demoiselle Sample.
 *
 * Demoiselle Sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License 3 as published by
 * the Free Software Foundation.
 *
 * Demoiselle Sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Demoiselle Sample.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.gov.serpro.auction5.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author CETEC/CTJEE
 * @see IPojo
 */
@Entity
@Table(name = "categories")
@SequenceGenerator(name = "CategorySequence", sequenceName = "categories_seq")
public class Category implements Serializable {

	private static final long serialVersionUID = 333L;

	@Id
	@GeneratedValue(generator = "CategorySequence", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(length = 30, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "parent_id", nullable = true)
	private Category parentCategory;

	@Column(nullable = false)
	private boolean active;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<Item> items;

	@OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
	private Set<Category> childrenCategories;
	
	public Category() {
		super();
	}

	public Category(int id, String name, Category parent) {
		this.id = (Long.valueOf(id));
		this.name = name;
		this.parentCategory = parent;
		this.active = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Set<Category> getChildrenCategories() {
		return childrenCategories;
	}

	public void setChildrenCategories(Set<Category> childrenCategories) {
		this.childrenCategories = childrenCategories;
	}

	public String toLog() {
		return toString();
	}
	
}
