package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.naming.SizeLimitExceededException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.event.GrupoEvent;
import br.gov.serpro.catalogo.event.GrupoEvent.INSERIR_USUARIO;
import br.gov.serpro.catalogo.persistence.UserDAO;
import br.gov.serpro.catalogo.security.LDAPAuthenticator;

@ValidateRequest
@Path("user")
@Produces(APPLICATION_JSON)
public class UserService {
	
	static final String ADMINISTRADOR = "ADMINISTRADOR";

	@Inject
	private UserDAO userDAO;
	
	@Inject
	private LDAPAuthenticator ldapAuthenticator;

	@Inject @INSERIR_USUARIO private Event<GrupoEvent> eventoGrupoInserirUsuario;

	@GET
	public List<User> listar() {
		List<User> users = userDAO.findAll();
		return users;
	}
	
	@GET
	@Path("{id}")
	public User carregar(@NotNull @PathParam("id") Long id) {
		return userDAO.load(id);
	}
	
	@GET
	@Path("cpf/{cpf}")
	public User carregarByCPF(@NotNull @PathParam("cpf") String cpf) throws Exception {
		return ldapAuthenticator.searchUserByCPF(cpf);
	}
	
	@GET
	@Path("nome/{nome}")
	public List<User> carregarByNome(@NotNull @PathParam("nome") String nome) throws Exception {
		try {
			return ldapAuthenticator.searchUserByDisplayName(nome);
//			return userDAO.findAll();
		}catch(SizeLimitExceededException sizeLimitExceededException) {
			throw sizeLimitExceededException;
		}
	}
	
	@PUT
	@Transactional
	@RequiredRole(ADMINISTRADOR)
	public void alterar(@Valid User user) {
		userDAO.update(user);
		if((user.getGrupos() != null) && (user.getGrupos().size() > 0)){
			eventoGrupoInserirUsuario.fire(new GrupoEvent(user.getGrupos(),user));
		}
	}
	
	@POST
	@Transactional
	@RequiredRole(ADMINISTRADOR)
	public void inserir(@Valid User user)  throws Exception {
		if(userExists(user.getCPF())) {
			ValidationException ve = new ValidationException();
			ve.addViolation(null, "Usuário já cadastrado na base.");
			throw ve;
		}else {
			userDAO.insert(user);
			if((user.getGrupos() != null) && (user.getGrupos().size() > 0)){
				eventoGrupoInserirUsuario.fire(new GrupoEvent(user.getGrupos(),user));
			}
		}
	}
	
	private boolean userExists(String cpf) {
		if (userDAO.loadByCPF(cpf) != null){
			return true;
		}
		return false;
	}
}