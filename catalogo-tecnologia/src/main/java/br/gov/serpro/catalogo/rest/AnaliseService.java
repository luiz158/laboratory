package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
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
	public Long salvar(@Valid Analise analise) {
		return analiseDAO.insert(analise).getId();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		analiseDAO.delete(id);
	}

	@GET
	public List<Analise> listar() {
		return analiseDAO.findAll();
	}
	
	@PUT
	@Transactional
	public void alterar(@Valid Analise analise) {
		analiseDAO.update(analise);
	}
	
	@GET
	@Path("/{id}")
	public Analise carregar(@NotNull @PathParam("id") Long id) {
		return analiseDAO.load(id);
	}
	
	@POST
	//@Transactional
	@Path("/anexar")
	@Consumes("multipart/form-data")
	public Response salvarAnexo(MultipartFormDataInput input) {
		String fileName = "";
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("anexo");
 
		System.out.println("O upload chegou...");
		
		for (InputPart inputPart : inputParts) {
 
		 try {
 
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);
 
			//convert the uploaded file to inputstream
			InputStream inputStream = inputPart.getBody(InputStream.class,null);
 
			byte [] bytes = IOUtils.toByteArray(inputStream);
 
 
			System.out.println("Done: "+fileName);
 
		  } catch (IOException e) {
			e.printStackTrace();
		  }
 
		}
 
		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();
 
	}
	
	
	/**
	 * header sample
	 * {
	 * 	Content-Type=[image/png], 
	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	//get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) { 
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";"); 
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) { 
				String[] name = filename.split("="); 
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	
}
