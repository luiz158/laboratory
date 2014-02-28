package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@GET
	@Path("/fluxo/{id}")
	public List<Fase> obterCadeiaDasFases(@PathParam("id") Long id){
		List<Fase> lista = new ArrayList<Fase>();
		
		Fase faseInicial = faseDAO.load(id);
		lista.add(faseInicial);
		
		Fase faseAnterior = (faseInicial.getFaseAnterior()!=null)? faseDAO.load(faseInicial.getFaseAnterior().getId()): null;
		while(faseAnterior!=null){			
			lista.add(0, faseAnterior);
			faseAnterior = (faseAnterior.getFaseAnterior()!=null)? faseDAO.load(faseAnterior.getFaseAnterior().getId()): null;
		}
		
		Fase fasePosterior = faseDAO.obterFasePosterior(faseInicial.getId());
		while(fasePosterior!=null){			
			lista.add(fasePosterior);
			fasePosterior  = faseDAO.obterFasePosterior(fasePosterior.getId());
		}
		
		return lista;
		
	}
	
	
}
