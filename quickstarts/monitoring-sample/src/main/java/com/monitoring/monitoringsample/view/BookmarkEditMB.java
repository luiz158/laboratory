package com.monitoring.monitoringsample.view;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.monitoring.monitoringsample.business.BookmarkBC;
import com.monitoring.monitoringsample.domain.Bookmark;
import com.monitoring.monitoringsample.monitor.BookmarkMonitor;

@ViewController
@PreviousView("/bookmark_list.xhtml")
public class BookmarkEditMB extends AbstractEditPageBean<Bookmark, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private BookmarkMonitor bookmarkMonitor;
	
	@Inject
	private MessageContext messageContext;

	@Inject
	private BookmarkBC bookmarkBC;
	
	@Override
	@Transactional
	public String delete() {
		this.bookmarkBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		if (bookmarkMonitor.getInsertBookmarkEnabled()){
			this.bookmarkBC.insert(getBean());
			return getPreviousView();
		}
		else{
			messageContext.add("A criação de bookmarks está temporariamente suspensa devido a manutenção interna");
			return null;
		}
	}

	@Override
	@Transactional
	public String update() {
		this.bookmarkBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected Bookmark handleLoad(Long id) {
		return this.bookmarkBC.load(id);
	}
}
