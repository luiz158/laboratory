package exp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<Url, String> {

	@Override
	public void initialize(final Url constraintAnnotation) {
	}

	@Override
	public boolean isValid(String url, final ConstraintValidatorContext context) {
		boolean result = false;
		if ( url == null || "".equals(url) ) {
			result = true;
		} else {
			Pattern pattern = Pattern.compile("^(http(?:s)?\\:\\/\\/[a-zA-Z0-9\\-]+(?:\\.[a-zA-Z0-9\\-]+)*\\.[a-zA-Z]{2,6}(?:\\/?|(?:\\/[\\w\\-]+)*)(?:\\/?|\\/\\w+\\.[a-zA-Z]{2,4}(?:\\?[\\w]+\\=[\\w\\-]+)?)?(?:\\&[\\w]+\\=[\\w\\-]+)*)$");  
			Matcher matcher = pattern.matcher(url);
			result = matcher.find();
		}
		return result;
	}
	
}
