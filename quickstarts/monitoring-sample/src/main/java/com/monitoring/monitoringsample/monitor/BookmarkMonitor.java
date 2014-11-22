package com.monitoring.monitoringsample.monitor;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.ManagedOperation;
import br.gov.frameworkdemoiselle.annotation.ManagedProperty;
import br.gov.frameworkdemoiselle.stereotype.ManagementController;

import com.monitoring.monitoringsample.business.BookmarkBC;

@ManagementController
public class BookmarkMonitor{
	
	@ManagedProperty(description="Determina se é possival incluir novos bookmarks")
	private boolean insertBookmarkEnabled = true;
	
	@Inject
	private BookmarkBC bookmarkBC;
	
	@ManagedOperation
	public int countRegisteredBookmarks(){
		return bookmarkBC.findAll().size();
	}

	public boolean getInsertBookmarkEnabled() {
		return insertBookmarkEnabled;
	}

	public void setInsertBookmarkEnabled(boolean insertBookmarkEnabled) {
		this.insertBookmarkEnabled = insertBookmarkEnabled;
	}
	
}
