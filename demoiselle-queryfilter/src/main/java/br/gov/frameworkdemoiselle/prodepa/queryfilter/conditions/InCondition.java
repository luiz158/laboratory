package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.util.StringUtil;

public class InCondition extends DefaultCondition {

	public InCondition(String attribute, List values, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.IN, attribute, values, toLowerCase, sequence);
	}
	
	@Override
	public String getFragment() {
		if(getToLowerCase()) {
			
			Boolean isString = false;
			if(getValue() instanceof List) {

				if(((List<?>) getValue()).get(0) instanceof String) {
					isString = true;
				}
						
			} else {
				if(getValue() instanceof String) {
					isString = true;
				}
			}
			
			if(isString) {
				return "LOWER( "+getAttribute() + ") " + getType().getOperator() + " ( :p" + getSequence() + " )";
			} else {
				return getAttribute() + " " + getType().getOperator() + " ( :p" + getSequence() + " )";
			}
			
		} else {
			return getAttribute() + " " + getType().getOperator() + " ( :p" + getSequence() + " )";
		}
	}

}
