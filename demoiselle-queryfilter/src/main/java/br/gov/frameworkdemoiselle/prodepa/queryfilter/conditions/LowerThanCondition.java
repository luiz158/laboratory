package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class LowerThanCondition extends DefaultCondition {

	public LowerThanCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.LT, attribute, value, toLowerCase, sequence);
	}

}
