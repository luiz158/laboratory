package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons;

import java.util.Map;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.util.StringUtil;

public class DefaultCondition extends AbstractCondition {

	public DefaultCondition(AndConditionType type, String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(type, attribute, value, toLowerCase,sequence);
	}

	@Override
	public String getFragment() {
		if(getToLowerCase()) {
			return "LOWER(" + getAttribute() + ") " + getType().getOperator() + " :_" + getSequence();
		} else {
			return getAttribute() + " " + getType().getOperator() + " :_" + getSequence();
		}
	}
	
	@Override
	public Map<String, Object> getFragmentParams() {
		if(getToLowerCase()) {
			if(getValue() instanceof String) {
				putParam("_"+getSequence().toString(), ((String) getValue()).toLowerCase());
			} else {
				putParam("_"+getSequence().toString(), getValue());
			}
		} else {
			putParam("_"+getSequence().toString(), getValue());
		}
		return getParams();
	}
}
