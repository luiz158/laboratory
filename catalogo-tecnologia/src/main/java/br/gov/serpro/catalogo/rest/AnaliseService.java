package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import static br.gov.serpro.catalogo.security.Roles.*;

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
import br.gov.serpro.catalogo.bussiness.FaseBC;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.persistence.AnaliseDAO;
import br.gov.serpro.catalogo.security.Roles;

@ValidateRequest
@Path("analise")
@Produces(APPLICATION_JSON)
public class AnaliseService {


	
	@Inject
	private FaseBC faseBC;
	
	@Inject
	private AnaliseDAO analiseDAO;

	@POST
	@Transactional
	@RequiredRole({ADMINISTRADOR, ANALISE})
	public Long salvar(@Valid Analise analise) {
		return faseBC.salvar(analise).getId();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	@RequiredRole({ADMINISTRADOR, ANALISE})
	public void excluir(@NotNull @PathParam("id") Long id) {
		analiseDAO.delete(id);
	}

	@GET
	public List<Analise> listar() {
		return analiseDAO.findAll();
	}
	
	@PUT
	@Transactional
	@RequiredRole({ADMINISTRADOR, ANALISE})
	public Long alterar(@Valid Analise analise) {
		return faseBC.salvar(analise).getId();
	}
	
	@PUT
	@Transactional
	@Path("finalizar")
	@RequiredRole({ADMINISTRADOR, ANALISE})
	public void finalizar(@Valid Analise analise) {
		faseBC.finalizarFase(analise);
	}
	
	
	@GET
	@Path("{id}")
	public Analise carregar(@NotNull @PathParam("id") Long id) {
		return analiseDAO.load(id);
	}
	
}
