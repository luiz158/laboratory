package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Anexo;
import br.gov.serpro.catalogo.persistence.AnaliseDAO;
import br.gov.serpro.catalogo.persistence.AnexoDAO;

@ValidateRequest
@Path("/api/anexo")
@Produces(APPLICATION_JSON)
public class AnexoService {

	@Inject
	private AnexoDAO anexoDAO;

	@Inject
	private AnaliseDAO analiseDAO;

	@POST
	@Transactional
	@Consumes("multipart/form-data")
	public Response salvarAnexo(MultipartFormDataInput input) {

		Anexo anexo = new Anexo();

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputPartsAnexo = uploadForm.get("file");
		List<InputPart> inputPartsAnalise = uploadForm.get("anexo");

		try {
			String jsonString = inputPartsAnalise.get(0).getBodyAsString();
			anexo = new ObjectMapper().readValue(jsonString, Anexo.class);
			System.out.println(anexo);
			// anexo.setAnalise(ob);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (InputPart inputPart : inputPartsAnexo) {
			try {
				InputStream inputStream = inputPart.getBody(InputStream.class, null);
				byte[] bytes = IOUtils.toByteArray(inputStream);
				anexo.setArquivo(bytes);
				anexo.setAnalise(analiseDAO.load(anexo.getAnalise().getId()));
				anexoDAO.insert(anexo);
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).entity("Não foi possível salvar o anexo").build();
			}
		}
		return Response.status(200).entity("Anexo armazenado").build();
	}

	@GET
	@Path("/{id}/{fase}")
	public List<Anexo> listar(@PathParam("id") Long id, @PathParam("fase") Integer fase) {
		// TODO Filtrar pela demanda e fase...
		System.out.println("ID: " + id);
		System.out.println("Fase: " + fase);
		return anexoDAO.findAll();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		anexoDAO.delete(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/force-download")
	public Response download(@PathParam("id") Long id) {
		Anexo  anexo = anexoDAO.load(id);

		return Response.ok(anexo.getArquivo(), MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition", "attachment; filename = '"+anexo.getNomeArquivo()+"'").build();
	}
}
