package br.gov.frameworkdemoiselle.prodepa.queryfilter.util;

import static org.junit.Assert.*;

import org.junit.Test;


public class StringUtilTest {

	String rootAlias = "p";
	
	@Test
	public void testCastToAliasName() {
		
		//select p from Person p join p.dogs d
		
		String join = "dogs";

		assertEquals(join, "p.dogs d");
	}

	@Test
	public void testCastToParamName() {
		
		//select p from Person p join p.car ca join ca.color co join co.manufacturer nanu
		//select p from Person p join p.car P1 join P1.color P2 join P2.manufacturer P3
		
		StringBuilder b = new StringBuilder(); 
	
		
		String join = "car.color.manufacturer";
		
		
		//car = P1
		//car.color = P2
		//car.color.manufacturer = P3
		
		String[] paths = join.split("\\.");
		
		StringBuilder tmp = new StringBuilder();
		int i = 1;
		for (String j : paths) {
			
			b.append("join " + rootAlias);
			if(i > 1) {
				b.append(i-1);
				tmp.append(".");
			}
			
			b.append("."+ j + " p" + (i) + " ");
			
			tmp.append(j);
			
			i++;
			//System.out.println(b.toString());
			System.out.println(tmp.toString());
		}
		
		//p.car.color.manufacturer m
		
	}

}
