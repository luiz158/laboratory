package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.persistence.AnexoDAO;

@ValidateRequest
@Path("/api/anexo")
@Produces(APPLICATION_JSON)
public class AnexoService {

	@Inject
	private AnexoDAO anexoDAO;
	
	@POST
	@Transactional
	@Consumes("multipart/form-data")
	public Response salvarAnexo(MultipartFormDataInput input) {
		String fileName = "";
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputPartsAnexo = uploadForm.get("anexo");
		List<InputPart> inputPartsAnalise = uploadForm.get("analise");		
		
		System.out.println("O upload chegou...");
		
		try {
			
			System.out.println(inputPartsAnalise.get(0).getBodyAsString());
			Analise analise = inputPartsAnalise.get(0).getBody(Analise.class,null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for (InputPart inputPart : inputPartsAnexo) { 
		 try { 
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);
 
			//convert the uploaded file to inputstream
			InputStream inputStream = inputPart.getBody(InputStream.class,null);
 
			byte [] bytes = IOUtils.toByteArray(inputStream);
			escreverArquivo(bytes);
						 
			System.out.println("Done: "+fileName);
 
		  } catch (IOException e) {
			e.printStackTrace();
		  } 
		}
 
		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();
 
	}
	
	
	private void escreverArquivo(byte[] arquivo ){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File("/home/82546010549/Desktop/arquivo.teste"));    
			fileOutputStream.write(arquivo);    
			fileOutputStream.flush();             // <<--- ATENÇÃO AQUI   
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

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
