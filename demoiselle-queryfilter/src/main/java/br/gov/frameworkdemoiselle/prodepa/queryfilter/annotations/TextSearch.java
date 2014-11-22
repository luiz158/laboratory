package br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.TextSearchStyle.Like;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.TextSearchStyle;

@Retention(RUNTIME)
@Target({METHOD, FIELD}) 
public @interface TextSearch {

  TextSearchStyle strategy() default Like;
  
}
