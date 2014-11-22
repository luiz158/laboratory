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
import br.gov.serpro.catalogo.entity.PlataformaTecnologica;
import br.gov.serpro.catalogo.persistence.PlataformaTecnologicaDAO;

@ValidateRequest
@Path("plataformaTecnologica")
@Produces(APPLICATION_JSON)
public class PlataformaTecnologicaService {

	static final String ADMINISTRADOR = "ADMINISTRADOR";
	static final String CADASTRADOR = "CADASTRADOR";
	
	@Inject
	private PlataformaTecnologicaDAO plataformaTecnologicaDAO;
	
	@POST
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public Long salvar(@Valid PlataformaTecnologica plataformaTecnologica) {
		return plataformaTecnologicaDAO.insert(plataformaTecnologica).getId();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void excluir(@NotNull @PathParam("id") Long id) {
		plataformaTecnologicaDAO.delete(id);
	}

	@GET
	public List<PlataformaTecnologica> listar() {
		return plataformaTecnologicaDAO.findAll();
	}
	
	@PUT
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void alterar(@Valid PlataformaTecnologica plataformaTecnologica) {
		plataformaTecnologicaDAO.update(plataformaTecnologica);
	}
	
	@GET
	@Path("{id}")
	public PlataformaTecnologica carregar(@NotNull @PathParam("id") Long id) {
		return plataformaTecnologicaDAO.load(id);
	}
}