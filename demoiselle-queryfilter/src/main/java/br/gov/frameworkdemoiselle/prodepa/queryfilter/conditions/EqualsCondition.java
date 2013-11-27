package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class EqualsCondition extends DefaultCondition {

	public EqualsCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.EQUAL, attribute, value, toLowerCase, sequence);
	}

}
