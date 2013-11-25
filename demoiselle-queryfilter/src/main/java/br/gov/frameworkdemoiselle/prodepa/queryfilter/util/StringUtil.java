package br.gov.frameworkdemoiselle.prodepa.queryfilter.util;


public class StringUtil {

	public static String castToAliasName(String name) {
		return "_"+name.toLowerCase().replace(".","_");
	}
	
	public static String castToParamName(String name) {
		return name.replace(".","_");
	}
}
