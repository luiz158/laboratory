package unit;

import java.io.File;

import org.demoiselle.drails.exceptions.ValidationException;
import org.demoiselle.drails.validations.InitialValidations;
import org.junit.Before;
import org.junit.Test;

import util.InitConfigurationTest;

public class InitialValidationTest {
	
	private final File demoiselleFolder = new File("/opt/");
	
	@Before
	public void setUp(){
		new InitConfigurationTest();
	}
  
    @Test(expected = ValidationException.class)
    public void demoiselleDeveEstarInstalado(){
        
        InitialValidations validations = new InitialValidations(demoiselleFolder);
        validations.verifyDemoiselleInstallation();
        
    }
    
    @Test(expected = ValidationException.class)
    public void mavenDeveEstarInstalado(){
        
        InitialValidations validations = new InitialValidations(demoiselleFolder);
        validations.verifyMavenInstallation();
        
    }
  
    @Test(expected = ValidationException.class)
    public void demoiselleEMavenDevemEstarIntalado(){
        
        InitialValidations validation = new InitialValidations(demoiselleFolder);
        validation.validate();
        
    }
}
