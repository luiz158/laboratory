package example.security;


import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

@Alternative
public class LoginAuthenticator implements Authenticator {
	
	@Inject
	private Login credential;
	
	private User user;
	
	@Override
	public boolean authenticate() {
		if ((credential.getLogin().equals("admin"))&&(credential.getPassword().equals("admin"))) {
			user = new User() {
				
				@Override
				public void setAttribute(Object key, Object value) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public String getId() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Object getAttribute(Object key) {
					// TODO Auto-generated method stub
					return null;
				}
			};
			
			return true;
		}
		
		//código de autenticação
		
		// credencial -> banco (verificar usuario e senha)
		
		// carregar um objeto do tipo User
		
		return false;
	}

	@Override
	public User getUser() {
		
		//retornar o objeto User carregado no authenticate
		
		//código para retornar usuário logado
		return user;
	}

	@Override
	public void unAuthenticate() {
		user = null;
		
		// TODO Auto-generated method stub
	}

}
