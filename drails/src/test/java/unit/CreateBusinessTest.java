package unit;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.commands.CreateAppCommand;
import org.demoiselle.drails.commands.CreateBusinessCommand;
import org.demoiselle.drails.commands.CreateDomainCommand;
import org.demoiselle.drails.constants.CommandsConstant;
import org.demoiselle.drails.constants.ConfigConstant;
import org.demoiselle.drails.exceptions.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.InitConfigurationTest;

public class CreateBusinessTest {
	
	String nomeProjeto;
	File novoProjeto = null;
	File projectFolder = null;
	
	@Before
    public void setUp() {		
		new InitConfigurationTest();
        
        nomeProjeto = "br.gov.testeTDD";
        novoProjeto = FileUtils.getTempDirectory();
        new CreateAppCommand(novoProjeto).execute(CommandsConstant.CREATE_APP + " " + nomeProjeto + " 2.4.0");
        
        projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
        
        new CreateDomainCommand(projectFolder).execute(CommandsConstant.CREATE_DOMAIN + " cliente");
    }
    
    @After
    public void down(){
    	File folder = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto);
        FileUtils.deleteQuietly(folder);
    }
	
	@Test
	public void criarUmBusinessController(){
		
        new CreateBusinessCommand(projectFolder).execute(CommandsConstant.CREATE_BUSINESS + " br.gov.testetdd.domain.Cliente");
        
        String businessPath = new StringBuilder()
				.append(novoProjeto.getAbsolutePath())
				.append(File.separator)
				.append(Config.getInstance(novoProjeto).getNameApp())
				.append(File.separator)
				.append("src")
				.append(File.separator)
				.append("main")
				.append(File.separator)
				.append("java")
				.append(File.separator)	
				.append(Config.getInstance(novoProjeto).getPackageAppWithNameApp().replaceAll("\\.", File.separator))
				.append(File.separator)
				.append("business")
				.append(File.separator)
				.append("ClienteBC.java")
				.toString();
		
		assertTrue(new File(businessPath).exists());
		
	}
	
	@Test(expected = ValidationException.class)
	public void projetoDeveSerCriadoEmDrails() {
		
		File properties = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto + File.separator + ConfigConstant.APPLICATION_FILE_NAME);
        FileUtils.deleteQuietly(properties);
		
        CreateBusinessCommand command = new CreateBusinessCommand(novoProjeto);
		command.execute(CommandsConstant.CREATE_BUSINESS + " cliente");
		
	}
	
	@Test(expected = ValidationException.class)
	public void nomeDoDominioDeveSerInformado(){
		
        File projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
		
		new CreateBusinessCommand(projectFolder).execute(CommandsConstant.CREATE_BUSINESS);
		
	}
	
	@Test(expected = ValidationException.class)
	public void dominioInformadoDeveExistir(){
		File projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
		
		new CreateDomainCommand(projectFolder).execute(CommandsConstant.CREATE_DOMAIN + " empresa");
		
		new CreateBusinessCommand(projectFolder).execute(CommandsConstant.CREATE_BUSINESS + " carro");
		
	}
}


