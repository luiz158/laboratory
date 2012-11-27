package br.gov.frameworkdemoiselle.security;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Credentials implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	public void clear() {
		this.username = null;
		this.password = null;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
