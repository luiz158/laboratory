//package bookmark.security;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import bookmark.rest.Token;
//import br.gov.frameworkdemoiselle.security.SecurityContext;
//import br.gov.frameworkdemoiselle.util.Beans;
//
//@WebFilter("/api/*")
//public class TokenAuthenticationFilter implements Filter {
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
//			ServletException {
//
//		if (request instanceof HttpServletRequest) {
//			Cookie[] cookies = ((HttpServletRequest) request).getCookies();
//			String value = null;
//
//			if (cookies != null) {
//				for (int i = 0; i < cookies.length; i++) {
//					if (cookies[i].getName().equalsIgnoreCase("token")) {
//						value = cookies[i].getValue();
//						break;
//					}
//				}
//			}
//
//			if (value != null) {
//				Token token = Beans.getReference(Token.class);
//				token.setValue(value);
//
//				SecurityContext securityContext = Beans.getReference(SecurityContext.class);
//				securityContext.login();
//			}
//		}
//
//		chain.doFilter(request, response);
//	}
//
//	@Override
//	public void destroy() {
//	}
//}
