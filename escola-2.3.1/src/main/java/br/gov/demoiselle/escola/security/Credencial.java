package br.gov.demoiselle.escola.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import br.gov.demoiselle.escola.bean.Usuario;

@SessionScoped
public class Credencial implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
