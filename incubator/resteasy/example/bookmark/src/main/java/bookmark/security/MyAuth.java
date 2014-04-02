package bookmark.security;

import javax.enterprise.context.RequestScoped;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.Credentials;
import br.gov.frameworkdemoiselle.security.User;
import br.gov.frameworkdemoiselle.util.Beans;

@RequestScoped
public class MyAuth implements Authenticator {

	private static final long serialVersionUID = 1L;

	private User user;

	@Override
	public void authenticate() throws Exception {

		final Credentials credentials = Beans.getReference(Credentials.class);

		this.user = new User() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getId() {
				return credentials.getUsername();
			}

			@Override
			public Object getAttribute(Object key) {
				return null;
			}

			@Override
			public void setAttribute(Object key, Object value) {
			}
		};
	}

	@Override
	public void unauthenticate() throws Exception {
		this.user = null;
	}

	@Override
	public User getUser() {
		return this.user;
	}
}
