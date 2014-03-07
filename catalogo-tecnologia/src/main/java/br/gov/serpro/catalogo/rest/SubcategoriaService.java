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
import br.gov.serpro.catalogo.entity.Subcategoria;
import br.gov.serpro.catalogo.persistence.SubcategoriaDAO;

@ValidateRequest
@Path("/api/subcategoria")
@Produces(APPLICATION_JSON)
public class SubcategoriaService {
	
	@Inject
	private SubcategoriaDAO subcategoriaDAO;
	
	@POST
	@Transactional
	public Long salvar(@Valid Subcategoria subcategoria) {
		return subcategoriaDAO.insert(subcategoria).getId();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		subcategoriaDAO.delete(id);
	}

	@GET
	public List<Subcategoria> listar() {
		return subcategoriaDAO.findAll();
	}
	
	@PUT
	@Transactional
	public void alterar(@Valid Subcategoria subcategoria) {
		subcategoriaDAO.update(subcategoria);
	}
	
	@GET
	@Path("/{id}")
	public Subcategoria carregar(@NotNull @PathParam("id") Long id) {
		return subcategoriaDAO.load(id);
	}
	
	@GET
	@Path("/listar/{temaId}")
	public List<Subcategoria> listarSubcategoriaByTema(@NotNull @PathParam("temaId") Long temaId) {
		String jpql = "SELECT s FROM Subcategoria s WHERE s.tema.id ="+temaId;
		return subcategoriaDAO.findByJPQL(jpql);
	}
}