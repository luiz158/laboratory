package br.gov.serpro.catalogo.bussiness;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.persistence.UserDAO;

@BusinessController
public class UsuarioBC implements Serializable{

	private static final long serialVersionUID = -7801407214303725321L;
	
	@Inject
	private UserDAO userDAO;

	/**
	 * Verifica se o usuario existe pelo CPF. Caso não exista persiste o usuário.
	 * 
	 * @param user
	 * @return
	 */
	public User carregarOuInserir(User user){
		User usuarioSistema = userDAO.loadByCPF(user.getCPF());
		if (usuarioSistema == null) {			
			usuarioSistema = userDAO.insert(user);
		}
		return usuarioSistema;
	}


	@Startup
	@Priority(0)
	@Transactional
	public User iniciarUsuario(){
		User admin = new User();
		admin.setCPF("admin");
		admin.setEmail("admin@catalogotecnologia.serpro");
		admin.setName("ADMIN");
		admin.setSetor("");
		admin.setTelephoneNumber("");
		return this.carregarOuInserir(admin);
	}
	

}
