package br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//TODO Nada implementado ainda
@Retention(RUNTIME)
@Target({METHOD, FIELD}) 
public @interface Fetch {
  
  
}
