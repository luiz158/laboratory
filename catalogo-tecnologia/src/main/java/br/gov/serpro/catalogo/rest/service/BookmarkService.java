package br.gov.serpro.catalogo.rest.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.serpro.catalogo.business.BookmarkBC;
import br.gov.serpro.catalogo.entity.Bookmark;

@ValidateRequest
@Path("/api/bookmark")
@Produces(APPLICATION_JSON)
public class BookmarkService {

	@Inject
	private BookmarkBC bc;

	@GET
	public List<Bookmark> signUp() {
		return bc.findAll();
	}
}
