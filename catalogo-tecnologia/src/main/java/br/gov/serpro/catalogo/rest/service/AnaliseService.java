package br.gov.serpro.catalogo.rest.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.persistence.AnaliseDAO;

@ValidateRequest
@Path("/api/analise")
@Produces(APPLICATION_JSON)
public class AnaliseService {

	@Inject
	private AnaliseDAO analiseDAO;

	@POST
	@Transactional
	public Long salvar(@NotNull @Valid Analise analise) {
		analiseDAO.insert(analise);
		return analise.getId();
	}
}
