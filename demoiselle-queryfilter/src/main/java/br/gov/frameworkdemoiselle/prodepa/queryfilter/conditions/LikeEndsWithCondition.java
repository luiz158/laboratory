package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class LikeEndsWithCondition extends DefaultCondition {

	public LikeEndsWithCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.LIKE_ENDS_WITH, attribute, value, toLowerCase, sequence);
	}

}
