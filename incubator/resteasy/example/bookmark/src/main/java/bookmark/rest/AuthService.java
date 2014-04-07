package bookmark.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.security.Credentials;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.Beans;

@ValidateRequest
@Path("auth")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class AuthService {

	@Inject
	private SecurityContext securityContext;

	@POST
	public Response login(@Valid LoginForm form) {
		Credentials credentials = Beans.getReference(Credentials.class);
		credentials.setUsername(form.username);
		credentials.setPassword(form.password);

		securityContext.login();

		Token token = Beans.getReference(Token.class);
		NewCookie cookie = new NewCookie("token", token.toString());

		return Response.status(Status.CREATED).cookie(cookie).build();
	}

	public static class LoginForm {

		private String username;

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
