package org.demoiselle.drails.validations;

import java.io.File;
import org.demoiselle.drails.exceptions.ValidationException;

public class InitialValidations implements Validation{
    
    private final File demoiselleFolder;
    
    public InitialValidations(File demoiselleFolder){
        this.demoiselleFolder = demoiselleFolder;
    }

    public void verifyDemoiselleInstallation() throws ValidationException {

        if (!"demoiselle".equals(demoiselleFolder.getName())) {
            throw new ValidationException("Você precisa instalar o demoiselle-infra: http://demoiselle.sourceforge.net/infra");
        }

    }

    public void verifyMavenInstallation() {
        File mavenDir = new File(demoiselleFolder + File.separator + "tool" + File.separator + "maven3" + File.separator + "bin");

        if (!mavenDir.exists()) {
            mavenDir = new File(demoiselleFolder + File.separator + "tool" + File.separator + "maven2" + File.separator + "bin");
            if (!mavenDir.exists()) {
                throw new ValidationException("Instale o demoiselle-maven");
            }
        }
    }

    @Override
    public void validate() throws ValidationException{
        verifyDemoiselleInstallation();
        verifyMavenInstallation();
    }


}
