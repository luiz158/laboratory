package org.demoiselle.drails.commands;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.VelocityContext;
import org.demoiselle.drails.App;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.exceptions.ValidationException;
import org.demoiselle.drails.transform.VelocityTransform;
import org.demoiselle.drails.validations.CreateBusinessValidation;

public class CreateBusinessCommand implements Command {

	private File project;

	public CreateBusinessCommand(File project) {
		this.project = project;
	}

	@Override
	public void execute(String line) throws ValidationException {
		
		new CreateBusinessValidation(project, line).validate();
		
		String params[] = line.split(" ");
		String domainName = params[1].trim();
		
		try {
			
			VelocityContext context = new VelocityContext();
			
			String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[domainName.split("\\.").length - 1]);
			
			context.put("packageName", Config.getInstance(project).getPackageAppWithNameApp());
			context.put("pojo", nameCamelCase);
			context.put("idType", "Long");
			context.put("serialVersionUID", System.currentTimeMillis());
			
			String templateFile = "bc" + File.separator + "pojoBC.vm";
			
			File businessFile = new VelocityTransform(project).transform(context, templateFile, Config.getInstance(project).getPathBusiness(),  nameCamelCase, "BC.java");
            
            App.out.println("=================================================");
            App.out.println("Business " + businessFile + " criado com sucesso !");
            App.out.println("=================================================");
            
        } 
		catch (Exception ex) {
            Logger.getLogger(CreateBusinessCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

	}

}
