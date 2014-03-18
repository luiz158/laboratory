package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.serpro.catalogo.entity.Perfil;

@ValidateRequest
@Path("perfis")
@Produces(APPLICATION_JSON)
public class PerfilService {

	@GET
	public Perfil[] listar() {
		return Perfil.values();
	}
}
