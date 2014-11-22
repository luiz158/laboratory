package bookmark.security;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import bookmark.rest.Token;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.Credentials;
import br.gov.frameworkdemoiselle.security.InvalidCredentialsException;
import br.gov.frameworkdemoiselle.security.User;
import br.gov.frameworkdemoiselle.util.Beans;

@RequestScoped
public class TokenAuthenticator implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Inject
	private Credentials credentials;

	@Inject
	private Token token;

	public void authenticate(Credentials credentials) throws Exception {
		final String username = credentials.getUsername();
		final String password = credentials.getPassword();

		if (username.equalsIgnoreCase(password)) {
			token.setValue(String.valueOf(new Date().getTime()));

			User user = new User() {

				private static final long serialVersionUID = 1L;

				@Override
				public String getId() {
					return username;
				}

				@Override
				public Object getAttribute(Object key) {
					return null;
				}

				@Override
				public void setAttribute(Object key, Object value) {
				}
			};

			setUser(user);

		} else {
			throw new InvalidCredentialsException("usuário ou senha inválidos");
		}
	}

	public void authenticate(Token token) throws Exception {
		if (getUser() == null) {
			throw new InvalidCredentialsException("token inválido");
		}
	}

	@Override
	public void authenticate() throws Exception {
		if (credentials.getUsername() != null) {
			authenticate(credentials);

		} else if (token.toString() != null) {
			authenticate(token);

		} else {
			throw new InvalidCredentialsException("nenhuma credencial foi informada");
		}
	}

	@Override
	public void unauthenticate() throws Exception {
		setUser(null);
	}

	@Override
	public User getUser() {
		HttpSession session = Beans.getReference(HttpSession.class);
		return (User) session.getAttribute(token.toString());
	}

	private void setUser(User user) {
		HttpSession session = Beans.getReference(HttpSession.class);
		session.setAttribute(token.toString(), user);
	}
}
