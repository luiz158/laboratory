package br.gov.serpro.catalogo.security;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.serpro.catalogo.entity.Grupo;
import br.gov.serpro.catalogo.entity.Perfil;
import br.gov.serpro.catalogo.entity.User;

public class Authorizer implements br.gov.frameworkdemoiselle.security.Authorizer{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SecurityContext securityContext;

	@Override
	public boolean hasRole(String role) throws Exception {
		if(securityContext.isLoggedIn()) {
			User user = (User)securityContext.getUser();
			if(user.getGrupos() != null) {
				for(Grupo grupo : user.getGrupos()) {
					for(Perfil perfil : grupo.getPerfis()) {
						if(role.equalsIgnoreCase(perfil.toString())) {
							return true;
						}
					}
				}
			}
		} 
		return false;
	}

	@Override
	public boolean hasPermission(String resource, String operation) throws Exception {
		return false;
	}

}
