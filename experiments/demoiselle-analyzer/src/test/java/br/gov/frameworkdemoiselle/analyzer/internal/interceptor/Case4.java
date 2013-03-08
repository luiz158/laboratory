package br.gov.frameworkdemoiselle.analyzer.internal.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.analyzer.Analyze;

public class Case4 {

	private List<String> stringList = new ArrayList<String>();

	private Map<String, String> stringMap = new HashMap<String, String>();

	private List<Map<String, String>> stringMapList = new ArrayList<Map<String, String>>();

	public Case4() {
		stringList.add("element 1");
		stringList.add("element 2");
		stringList.add("element 3");
		stringList.add("element 4");
		stringList.add("element 5");

		stringMap.put("key1", "value1");
		stringMap.put("key2", "value2");
		stringMap.put("key3", "value3");
		stringMap.put("key4", "value4");
		stringMap.put("key5", "value5");
		
		stringMapList.add(stringMap);
		stringMapList.add(stringMap);
	}

	@Analyze
	public void go() {
	}
}
