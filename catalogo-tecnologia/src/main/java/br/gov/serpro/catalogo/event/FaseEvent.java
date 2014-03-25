package br.gov.serpro.catalogo.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import br.gov.serpro.catalogo.entity.Fase;

public class FaseEvent {
	

	private final Fase fase;
	
	public FaseEvent(Fase fase) {
		this.fase = fase;
	}

	public Fase getFase() {
		return fase;
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
	

}
