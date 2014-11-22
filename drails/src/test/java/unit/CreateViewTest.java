package unit;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.commands.CreateAppCommand;
import org.demoiselle.drails.commands.CreateDomainCommand;
import org.demoiselle.drails.commands.CreateViewCommand;
import org.demoiselle.drails.constants.CommandsConstant;
import org.demoiselle.drails.constants.ConfigConstant;
import org.demoiselle.drails.exceptions.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.InitConfigurationTest;

public class CreateViewTest {
	
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
	public void criarUmViewController(){
		
        new CreateViewCommand(projectFolder).execute(CommandsConstant.CREATE_VIEW + " br.gov.testetdd.domain.Cliente");
        
        String viewPath = new StringBuilder()
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
				.append("view")
				.append(File.separator)				
				.toString();
		
		assertTrue(new File(viewPath + "ClienteEditMB.java").exists());
		assertTrue(new File(viewPath + "ClienteListMB.java").exists());
		
	}
	
	@Test(expected = ValidationException.class)
	public void projetoDeveSerCriadoEmDrails() {
		
		File properties = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto + File.separator + ConfigConstant.APPLICATION_FILE_NAME);
        FileUtils.deleteQuietly(properties);
		
        CreateViewCommand command = new CreateViewCommand(novoProjeto);
		command.execute(CommandsConstant.CREATE_VIEW + " cliente");
		
	}
	
	@Test(expected = ValidationException.class)
	public void nomeDoDominioDeveSerInformado(){
		
        File projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
		
		new CreateViewCommand(projectFolder).execute(CommandsConstant.CREATE_VIEW);
		
	}
	
	@Test(expected = ValidationException.class)
	public void dominioInformadoDeveExistir(){
		File projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
		
		new CreateDomainCommand(projectFolder).execute(CommandsConstant.CREATE_DOMAIN + " empresa");
		
		new CreateViewCommand(projectFolder).execute(CommandsConstant.CREATE_VIEW + " carro");
		
	}
}


