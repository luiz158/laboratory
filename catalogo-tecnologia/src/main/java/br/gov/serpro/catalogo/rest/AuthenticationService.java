package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.security.Credentials;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.serpro.catalogo.entity.User;

@ValidateRequest
@Path("auth")
@Produces(APPLICATION_JSON)
public class AuthenticationService {

	@Inject
	private SecurityContext securityContext;

	@POST
	public User login(@Valid LoginForm form) throws Exception {
		Credentials credentials = Beans.getReference(Credentials.class);
		credentials.setUsername(form.username);
		credentials.setPassword(form.password);

		securityContext.login();

		return (User) securityContext.getUser();
	}

	@DELETE
	public void logout() {
		securityContext.logout();
	}

	@GET
	public boolean isLoggedIn() {
		return securityContext.isLoggedIn();
	}

	@GET
	@Path("user")
	public User getUser() {
		return (User) securityContext.getUser();
	}

	public static class LoginForm {

		@NotEmpty
		private String username;

		@NotEmpty
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

}
