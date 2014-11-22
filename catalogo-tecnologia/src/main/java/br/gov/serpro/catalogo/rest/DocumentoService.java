package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.gov.serpro.catalogo.entity.Documento;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.persistence.DocumentoDAO;

//@ValidateRequest
@Path("documento")
@Produces(APPLICATION_JSON)
public class DocumentoService {

	@Inject
	private DocumentoDAO documentoDAO;

	@GET
	@Path("fase/{id}")
	public List<Documento> listar(@PathParam("id") Long id) {	
		List<Documento> list = documentoDAO.findAll();
		for (Documento documento : list) {
			Fase fase = new Fase();
			fase.setId(documento.getFase().getId());
			documento.setFase(fase);
		}
		return list;
	}	

	@POST
	@Path("fase/{id}/add")
	public Documento adicionar(Documento doc, @PathParam("id") Long id) {
		if(doc.getFase()==null) doc.setFase(new Fase());
		doc.getFase().setId(id);
		return documentoDAO.insert(doc);
	}
	
	@DELETE
	@Path("{id}")
	public void excluir(@PathParam("id") Long id) {
		documentoDAO.delete(id);
	}

}
