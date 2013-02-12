package br.gov.demoiselle.escola.security;

import java.util.Set;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.PapelUsuario;
import br.gov.frameworkdemoiselle.security.Authorizer;

public class Autorizador implements Authorizer {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Credencial credencial;
	
	@Override
	public boolean hasRole(String role) {
		
		Set<PapelUsuario> papeis = credencial.getUsuario().getPapeis();
		
		for(PapelUsuario papel : papeis){
			if(papel.getPapel().equals(role)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		return false;
	}

}
