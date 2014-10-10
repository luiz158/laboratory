package org.demoiselle.drails.util;

import java.util.List;

public class StringUtil {
	
	/**
	 *  Returns the Name of Class Parameter (N) for List<N> 
	 */
	public static String getClassNameOfListOf(String str) {
		
		int i = str.indexOf('<');
		int j = str.indexOf('>');
		
		return str.substring(i+1,j);
	}
	
	 /**
	 * Lowercases the first letter of a given string
	 */
	public static String lowerCaseFirstLetter(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	public static String hasOneInList(List<String> annotationsForAField, List<String> relationshipsAnnotations) {
		
		for (String element : annotationsForAField){
			if (relationshipsAnnotations.contains(element)){
				return element;
			}			
		}		
		
		return null;
	}

}
