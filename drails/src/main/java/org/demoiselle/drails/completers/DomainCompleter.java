package org.demoiselle.drails.completers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.demoiselle.drails.Config;

public class DomainCompleter {

	public static List<String> list(File project) {
		
		if(!Config.getInstance(project).getNameApp().isEmpty()){
		
			String pathDomain = Config.getInstance(project).getPathDomain();
			
			List<File> domains = (List<File>) FileUtils.listFiles(new File(pathDomain), new String[]{"java"}, true);
			
			List<String> files = new ArrayList<>();
			
			for(File file : domains){
				String replace = project.getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
				String name = file.getAbsolutePath().replaceAll(replace, "").replaceAll(File.separator, ".").replaceAll(".java", "");
				files.add(name);
			}
			
			return files;
		}
		
		return new ArrayList<String>();
	}

}
