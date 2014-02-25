package br.gov.serpro.catalogo.security;

import java.util.Hashtable;

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
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.serpro.catalogo.entity.User;

@SessionScoped
public class LDAPAuthenticator implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Inject
	private Credentials credentials;

	private User user;
	
	@Inject
	private LDAPConfig ldapConfig;

	@Override
	public void authenticate() throws Exception {
		
			SearchControls controls = createSearchControls();
			String filter = createFilter(credentials.getUsername());
			SearchResult searchResult = createSearchResult(controls, filter);

			LdapContext ldapContext = createContext(searchResult.getNameInNamespace(), credentials.getPassword());
			ldapContext.close();

			user = createUser(searchResult.getAttributes());

		/*} catch (Exception cause) {
			throw new InvalidCredentialsException("usuário ou senha inválidos");
		} catch (AuthenticationException cause) {
			
		}*/
	}
	
	public User searchUserByCPF(String cpf) throws NamingException {
		SearchControls controls = createSearchControls();
		String filter = createFilter(cpf);
		SearchResult searchResult = createSearchResult(controls, filter);
		if(searchResult == null){
			return null;
		}
		return createUser(searchResult.getAttributes());
	}

	@Override
	public void unauthenticate() throws Exception {
		user = null;
		Beans.getReference(HttpSession.class).invalidate();		
	}

	@Override
	public User getUser() {
		return user;
	}

	private User createUser(Attributes attributes) throws NamingException {
		User result = new User();

		result.setName(attributes.get("uid").get().toString());
		result.setDisplayName(attributes.get("cn").get().toString());
		result.setEmail(attributes.get("mail").get().toString());
		result.setTelephoneNumber(attributes.get("telephoneNumber").get().toString());

		return result;
	}
	
	private SearchControls createSearchControls() {
		final SearchControls result = new SearchControls();
		result.setSearchScope(ldapConfig.getSearchScope());

		return result;
	}

	private String createFilter(String username) {
		String result = ldapConfig.getBaseFilter();
		result = result.replaceAll("\\{0\\}", username);

		return result;
	}
	
	private SearchResult createSearchResult(SearchControls controls, String filter) throws NamingException {
		LdapContext ldapContext = createContext();
		final NamingEnumeration<SearchResult> naming = ldapContext.search(ldapConfig.getBaseCtxDN(), filter, controls);
		ldapContext.close();

		return naming.hasMoreElements() ? naming.next() : null;
	}
	
	private LdapContext createContext() throws NamingException {
		return createContext(ldapConfig.getBindDN(), ldapConfig.getBindCredential());
	}
	
	private LdapContext createContext(String username, String password) throws NamingException {
		final Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapConfig.getProviderURL());
		env.put(Context.SECURITY_AUTHENTICATION, ldapConfig.getSecurityAuthentication());
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		env.put(Context.REFERRAL, "follow");

		return new InitialLdapContext(env, null);
	}

	
}
