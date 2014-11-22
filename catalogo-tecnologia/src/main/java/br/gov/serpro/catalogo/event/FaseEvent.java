package br.gov.serpro.catalogo.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.User;

public class FaseEvent {
	

	private final Fase fase;
	private final User user;
	
	public FaseEvent(Fase fase) {
		this.fase = fase;
		this.user = null;
	}

	public FaseEvent(User user, Fase fase) {
		this.fase = fase;
		this.user = user;
	}

	public Fase getFase() {
		return fase;
	}
	
	
	public User getUser() {
		return user;
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
	public @interface FINALIZAR {

	}
	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
	public @interface EXCLUIR {
		
		
	}
	
	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
	public @interface ADD_MEMBRO {}
	
	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
	public @interface ADD_INTERESSADO {}
	

}
