package br.gov.serpro.catalogo.rest.service;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import br.gov.frameworkdemoiselle.security.Credentials;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.Beans;

@Path("/auth")
public class AuthenticationService {

	@Inject
	private SecurityContext securityContext;

	@POST
	public void login(@FormParam("username") String username, @FormParam("password") String password) throws Exception {
		Credentials credentials = Beans.getReference(Credentials.class);
		credentials.setUsername(username);
		credentials.setPassword(password);

		securityContext.login();
	}
/*
	@DELETE
	@LoggedIn
	public void logout() {
		securityContext.logout();
	}

	@GET
	@LoggedIn
	@Produces(JSON_MEDIA_TYPE)
	public User getUser() {
		return (User) securityContext.getCurrentUser();
	}
*/
}
