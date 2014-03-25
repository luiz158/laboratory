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
import br.gov.serpro.catalogo.entity.OrigemDemanda;
import br.gov.serpro.catalogo.persistence.OrigemDemandaDAO;

@ValidateRequest
@Path("origemDemanda")
@Produces(APPLICATION_JSON)
public class OrigemDemandaService {
	
	static final String ADMINISTRADOR = "ADMINISTRADOR";
	static final String CADASTRADOR = "CADASTRADOR";
	
	@Inject
	private OrigemDemandaDAO origemDemandaDAO;
	
	@POST
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public Long salvar(@Valid OrigemDemanda origemDemanda) {
		return origemDemandaDAO.insert(origemDemanda).getId();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void excluir(@NotNull @PathParam("id") Long id) {
		origemDemandaDAO.delete(id);
	}

	@GET
	public List<OrigemDemanda> listar() {
		return origemDemandaDAO.findAll();
	}
	
	@PUT
	@Transactional
	@RequiredRole({ADMINISTRADOR, CADASTRADOR})
	public void alterar(@Valid OrigemDemanda origemDemanda) {
		origemDemandaDAO.update(origemDemanda);
	}
	
	@GET
	@Path("{id}")
	public OrigemDemanda carregar(@NotNull @PathParam("id") Long id) {
		return origemDemandaDAO.load(id);
	}
}