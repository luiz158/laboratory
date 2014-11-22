package org.demoiselle.drails.validations;

import java.io.File;

import org.demoiselle.drails.Config;
import org.demoiselle.drails.constants.CommandsConstant;
import org.demoiselle.drails.exceptions.ValidationException;
import org.demoiselle.drails.util.CommonsValidations;

public class CreateAppValidation implements Validation{
    
    private final String line;
    
    public CreateAppValidation(String line){
        this.line = line;
    }

    @Override
    public void validate() throws ValidationException {
        
         String[] params = line.split(" ");
        
        if(params.length != 3){
            throw new ValidationException("Parâmetros invalidos, utilize: " + CommandsConstant.CREATE_APP + " <pacote, nome projeto> <versão demoiselle>");
        }
        
        String projectName = params[1].trim();
        String demoiselleVersion = params[2].trim();
        
        if(projectName.isEmpty() || demoiselleVersion.isEmpty()){
            throw new ValidationException("Parâmetros invalidos, utilize: " + CommandsConstant.CREATE_APP + " <pacote, nome projeto> <versão demoiselle>");
        }
        
        Config.getInstance(new File("").getAbsoluteFile()).setVersion(demoiselleVersion);
        
        if(!CommonsValidations.verifyIfTemplateVersionExists(new File("").getAbsoluteFile())){
        	throw new ValidationException("Não foi possível localizar os templates para a Versão do Demoiselle '" + demoiselleVersion + "'!");
        }
        
    }
    
}
