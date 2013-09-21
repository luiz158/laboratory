package exp.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@Constraint(validatedBy = UrlValidator.class)
public @interface Url {

	Class<?>[] groups() default {};

	String message() default "{exp.url}";

	Class<? extends Payload>[] payload() default {};
	
}
