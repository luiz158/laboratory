package example.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class Login implements Serializable{

	private String login;
	private String password;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
