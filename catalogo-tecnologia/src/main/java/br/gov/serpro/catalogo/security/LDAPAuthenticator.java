package br.gov.serpro.catalogo.security;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpSession;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.Credentials;
import br.gov.frameworkdemoiselle.security.InvalidCredentialsException;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.serpro.catalogo.bussiness.UsuarioBC;
import br.gov.serpro.catalogo.entity.User;

@SessionScoped
public class LDAPAuthenticator implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Inject
	private Credentials credentials;

	private User user;

	@Inject
	private LDAPConfig ldapConfig;

	@Inject
	private UsuarioBC usuarioBC;

	@Override
	public void authenticate() throws Exception {

		// Criando o usuário admin, caso não exista.
		if (credentials.getUsername().equals("admin")) {
			if (credentials.getPassword().equals("adminCetec")) {
				this.user = usuarioBC.iniciarUsuario();
				return;
			}else{
				throw new InvalidCredentialsException("usuário ou senha inválidos");
			}
		}

		SearchControls controls = createSearchControls();
		String filter = createCPFFilter(credentials.getUsername());
		SearchResult searchResult = createSearchResult(controls, filter);

		LdapContext ldapContext = createContext(
				searchResult.getNameInNamespace(), credentials.getPassword());
		ldapContext.close();

		this.user = usuarioBC.carregarOuInserir(createUser(searchResult
				.getAttributes()));

		List<String> roles = new ArrayList<String>();
		roles.add("logado");
		user.setAttribute("roles", roles);

		/*
		 * } catch (Exception cause) { throw new
		 * InvalidCredentialsException("usuário ou senha inválidos"); } catch
		 * (AuthenticationException cause) {
		 * 
		 * }
		 */
	}

	public User searchUserByCPF(String cpf) throws NamingException {
		SearchControls controls = createSearchControls();
		String filter = createCPFFilter(cpf);
		SearchResult searchResult = createSearchResult(controls, filter);
		if (searchResult == null) {
			return null;
		}
		return createUser(searchResult.getAttributes());
	}

	public List<User> searchUserByDisplayName(String displayName)
			throws NamingException {
		SearchControls controls = createSearchControls();
		String filter = createDisplayNameFilter(displayName);
		NamingEnumeration<SearchResult> searchResults = createListSearchResult(
				controls, filter);
		if (searchResults == null) {
			return null;
		}

		List<User> users = new ArrayList<User>();
		while (searchResults.hasMore()) {
			users.add(createUser(((SearchResult) searchResults.next())
					.getAttributes()));
		}
		return users;
	}

	@Override
	public void unauthenticate() throws Exception {
		this.user = null;
		Beans.getReference(HttpSession.class).invalidate();
	}

	@Override
	public User getUser() {
		return this.user;
	}

	private User createUser(Attributes attributes) throws NamingException {
		User result = new User();

		result.setCPF(attributes.get("uid").get().toString());
		result.setName(attributes.get("cn").get().toString());
		if (attributes.get("mail") == null) {
			result.setEmail("");
		} else {
			result.setEmail(attributes.get("mail").get().toString());
		}
		if (attributes.get("telephoneNumber") == null) {
			result.setTelephoneNumber("");
		} else {
			result.setTelephoneNumber(attributes.get("telephoneNumber").get()
					.toString());
		}
		if (attributes.get("ou") == null) {
			result.setSetor("");
		} else {
			result.setSetor(attributes.get("ou").get().toString());
		}

		return result;
	}

	private SearchControls createSearchControls() {
		final SearchControls result = new SearchControls();
		result.setSearchScope(ldapConfig.getSearchScope());

		return result;
	}

	private String createCPFFilter(String username) {
		String result = ldapConfig.getBaseFilter();
		result = result.replaceAll("\\{0\\}", username);

		return result;
	}

	private String createDisplayNameFilter(String displayname) {
		String result = ldapConfig.getNameFilter();
		result = result.replaceAll("\\{0\\}", displayname);

		return result;
	}

	private SearchResult createSearchResult(SearchControls controls,
			String filter) throws NamingException {
		LdapContext ldapContext = createContext();
		final NamingEnumeration<SearchResult> naming = ldapContext.search(
				ldapConfig.getBaseCtxDN(), filter, controls);
		ldapContext.close();

		return naming.hasMoreElements() ? naming.next() : null;
	}

	private NamingEnumeration<SearchResult> createListSearchResult(
			SearchControls controls, String filter) throws NamingException {
		LdapContext ldapContext = createContext();
		final NamingEnumeration<SearchResult> naming = ldapContext.search(
				ldapConfig.getBaseCtxDN(), filter, controls);
		ldapContext.close();

		return naming.hasMoreElements() ? naming : null;
	}

	private LdapContext createContext() throws NamingException {
		return createContext(ldapConfig.getBindDN(),
				ldapConfig.getBindCredential());
	}

	private LdapContext createContext(String username, String password)
			throws NamingException {
		final Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapConfig.getProviderURL());
		env.put(Context.SECURITY_AUTHENTICATION,
				ldapConfig.getSecurityAuthentication());
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		env.put(Context.REFERRAL, "follow");

		return new InitialLdapContext(env, null);
	}

}
