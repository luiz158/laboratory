package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import java.util.Map;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.AbstractCondition;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class IsEmptyCondition extends AbstractCondition {

	public IsEmptyCondition(String attribute, Integer sequence) {
		super(AndConditionType.IS_EMPTY, attribute, null, null, sequence);
	}

	@Override
	public String getFragment() {
		return getAttribute() + " " + getType().getOperator();
	}
	
	@Override
	public Map<String, Object> getFragmentParams() {
		return getParams();
	}
}
