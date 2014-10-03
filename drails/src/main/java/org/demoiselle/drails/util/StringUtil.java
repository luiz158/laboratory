package org.demoiselle.drails.util;

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

}
