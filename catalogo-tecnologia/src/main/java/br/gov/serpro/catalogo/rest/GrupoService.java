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
import br.gov.serpro.catalogo.bussiness.GrupoBC;
import br.gov.serpro.catalogo.entity.Grupo;

@ValidateRequest
@Path("grupo")
@Produces(APPLICATION_JSON)
public class GrupoService {
	
	static final String ADMINISTRADOR = "ADMINISTRADOR";

	@Inject
	private GrupoBC grupoBC;
	
	@POST
	@Transactional
	@RequiredRole(ADMINISTRADOR)
	public Long salvar(@Valid Grupo grupo) {
		return grupoBC.insert(grupo).getId();
	}

	@PUT
	@Transactional
	@RequiredRole(ADMINISTRADOR)
	public void alterar(@Valid Grupo grupo) {
		grupoBC.update(grupo);
	}

	@DELETE
	@Path("{id}")
	@Transactional
	@RequiredRole(ADMINISTRADOR)
	public void excluir(@NotNull @PathParam("id") Long id) {
		grupoBC.delete(id);
	}

	@GET
	public List<Grupo> listar() {
		return grupoBC.findAll();
	}
	
	@GET
	@Path("{id}")
	public Grupo carregar(@NotNull @PathParam("id") Long id) {
		return grupoBC.load(id);
	}
}
