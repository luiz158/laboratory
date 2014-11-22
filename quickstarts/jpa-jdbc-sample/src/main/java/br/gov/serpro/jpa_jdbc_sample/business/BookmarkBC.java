package br.gov.serpro.jpa_jdbc_sample.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.jpa_jdbc_sample.domain.Bookmark;
import br.gov.serpro.jpa_jdbc_sample.persistence.BookmarkDAO;
import br.gov.serpro.jpa_jdbc_sample.persistence.LogDAO;

@BusinessController
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LogBC logBC;

	@Transactional
	public Bookmark insert(final Bookmark bookmark) {
		Bookmark bookmarkResult = getDelegate().insert(bookmark);
		logBC.insert(bookmarkResult, "insert");
		return bookmarkResult;
	}
	
	@Transactional
	public Bookmark update(final Bookmark bookmark) {
		Bookmark bookmarkResult = getDelegate().update(bookmark);
		logBC.insert(bookmarkResult, "update");
		return bookmarkResult;
	}
}
