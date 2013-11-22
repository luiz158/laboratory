package br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations;

import static br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.OrConditionType.EQUAL;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.OrConditionType;

@Retention(RUNTIME)
@Target({METHOD, FIELD}) 
public @interface Or {
  
  String[] attributeNames() default {};
  
  boolean ignoreCase() default false;
  
  OrConditionType conditionType() default EQUAL;
}
