package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class LikeCondition extends DefaultCondition {

	public LikeCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.LIKE, attribute, value, toLowerCase, sequence);
	}

}
