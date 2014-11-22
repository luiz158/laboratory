package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.bussiness.FaseBC;
import br.gov.serpro.catalogo.entity.Sustentacao;

@ValidateRequest
@Path("sustentacao")
@Produces(APPLICATION_JSON)
public class SustentacaoService {
	
	@Inject
	private FaseBC faseBC;
	
	@POST
	@Transactional
	@LoggedIn
	public Long salvar(@Valid Sustentacao f) {
		return faseBC.salvar(f).getId();
	}
	
	@PUT
	@Transactional
	@Path("finalizar")
	public void finalizar(@Valid Sustentacao f) {
		faseBC.finalizarFase(f);
	}
	
	@PUT
	@Transactional
	@LoggedIn
	public void alterar(@Valid Sustentacao f) {
		faseBC.salvar(f);
	}
	
	@GET
	@Path("{id}")
	public Sustentacao carregar(@NotNull @PathParam("id") Long id) {
		return (Sustentacao)faseBC.load(id);
	}
	
}
