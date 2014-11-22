package bookmark.rest;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import java.util.Date;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("exp")
public class ExpService {

	@POST
	public Response criar() {
		NewCookie cookie = new NewCookie("token", String.valueOf(new Date().getTime()));
		return Response.status(NO_CONTENT).cookie(cookie).build();
	}

	@GET
	public String obter(@CookieParam("token") String token) {
		return token;
	}
}
