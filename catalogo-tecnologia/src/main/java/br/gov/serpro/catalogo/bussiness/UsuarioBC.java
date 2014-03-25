package br.gov.serpro.catalogo.bussiness;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.catalogo.entity.Usuario;
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
	public Usuario carregarOuInserir(Usuario user){
		Usuario usuarioSistema = userDAO.loadByCPF(user.getCPF());
		if (usuarioSistema == null) {			
			usuarioSistema = userDAO.insert(user);
		}
		return usuarioSistema;
	}


	

}
