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

import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Fabricante;
import br.gov.serpro.catalogo.persistence.FabricanteDAO;

@ValidateRequest
@Path("fabricante")
@Produces(APPLICATION_JSON)
public class FabricanteService {
	
	static final String ADMINISTRADOR = "ADMINISTRADOR";
	static final String CADASTRADOR = "CADASTRADOR";

	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@POST
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public Long salvar(@Valid Fabricante fabricante) {
		return fabricanteDAO.insert(fabricante).getId();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void excluir(@NotNull @PathParam("id") Long id) {
		fabricanteDAO.delete(id);
	}

	@GET
	public List<Fabricante> listar() {
		return fabricanteDAO.findAll();
	}
	
	@PUT
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void alterar(@Valid Fabricante fabricante) {
		fabricanteDAO.update(fabricante);
	}
	
	@GET
	@Path("{id}")
	public Fabricante carregar(@NotNull @PathParam("id") Long id) {
		return fabricanteDAO.load(id);
	}
}