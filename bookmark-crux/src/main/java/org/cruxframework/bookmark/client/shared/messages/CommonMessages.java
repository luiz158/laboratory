package org.cruxframework.bookmark.client.shared.messages;

import org.cruxframework.crux.core.client.i18n.MessageName;

import com.google.gwt.i18n.client.Messages;

@MessageName("commonMessages")
public interface CommonMessages extends Messages
{
	@DefaultMessage("Bookmark")
	String title();

	@DefaultMessage("Bookmark")
	String menuBookmark();
	
	@DefaultMessage("New")
	String menuNew();
	
	@DefaultMessage("List")
	String menuList();
	
	@DefaultMessage("Link: ")
	String addLink();
	
	@DefaultMessage("Description: ")
	String addDescription();
	
	@DefaultMessage("Save")
	String addSave();
	
	@DefaultMessage("Description")
	String listDescription();
	
	@DefaultMessage("Link")
	String listLink();
	
	@DefaultMessage("Action")
	String listAction();
	
	@DefaultMessage("Id")
	String listId();
	
	@DefaultMessage("Edit")
	String listEdit();
	
	@DefaultMessage("New")
	String listNew();
	
	@DefaultMessage("Delete")
	String listDelete();
	
	@DefaultMessage("Quit")
	String menuQuit();
	
	@DefaultMessage("Fill all fields.")
	String fillMessage();
	
	@DefaultMessage("Welcome to the example application Bookmark. This is your starting point, feel free to change this application.")
	String home();
	
}

