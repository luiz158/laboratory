package br.gov.frameworkdemoiselle.management.internal.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.gov.frameworkdemoiselle.management.validation.AllowedValues;


public class AllowedValuesValidator implements ConstraintValidator<AllowedValues, Object> {
	
	private br.gov.frameworkdemoiselle.management.validation.AllowedValues.ValueType valueType;
	private String[] allowedValues;

	@Override
	public void initialize(AllowedValues constraintAnnotation) {
		valueType = constraintAnnotation.valueType();
		allowedValues = constraintAnnotation.allows();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		//TODO Implementar este método.
		return true;
		
		/*if (value==null){
			return false;
		}

		switch(valueType){
			case STRING:
				for (String str : allowedValues){
					if (str.equals(value)) return true;
				}
				return false;
				
			case INTEGER:
				try{
					Integer number = Integer.valueOf(value.toString());
					//for ()
				}
				catch(NumberFormatException ne){
					return false;
				}
		}

		return false;*/
	}

}
