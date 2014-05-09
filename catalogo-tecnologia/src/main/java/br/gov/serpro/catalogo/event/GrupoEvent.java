package br.gov.serpro.catalogo.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.inject.Qualifier;

import br.gov.serpro.catalogo.entity.Grupo;
import br.gov.serpro.catalogo.entity.User;


public class GrupoEvent {
	
	private final Grupo grupo;
	private final List<Grupo> listaGrupos;
	private final User user;
	
	public GrupoEvent(Grupo grupo) {
		this.grupo = grupo;
		this.listaGrupos = null;
		this.user = null;
	}

	public GrupoEvent(List<Grupo> listaGrupos, User user) {
		this.grupo = null;
		this.listaGrupos = listaGrupos;
		this.user = user;
	}

	public Grupo getGrupo() {
		return grupo;
	}
	
	
	public List<Grupo> getListaGrupos() {
		return this.listaGrupos;
	}

	public User getUser() {
		return this.user;
	}

	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
	public @interface CRIAR {

	}
	
	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
	public @interface ATUALIZAR {

	}
	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
	public @interface EXCLUIR {
		
		
	}
	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
	public @interface INSERIR_USUARIO {
		
		
	}
}