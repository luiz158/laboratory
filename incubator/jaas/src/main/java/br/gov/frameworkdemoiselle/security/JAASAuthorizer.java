package br.gov.frameworkdemoiselle.security;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Enumeration;

import javax.security.auth.login.LoginContext;

import br.gov.frameworkdemoiselle.util.Beans;

public class JAASAuthorizer implements Authorizer {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean hasRole(String role) {
		boolean result = false;

		LoginContext loginContext = Beans.getReference(JAASAuthenticator.class).getLoginContext();
		Group group;
		Principal member;
		Enumeration<? extends Principal> enumeration;

		for (Principal principal : loginContext.getSubject().getPrincipals()) {

			if (principal instanceof Group) {
				group = (Group) principal;
				enumeration = group.members();

				while (enumeration.hasMoreElements()) {
					member = (Principal) enumeration.nextElement();

					if (member.getName().equals(role)) {
						result = true;
						break;
					}
				}

				// principal = (Group) principal;
			}
		}

		return result;
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		return true;
	}
}
