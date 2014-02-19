package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.persistence.AnaliseDAO;

@Path("/api/fase")
@Produces(APPLICATION_JSON)
public class FaseService {
	
	@Inject
	private AnaliseDAO analiseDAO;
	

	@POST
	public List<FaseDTO> pesquisar(FaseDTO fase) {
		List<FaseDTO> lista = new ArrayList<FaseDTO>();
		List<Analise> analises = analiseDAO.pesquisarAnalise(fase);
		//List<Prospeccao> prospeccoes = prospeccaoDAO.pesquisar(fase);
		
		for (Analise a : analises) {
			FaseDTO f = new FaseDTO();
			f.setId(a.getId());
			f.setFase(1);
			f.setDataFinalizacao(a.getDataFinalizacao());
			f.setDataRegistro(a.getDataAnalise());
			f.setGestor(a.getGestorAnalise());
			f.setGestorArea(a.getAreaGestorAnalise());
			f.setObjetivo(a.getDetalhamento());
			f.setOrigemReferencia(a.getOrigemReferencia());
			f.setCodigoReferencia(a.getCodigoReferencia());
			lista.add(f);
		}
		
		return lista;
	}
	
	
}
