package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class NotLikeCondition extends DefaultCondition {

	public NotLikeCondition(String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.NOT_LIKE, attribute, value, toLowerCase, sequence);
	}

}
