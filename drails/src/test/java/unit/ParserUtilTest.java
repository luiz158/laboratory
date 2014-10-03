package unit;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.demoiselle.drails.util.ParserUtil;
import org.junit.Test;

public class ParserUtilTest {
	
	
	@Test
	public void teste(){
		
		String path = new File("").getAbsolutePath() 
				+ File.separator
				+ "src"
				+ File.separator
				+ "test"
				+ File.separator
				+ "java"
				+ File.separator
				+ "unit"
				+ File.separator
				+ "classes"
				+ File.separator
				+ "Bookmark.java";
		
		File domain = new File(path);
		
		Map<String, String> atributos = ParserUtil.getAttributesFromClassFile(domain);
		
		Iterator<Entry<String, String>> it = atributos.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
			
			String key = pairs.getKey();
			String value = pairs.getValue();
			
			List<String> varAnnotationsForField = ParserUtil.getAnnotationsForField(domain, key);
			
			for(String fieldAnnotated : varAnnotationsForField){
				
				System.out.println(key + " = " + value + " >>> " + fieldAnnotated);
				
			}
			
			it.remove();
		}
		
	}
	
}
