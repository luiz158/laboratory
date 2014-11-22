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
import org.demoiselle.drails.validations.CreateDomainValidation;

public class CreateDomainCommand implements Command {
	
	private File project;
	
	public CreateDomainCommand(File project){
		this.project = project;
	}

	@Override
	public void execute(String line) throws ValidationException {
		
		new CreateDomainValidation(project, line).validate();
		
		String params[] = line.split(" ");
		String domainName = params[1].trim();
		
		try {
			
            String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[0]);
            
            VelocityContext context = new VelocityContext();
            context.put("packageName", Config.getInstance(project).getPackageAppWithNameApp());
            context.put("pojo", nameCamelCase);
            String templateFile = "pojo" + File.separator + "pojo.vm";
            
            File domainFile = new VelocityTransform(project).transform(context, templateFile, Config.getInstance(project).getPathDomain(), nameCamelCase, ".java");
            
            App.out.println("Domínio " + domainFile + " criado com sucesso !");
            
        } 
		catch (Exception ex) {
            Logger.getLogger(CreateDomainCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

		
	}

}
