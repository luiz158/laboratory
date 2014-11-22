package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import java.util.Map;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.AbstractCondition;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.util.StringUtil;

public class BetweenCondition extends AbstractCondition {

	private Object valueB;
	
	public BetweenCondition(String attribute, Object valueA, Object valueB,  Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.BETWEEN, attribute, valueA, toLowerCase, sequence);
		this.valueB = valueB;
	}

	@Override
	public String getFragment() {
		if(getToLowerCase()) {
			return "LOWER("+getAttribute() + ") " + getType().getOperator() + " :p" + getSequence() + "_A AND :p" +  getSequence() + "_B";
		} else {
			return getAttribute() + " " + getType().getOperator() + " :p" + getSequence() + "_A AND :p" +  getSequence() + "_B";
		}
	}
	
	@Override
	public Map<String, Object> getFragmentParams() {
		if(getToLowerCase() && (getValue() instanceof String)) {
			putParam("p"+getSequence()+ "_A", ((String) getValue()).toLowerCase());
			putParam("p"+getSequence()+ "_B", ((String) getValueB()).toLowerCase());
		} else {
			putParam("p"+getSequence()+ "_A", getValue());
			putParam("p"+getSequence()+ "_B", getValueB());
		}
		
		return getParams();
	}

	
	public Object getValueB() {
		return valueB;
	}
	public void setValueB(Object valueB) {
		this.valueB = valueB;
	}
}
