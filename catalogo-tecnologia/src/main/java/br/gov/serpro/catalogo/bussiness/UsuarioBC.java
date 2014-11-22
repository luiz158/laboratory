package br.gov.serpro.catalogo.bussiness;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Grupo;
import br.gov.serpro.catalogo.entity.Perfil;
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.persistence.GrupoDAO;
import br.gov.serpro.catalogo.persistence.UserDAO;

@BusinessController
public class UsuarioBC implements Serializable{

	private static final long serialVersionUID = -7801407214303725321L;
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private GrupoDAO grupoDAO;

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
		
		if(grupoDAO.findAll().isEmpty()){
			Grupo grupo = new Grupo();
			grupo.setNome("ADMIN");
			grupo.setDescricao("Grupo para os administradores do sistema.");
			grupo.setPerfis(new ArrayList<Perfil>());
			grupo.getPerfis().add(Perfil.ADMINISTRADOR);
			admin.setGrupos(new ArrayList<Grupo>());
			admin.getGrupos().add(grupoDAO.insert(grupo));
		}
		
		return this.carregarOuInserir(admin);
	}
	

}
