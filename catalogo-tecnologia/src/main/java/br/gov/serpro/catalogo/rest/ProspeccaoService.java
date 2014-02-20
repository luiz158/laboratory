package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.persistence.ProspeccaoDAO;

@ValidateRequest
@Path("/api/prospeccao")
@Produces(APPLICATION_JSON)
public class ProspeccaoService {

	@Inject
	private ProspeccaoDAO prospeccaoDAO;

	@POST
	@Transactional
	public Long salvar(@Valid Prospeccao prospeccao) {
		return prospeccaoDAO.insert(prospeccao).getId();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		prospeccaoDAO.delete(id);
	}

	@GET
	public List<Prospeccao> listar() {
		return prospeccaoDAO.findAll();
	}
	
	@PUT
	@Transactional
	public void alterar(@Valid Prospeccao prospeccao) {
		prospeccaoDAO.update(prospeccao);
	}
	
	@GET
	@Path("/{id}")
	public Prospeccao carregar(@NotNull @PathParam("id") Long id) {
		return prospeccaoDAO.load(id);
	}
	
}
