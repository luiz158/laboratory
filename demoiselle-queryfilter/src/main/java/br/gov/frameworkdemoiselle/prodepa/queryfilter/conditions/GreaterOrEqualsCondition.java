package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class GreaterOrEqualsCondition extends DefaultCondition {

	public GreaterOrEqualsCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.GE, attribute, value, toLowerCase, sequence);
	}

}
