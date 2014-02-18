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
import br.gov.serpro.catalogo.entity.Grupo;
import br.gov.serpro.catalogo.persistence.GrupoDAO;

@ValidateRequest
@Path("/api/grupo")
@Produces(APPLICATION_JSON)
public class GrupoService {

	@Inject
	private GrupoDAO grupoDAO;
	
	@POST
	@Transactional
	public Long salvar(@Valid Grupo grupo) {
		return grupoDAO.insert(grupo).getId();
	}

	@PUT
	@Transactional
	public void alterar(@Valid Grupo grupo) {
		grupoDAO.update(grupo);
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		grupoDAO.delete(id);
	}

	@GET
	public List<Grupo> listar() {
		return grupoDAO.findAll();
	}
	
	@GET
	@Path("/{id}")
	public Grupo carregar(@NotNull @PathParam("id") Long id) {
		return grupoDAO.load(id);
	}
}
