package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Declinio;
import br.gov.serpro.catalogo.persistence.DeclinioDAO;

@ValidateRequest
@Path("declinio")
@Produces(APPLICATION_JSON)
public class DeclinioService {

	@Inject
	private DeclinioDAO declinioDAO;
	
	@POST
	@Transactional
	public Long salvar(@Valid Declinio fase) {
		return declinioDAO.insert(fase).getId();
	}
	
	@PUT
	@Transactional
	public void alterar(@Valid Declinio fase) {
		declinioDAO.update(fase);
	}
	
	@GET
	@Path("{id}")
	public Declinio carregar(@NotNull @PathParam("id") Long id) {
		return declinioDAO.load(id);
	}
	
}
