package br.gov.serpro.catalogo.jee.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.security.SecurityContext;

@WebFilter(filterName = "LoginFilter", 
		  urlPatterns = { 
		"/home.html",
		"/home.html*",
		"/home.html#/*"})
public class LoginFilter implements Filter {
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private Logger logger;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
		logger.error("Verificando o acesso a url protegida.");
		if (!securityContext.isLoggedIn()) {
			logger.error("Não está logado, vai para o index.");
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath+"/index.html?destino=");
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}


}