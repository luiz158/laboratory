package org.demoiselle.drails.util;

import java.io.File;

import org.apache.commons.lang3.text.WordUtils;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.constants.ConfigConstant;
import org.demoiselle.drails.exceptions.ValidationException;

public class CommonsValidations {
	
	public static boolean verifyIfDrailsProject(File project){
	
		File properties = new File(project + File.separator + ConfigConstant.APPLICATION_FILE_NAME);
		if(!properties.isFile()){
			return false;
		}
		
		return true;
	}
	
	
	public static boolean verifyIfDomainExists(File project, String domainName) throws ValidationException{
		
		String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[domainName.split("\\.").length - 1]);
		
		File domainFile = new File(Config.getInstance(project).getPathDomain() + nameCamelCase + ".java");
		
		if(domainFile.exists()){
			return true;
		}
		
		return false;
	}


	public static boolean verifyIfTemplateVersionExists(File project) {
		
		File templateFolder = new File(Config.getInstance(project).getPathDrailsTemplates());
		
		if(templateFolder.exists() && templateFolder.isDirectory()){
			return true;
		}
		
		return false;
	}

}
