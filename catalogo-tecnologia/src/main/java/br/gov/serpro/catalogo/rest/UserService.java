package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.persistence.UserDAO;

@ValidateRequest
@Path("/api/user")
@Produces(APPLICATION_JSON)
public class UserService {

	@Inject
	private UserDAO userDAO;
	
	@GET
	public List<User> listar() {
		return userDAO.findAll();
	}
	
}
