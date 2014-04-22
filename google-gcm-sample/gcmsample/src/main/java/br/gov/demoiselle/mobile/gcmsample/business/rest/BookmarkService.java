package br.gov.demoiselle.mobile.gcmsample.business.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.gov.demoiselle.mobile.gcmsample.business.BookmarkBC;
import br.gov.demoiselle.mobile.gcmsample.domain.Bookmark;

@Path("bookmark")
@Produces(APPLICATION_JSON)
public class BookmarkService {
	
	@Inject
	private BookmarkBC bookmarkBC;
	
	@GET
	public List<Bookmark> listarBookmarks(){
		return bookmarkBC.findAll();
	}

}
