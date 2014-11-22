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
			return "LOWER(" + getAttribute() + ") " + getType().getOperator() + " :p" + getSequence();
		} else {
			return getAttribute() + " " + getType().getOperator() + " :p" + getSequence();
		}
	}
	
	@Override
	public Map<String, Object> getFragmentParams() {
		if(getToLowerCase()) {
			if(getValue() instanceof String) {
				putParam("p"+getSequence().toString(), ((String) getValue()).toLowerCase());
			} else {
				putParam("p"+getSequence().toString(), getValue());
			}
		} else {
			putParam("p"+getSequence().toString(), getValue());
		}
		return getParams();
	}
}
