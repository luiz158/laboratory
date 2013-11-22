package br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations;

import static br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType.EQUAL;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;

@Retention(RUNTIME)
@Target({METHOD, FIELD}) 
public @interface And {
  
  String[] attributeNames() default {};
  
  boolean ignoreCase() default false;
  
  AndConditionType conditionType() default EQUAL;
  
}
