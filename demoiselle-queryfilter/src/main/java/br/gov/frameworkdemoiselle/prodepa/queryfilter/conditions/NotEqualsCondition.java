package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class NotEqualsCondition extends DefaultCondition {

	public NotEqualsCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.NOT_EQUALS, attribute, value, toLowerCase, sequence);
	}

}
