package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions;

import java.util.List;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons.DefaultCondition;

public class NotInCondition extends DefaultCondition {

	public NotInCondition(String attribute, List values, Boolean toLowerCase, Integer sequence) {
		super(AndConditionType.NOT_IN, attribute, values, toLowerCase, sequence);
	}

}
