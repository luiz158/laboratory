package br.gov.serpro.catalogo.exception;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.ApplicationException;

import br.gov.frameworkdemoiselle.security.AuthorizationException;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

	@Override
	public Response toResponse(ApplicationException exception) {
		if (exception.getCause() instanceof AuthorizationException) {
			return Response.status(SC_UNAUTHORIZED).entity(exception.getCause()).build();
		} else {
			return Response.status(SC_INTERNAL_SERVER_ERROR).entity(exception.getCause()).build();
		}
	}

}
