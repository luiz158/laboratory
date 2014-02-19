package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.persistence.FaseDAO;

@Path("/api/fase")
@Produces(APPLICATION_JSON)
public class FaseService {
	
	@Inject
	private FaseDAO faseDAO;
	

	@POST
	public List<Fase> pesquisar(FaseDTO fase) {
		return faseDAO.pesquisar(fase);
	}
	
	
}
