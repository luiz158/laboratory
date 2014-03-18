package br.gov.serpro.catalogo.exception;

import javax.naming.SizeLimitExceededException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.servlet.http.HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE;

@Provider
public class SizeLimitExceededExceptionMapper implements ExceptionMapper<SizeLimitExceededException> {

	@Override
	public Response toResponse(SizeLimitExceededException exception) {
		return Response.status(SC_REQUEST_ENTITY_TOO_LARGE).entity(exception.getCause()).build();
	}

}
