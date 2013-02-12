package br.gov.demoiselle.escola.security;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.business.UsuarioBC;
import br.gov.demoiselle.escola.ui.managedbean.LoginMB;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

@Alternative
public class Autenticador implements Authenticator{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LoginMB loginMB;
	
	@Inject
	private UsuarioBC usuarioBC;
	
	@Inject
	private Credencial credencial;

	@Override
	public boolean authenticate() {
		
		Usuario usuario = usuarioBC.login(loginMB.getUsuario());
		
		if(usuario != null){
			usuario.getPapeis();
			credencial.setUsuario(usuario);
			return true;
		}
		
		return false;
	}

	@Override
	public void unAuthenticate() {
		credencial.setUsuario(null);
	}

	@Override
	public User getUser() {
		
		if(credencial.getUsuario() == null ){
			return null;
		}
		else{
			return new User() {
				
				@Override
				public void setAttribute(Object key, Object value) {
					
				}
				
				@Override
				public String getId() {
					return String.valueOf(credencial.getUsuario().getId());
				}
				
				@Override
				public Object getAttribute(Object key) {
					return null;
				}
			};
		}
	}

}
