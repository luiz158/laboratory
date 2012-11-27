package br.gov.frameworkdemoiselle.security;

import java.io.IOException;
import java.security.Principal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import br.gov.frameworkdemoiselle.internal.configuration.JAASConfig;

@SessionScoped
public class JAASAuthenticator implements Authenticator {

	private static final long serialVersionUID = 1L;

	private transient LoginContext loginContext;

	private User user;

	@Inject
	private Credentials credentials;

	@Inject
	private JAASConfig config;

	@Override
	public boolean authenticate() {
		boolean result = false;

		try {
			getLoginContext().login();
			getLoginContext().getSubject().getPrincipals().add(new Principal() {

				@Override
				public String getName() {
					return credentials.getUsername();
				}
			});

			this.credentials.clear();
			result = true;

		} catch (LoginException cause) {
			result = false;
		}

		return result;
	}

	@Override
	public void unAuthenticate() {
		try {
			getLoginContext().logout();
			user = null;

		} catch (LoginException cause) {
			cause.printStackTrace();
		}
	}

	@Override
	public User getUser() {
		if (this.user == null && getLoginContext().getSubject() != null
				&& !getLoginContext().getSubject().getPrincipals().isEmpty()) {
			this.user = new User() {

				private static final long serialVersionUID = 1L;

				@Override
				public String getId() {
					return getLoginContext().getSubject().getPrincipals().iterator().next().getName();
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

		return this.user;
	}

	public LoginContext getLoginContext() {
		if (this.loginContext == null) {
			this.loginContext = createLoginContext();
		}

		return this.loginContext;
	}

	protected LoginContext createLoginContext() {
		LoginContext result = null;

		try {
			result = new LoginContext(this.config.getLoginModuleName(), createCallbackHandler());

		} catch (LoginException cause) {
			throw new SecurityException(cause);
		}

		return result;
	}

	protected CallbackHandler createCallbackHandler() {
		return new CallbackHandler() {

			public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
				for (int i = 0; i < callbacks.length; i++) {
					if (callbacks[i] instanceof NameCallback) {
						((NameCallback) callbacks[i]).setName(credentials.getUsername());

					} else if (callbacks[i] instanceof PasswordCallback) {
						((PasswordCallback) callbacks[i]).setPassword(credentials.getPassword().toCharArray());

					} else {
						System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX Unsupported callback " + callbacks[i]);
					}
				}
			}
		};
	}
}
