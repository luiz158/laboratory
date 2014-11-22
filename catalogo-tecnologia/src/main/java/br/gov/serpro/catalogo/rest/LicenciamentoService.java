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
import br.gov.serpro.catalogo.entity.Licenciamento;
import br.gov.serpro.catalogo.persistence.LicenciamentoDAO;

@ValidateRequest
@Path("licenciamento")
@Produces(APPLICATION_JSON)
public class LicenciamentoService {
	
	static final String ADMINISTRADOR = "ADMINISTRADOR";
	static final String CADASTRADOR = "CADASTRADOR";

	@Inject
	private LicenciamentoDAO licenciamentoDAO;
	
	@POST
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public Long salvar(@Valid Licenciamento licenciamento) {
		return licenciamentoDAO.insert(licenciamento).getId();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void excluir(@NotNull @PathParam("id") Long id) {
		licenciamentoDAO.delete(id);
	}

	@GET
	public List<Licenciamento> listar() {
		return licenciamentoDAO.findAll();
	}
	
	@PUT
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void alterar(@Valid Licenciamento licenciamento) {
		licenciamentoDAO.update(licenciamento);
	}
	
	@GET
	@Path("{id}")
	public Licenciamento carregar(@NotNull @PathParam("id") Long id) {
		return licenciamentoDAO.load(id);
	}
}