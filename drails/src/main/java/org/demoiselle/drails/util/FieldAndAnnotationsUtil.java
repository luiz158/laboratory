package org.demoiselle.drails.util;

import java.util.List;

public class FieldAndAnnotationsUtil {
	
	private String fieldName;

	private List<String> fieldAnnotations;

	public FieldAndAnnotationsUtil(String fieldName, List<String> fieldAnnotations){
		this.fieldName = fieldName;
		this.fieldAnnotations = fieldAnnotations;
	}
	public FieldAndAnnotationsUtil(){
	}

	public String getFieldName(){
		return fieldName;
	}

	public void setFieldName(String varName){
		fieldName = varName;
	}

	public List<String> getFieldAnnotations(){
		return fieldAnnotations;
	}

	public void setFieldAnnotations(List<String> varAnnotations){
		fieldAnnotations = varAnnotations;
	}

}
