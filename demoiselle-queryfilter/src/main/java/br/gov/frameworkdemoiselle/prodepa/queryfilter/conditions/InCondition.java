package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

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
		return getAttribute() + " " + getType().getOperator() + " ( :_" + getSequence() + " )";
	}

}
