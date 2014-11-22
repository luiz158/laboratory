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
import br.gov.serpro.catalogo.entity.Fornecedor;
import br.gov.serpro.catalogo.persistence.FornecedorDAO;

@ValidateRequest
@Path("fornecedor")
@Produces(APPLICATION_JSON)
public class FornecedorService {
	
	static final String ADMINISTRADOR = "ADMINISTRADOR";
	static final String CADASTRADOR = "CADASTRADOR";
	
	@Inject
	private FornecedorDAO fornecedorDAO;
	
	@POST
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public Long salvar(@Valid Fornecedor fornecedor) {
		return fornecedorDAO.insert(fornecedor).getId();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void excluir(@NotNull @PathParam("id") Long id) {
		fornecedorDAO.delete(id);
	}

	@GET
	public List<Fornecedor> listar() {
		return fornecedorDAO.findAll();
	}
	
	@PUT
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void alterar(@Valid Fornecedor fornecedor) {
		fornecedorDAO.update(fornecedor);
	}
	
	@GET
	@Path("{id}")
	public Fornecedor carregar(@NotNull @PathParam("id") Long id) {
		return fornecedorDAO.load(id);
	}
}