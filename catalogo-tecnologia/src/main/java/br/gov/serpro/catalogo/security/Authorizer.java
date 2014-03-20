package br.gov.serpro.catalogo.security;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.serpro.catalogo.entity.Grupo;
import br.gov.serpro.catalogo.entity.Perfil;
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.persistence.UserDAO;


public class Authorizer implements br.gov.frameworkdemoiselle.security.Authorizer{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private UserDAO userDAO;
	
	@Override
	public boolean hasRole(String role) throws Exception {
		if(securityContext.isLoggedIn()) {
			User user = getUser();
			for(Grupo grupo : user.getGrupos()) {
				for(Perfil perfil : grupo.getPerfis()) {
					if(role.equalsIgnoreCase(perfil.toString())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private User getUser() {
		return userDAO.loadByCPF(((User) securityContext.getUser()).getCPF());
	}

	@Override
	public boolean hasPermission(String resource, String operation) throws Exception {
		return false;
	}

}
