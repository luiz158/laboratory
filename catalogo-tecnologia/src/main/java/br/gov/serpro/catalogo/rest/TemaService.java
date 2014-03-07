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
import br.gov.serpro.catalogo.entity.Tema;
import br.gov.serpro.catalogo.persistence.TemaDAO;

@ValidateRequest
@Path("/api/tema")
@Produces(APPLICATION_JSON)
public class TemaService {
	
	@Inject
	private TemaDAO temaDAO;
	
	@POST
	@Transactional
	public Long salvar(@Valid Tema tema) {
		return temaDAO.insert(tema).getId();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		temaDAO.delete(id);
	}

	@GET
	public List<Tema> listar() {
		return temaDAO.findAll();
	}
	
	@PUT
	@Transactional
	public void alterar(@Valid Tema tema) {
		temaDAO.update(tema);
	}
	
	@GET
	@Path("/{id}")
	public Tema carregar(@NotNull @PathParam("id") Long id) {
		return temaDAO.load(id);
	}
	
	@GET
	@Path("/listar/{tecnologiaId}")
	public List<Tema> listarTemaByTecnologia(@NotNull @PathParam("tecnologiaId") Long tecnologiaId) {
		String jpql = "SELECT t FROM Tema t WHERE t.tecnologia.id ="+tecnologiaId;
		return temaDAO.findByJPQL(jpql);
	}
}