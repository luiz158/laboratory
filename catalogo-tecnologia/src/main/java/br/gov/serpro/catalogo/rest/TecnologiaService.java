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
import br.gov.serpro.catalogo.entity.Tecnologia;
import br.gov.serpro.catalogo.persistence.TecnologiaDAO;

@ValidateRequest
@Path("tecnologia")
@Produces(APPLICATION_JSON)
public class TecnologiaService {
	
	static final String ADMINISTRADOR = "ADMINISTRADOR";
	static final String CADASTRADOR = "CADASTRADOR";
	
	@Inject
	private TecnologiaDAO tecnologiaDAO;
	
	@POST
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public Long salvar(@Valid Tecnologia tecnologia) {
		return tecnologiaDAO.insert(tecnologia).getId();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void excluir(@NotNull @PathParam("id") Long id) {
		tecnologiaDAO.delete(id);
	}

	@GET
	public List<Tecnologia> listar() {
		return tecnologiaDAO.findAll();
	}
	
	@PUT
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void alterar(@Valid Tecnologia tecnologia) {
		tecnologiaDAO.update(tecnologia);
	}
	
	@GET
	@Path("{id}")
	public Tecnologia carregar(@NotNull @PathParam("id") Long id) {
		return tecnologiaDAO.load(id);
	}
}