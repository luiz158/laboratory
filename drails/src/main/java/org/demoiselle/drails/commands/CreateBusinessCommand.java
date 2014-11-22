package org.demoiselle.drails.commands;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.VelocityContext;
import org.demoiselle.drails.App;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.exceptions.ValidationException;
import org.demoiselle.drails.transform.VelocityTransform;
import org.demoiselle.drails.util.ParserUtil;
import org.demoiselle.drails.validations.CreateBusinessValidation;

public class CreateBusinessCommand implements Command {

	private File project;
	
	private Map<String, String> atributos;

	public CreateBusinessCommand(File project) {
		this.project = project;
	}

	@Override
	public void execute(String line) throws ValidationException {
		
		new CreateBusinessValidation(project, line).validate();
		
		String params[] = line.split(" ");
		String domainName = params[1].trim();
		
		try {
			
			String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[domainName.split("\\.").length - 1]);
			
			File domain = new File(Config.getInstance(project).getPathDomain() + File.separator + nameCamelCase + ".java");
			atributos = ParserUtil.getAttributesFromClassFile(domain);
			
			VelocityContext context = new VelocityContext();
			
			context.put("packageName", Config.getInstance(project).getPackageAppWithNameApp());
			context.put("pojo", nameCamelCase);
			context.put("idType", getDomainType(domain, "Id"));
			context.put("serialVersionUID", System.currentTimeMillis());
			
			String templateFile = "bc" + File.separator + "pojoBC.vm";
			
			File businessFile = new VelocityTransform(project).transform(context, templateFile, Config.getInstance(project).getPathBusiness(),  nameCamelCase, "BC.java");
            
            App.out.println("Business " + businessFile + " criado com sucesso !");
            
        } 
		catch (Exception ex) {
            Logger.getLogger(CreateBusinessCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

	}
	
	private String getDomainType(File domain, String annotation) {
		
		String result = "";
		
		for(Map.Entry<String, String> entry : atributos.entrySet()){
			
			String key = entry.getKey();
			String value = entry.getValue();
			
			List<String> annotationsForAField = ParserUtil.getAnnotationsForField(domain, key);
			if(annotationsForAField.contains(annotation)){
				result = value;
				break;
			}
			
		}
		
		return result;
	}

}
