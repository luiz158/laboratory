package com.jpa.emscopesample.business;

import java.util.ArrayList;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.jpa.emscopesample.domain.Bookmark;
import com.jpa.emscopesample.domain.Category;
import com.jpa.emscopesample.persistence.BookmarkDAO;

@BusinessController
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			Bookmark bookmark;
			ArrayList<Category> categories;
			
			bookmark = new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
			categories = new ArrayList<Category>();
			categories.add( new Category("Programação") );
			bookmark.setCategories(categories);
			insert(bookmark);
			
			bookmark = new Bookmark("Demoiselle SourceForge", "http://sf.net/projects/demoiselle");
			categories = new ArrayList<Category>();
			categories.add( new Category("Social") );
			bookmark.setCategories(categories);
			insert(bookmark);
			
			bookmark = new Bookmark("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br");
			categories = new ArrayList<Category>();
			categories.add( new Category("Contribuição") );
			categories.add( new Category("Programação") );
			bookmark.setCategories(categories);
			insert(bookmark);
		}
	}
	
}
