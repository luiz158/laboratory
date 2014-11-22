package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.Observacao;
import br.gov.serpro.catalogo.persistence.ObservacaoDAO;

@ValidateRequest
@Path("observacao")
@Produces(APPLICATION_JSON)
public class ObservacaoService {

	@Inject
	private ObservacaoDAO obsDAO;
	

	@GET
	@Path("fase/{id}")
	public List<Observacao> listar(@PathParam("id") Long id) {	
		Fase fase = new Fase();
		fase.setId(id);				
		return obsDAO.observacoes(fase);
	}	

	@POST
	@Path("fase/{id}/add")
	public Observacao adicionar(Observacao observacao, @PathParam("id") Long id) {
		if(observacao.getFase()==null) observacao.setFase(new Fase());
		observacao.getFase().setId(id);
		observacao.setData(new Date());
		return obsDAO.insert(observacao);
	}
	
	@PUT
	public Observacao atualizar(Observacao observacao) {
		observacao.setData(new Date());
		return obsDAO.update(observacao);
	}
	
	@DELETE
	@Path("{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		obsDAO.delete(id);
	}

}
