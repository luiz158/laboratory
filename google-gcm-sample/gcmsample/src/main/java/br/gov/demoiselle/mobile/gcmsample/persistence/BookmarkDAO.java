package br.gov.demoiselle.mobile.gcmsample.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.gov.demoiselle.mobile.gcmsample.domain.Bookmark;

@PersistenceController
public class BookmarkDAO extends JPACrud<Bookmark, Long> {
	
	private static final long serialVersionUID = 1L;
	
}
