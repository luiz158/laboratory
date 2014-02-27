package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseProduto;
import br.gov.serpro.catalogo.persistence.FaseProdutoDAO;

@Path("/api/fase/produto")
@Produces(APPLICATION_JSON)
public class FaseProdutoService {
	
	@Inject
	private FaseProdutoDAO faseProdutoDAO;
	

	@GET @Path("/{fase}")
	public List<FaseProduto> produtosDaFase(@PathParam("fase") Long fase) {
		Fase f = new Fase();
		f.setId(fase);
		return faseProdutoDAO.produtosDaFase(f);
	}
	
	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		faseProdutoDAO.delete(id);
	}
	
	@POST
	@Transactional
	public Long salvar(FaseProduto p) {
		return faseProdutoDAO.insert(p).getId();
	}
	
}
