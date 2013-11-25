package br.gov.frameworkdemoiselle.prodepa.queryfilter.conditions.commons;

import java.util.HashMap;
import java.util.Map;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.util.StringUtil;


public abstract class AbstractCondition {

	private Integer sequence;
	
	private AndConditionType type;
	
	private String attribute;

	private Object value;

	private Boolean toLowerCase;
	
	private Map<String, Object> params;

	public AbstractCondition(AndConditionType type, String attribute, Object value, Boolean toLowerCase, Integer sequence) {
		super();
		this.type = type;
		this.attribute = attribute;
		this.value = value;
		this.toLowerCase = toLowerCase;
		this.sequence = sequence;
		
		this.params = new HashMap<String, Object>();
	}

	public abstract String getFragment();
	
	public abstract Map<String, Object> getFragmentParams();
	
	//StringUtil.castToParamName(getAttribute())
	
	public void putParam(String key, Object val) {
		this.params.put(StringUtil.castToParamName(key), val);
	}
	
	
	/*Getters and Setters*/
	
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Boolean getToLowerCase() {
		return toLowerCase;
	}

	public void setToLowerCase(Boolean toLowerCase) {
		this.toLowerCase = toLowerCase;
	}

	
	public Map<String, Object> getParams() {
		return params;
	}

	
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	
	public AndConditionType getType() {
		return type;
	}
	
	public void setType(AndConditionType type) {
		this.type = type;
	}

	
	public Integer getSequence() {
		return sequence;
	}

	
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	
}
