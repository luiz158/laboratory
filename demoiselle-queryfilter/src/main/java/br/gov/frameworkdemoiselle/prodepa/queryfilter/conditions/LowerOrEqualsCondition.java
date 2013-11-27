package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.util.StringUtil;

public class LowerOrEqualsCondition extends DefaultCondition {

	public LowerOrEqualsCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.LE, attribute, value, toLowerCase, sequence);
	}
	
}
