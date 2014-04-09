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
import br.gov.serpro.catalogo.entity.Categoria;
import br.gov.serpro.catalogo.persistence.CategoriaDAO;

@ValidateRequest
@Path("categoria")
@Produces(APPLICATION_JSON)
public class CategoriaService {
	
	static final String ADMINISTRADOR = "ADMINISTRADOR";
	static final String CADASTRADOR = "CADASTRADOR";
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@POST
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public Long salvar(@Valid Categoria categoria) {
		return categoriaDAO.insert(categoria).getId();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void excluir(@NotNull @PathParam("id") Long id) {
		categoriaDAO.delete(id);
	}

	@GET
	public List<Categoria> listar() {
		return categoriaDAO.findAll();
	}
	
	@PUT
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void alterar(@Valid Categoria categoria) {
		categoriaDAO.update(categoria);
	}
	
	@GET
	@Path("{id}")
	public Categoria carregar(@NotNull @PathParam("id") Long id) {
		return categoriaDAO.load(id);
	}
	
	@GET
	@Path("listarCategoriaPorTecnologia/{tecnologiaId}")
	public List<Categoria> listarCategoriaPorTecnologia(@NotNull @PathParam("tecnologiaId") Long tecnologiaId) {
		return categoriaDAO.listarCategoraPorTecnologia(tecnologiaId);
	}
}