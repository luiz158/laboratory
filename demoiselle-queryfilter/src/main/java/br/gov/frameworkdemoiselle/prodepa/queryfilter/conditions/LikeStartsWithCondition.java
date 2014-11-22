package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class LikeStartsWithCondition extends DefaultCondition {

	public LikeStartsWithCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.LIKE_STARTS_WITH, attribute, value, toLowerCase, sequence);
	}

}
