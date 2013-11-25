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
		String f = getAttribute() + " " + getType().getOperator() + " :_" + getSequence() + "_A AND :_" +  getSequence() + "_B";
		return f;
	}
	
	@Override
	public Map<String, Object> getFragmentParams() {
		putParam("_"+getSequence()+ "_A", getValue());
		putParam("_"+getSequence()+ "_B", getValueB());
		
		return getParams();
	}

	
	public Object getValueB() {
		return valueB;
	}
	public void setValueB(Object valueB) {
		this.valueB = valueB;
	}
}
