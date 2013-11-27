package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class GreaterThanCondition extends DefaultCondition {

	public GreaterThanCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.GT, attribute, value, toLowerCase, sequence);
	}

}
