package org.demoiselle.drails.commands;

import java.io.File;
import java.util.List;

import org.demoiselle.drails.completers.DomainCompleter;
import org.demoiselle.drails.exceptions.ValidationException;
import org.demoiselle.drails.validations.GenerateAllValidation;

public class GenerateAllCommand implements Command {

	private File project;

	public GenerateAllCommand(File project) {
		this.project = project;
	}

	@Override
	public void execute(String line) throws ValidationException {
		
		new GenerateAllValidation(project, line).validate();
		
		String params[] = line.split(" ");
		String domainName = params[1].trim();
		
		if("*".equals(domainName)){
			List<String> domains = DomainCompleter.list(project);
			
			for(String domain : domains){
				String lineAll = " " + domain;
				new CreateBusinessCommand(project).execute(lineAll);
				new CreatePersistenceCommand(project).execute(lineAll);
				new CreateViewCommand(project).execute(lineAll);
			}
			
		}
		else{
			new CreateBusinessCommand(project).execute(line);
			new CreatePersistenceCommand(project).execute(line);
			new CreateViewCommand(project).execute(line);
		}
	}

}
