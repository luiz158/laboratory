package br.gov.frameworkdemoiselle.prodepa.queryfilter.stereotype;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

@Stereotype
@Target(TYPE) 
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface QueryFilter {

  String[] orderByAsc() default ""; //Seria aki ou em uma anotacao propria
  
  String[] orderByDesc() default ""; //Seria aki ou em uma anotacao propria
  
}
