package unit;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.commands.CreateAppCommand;
import org.demoiselle.drails.commands.CreateDomainCommand;
import org.demoiselle.drails.constants.CommandsConstant;
import org.demoiselle.drails.constants.ConfigConstant;
import org.demoiselle.drails.exceptions.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.InitConfigurationTest;

public class CreateDomainTest {
	
	String nomeProjeto;
	File novoProjeto = null;
	
	@Before
    public void setUp(){
		
		new InitConfigurationTest();
		
        nomeProjeto = "br.gov.testeTDD";
        novoProjeto = FileUtils.getTempDirectory();
        new CreateAppCommand(novoProjeto).execute(CommandsConstant.CREATE_APP + " " + nomeProjeto + " 2.4.0");
    }
    
    @After
    public void down(){
    	File folder = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto);
        FileUtils.deleteQuietly(folder);
    }
	
	
	@Test
	public void criarUmDominio(){
		
        File projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
		
		CreateDomainCommand command = new CreateDomainCommand(projectFolder);
		command.execute(CommandsConstant.CREATE_APP + " cliente");
		
		String domainPath = new StringBuilder()
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
				.append("domain")
				.append(File.separator)
				.append("Cliente.java")
				.toString();
		
		assertTrue(new File(domainPath).exists());
	}
	
	@Test(expected = ValidationException.class)
	public void projetoDeveSerCriadoEmDrails() {
		
		File properties = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto + File.separator + ConfigConstant.APPLICATION_FILE_NAME);
        FileUtils.deleteQuietly(properties);
		
		CreateDomainCommand command = new CreateDomainCommand(novoProjeto);
		command.execute(CommandsConstant.CREATE_DOMAIN + " cliente");
		
	}
	
	@Test(expected = ValidationException.class)
	public void nomeDoDominioDeveSerInformado(){
		
        File projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
		
		new CreateDomainCommand(projectFolder).execute(CommandsConstant.CREATE_DOMAIN);
		
	}

}
