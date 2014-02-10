package br.gov.frameworkdemoiselle.resteasy.internal.implementation;

import static javax.servlet.http.HttpServletResponse.SC_PRECONDITION_FAILED;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.JsonMappingException;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;

@Provider
public class DateConversionExceptionMapper implements ExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(JsonMappingException exception) {
		Response response;

		if (exception.getLocalizedMessage().indexOf(Date.class.getCanonicalName()) > -1) {
			ValidationException validation = new ValidationException();
			validation.addViolation(exception.getPath().get(0).getFieldName(), "data inválida");

			response = Response.status(SC_PRECONDITION_FAILED).entity(validation.getConstraintViolations()).build();

		} else {
			response = Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
		}

		return response;
	}
}
