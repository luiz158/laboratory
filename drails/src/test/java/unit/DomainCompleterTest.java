package unit;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.commands.CreateAppCommand;
import org.demoiselle.drails.commands.CreateDomainCommand;
import org.demoiselle.drails.completers.DomainCompleter;
import org.demoiselle.drails.constants.CommandsConstant;
import org.junit.Before;
import org.junit.Test;

import util.InitConfigurationTest;

public class DomainCompleterTest {
	
	@Before
	public void setUp(){
		new InitConfigurationTest();
	}
	
	@Test
	public void listarDominios(){
		
		//Cria novo projeto
		String nomeProjeto = "br.gov.testeTDD";
        File novoProjeto = FileUtils.getTempDirectory();
        
        File folder = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto);
        FileUtils.deleteQuietly(folder);
        
        
        new CreateAppCommand(novoProjeto).execute(CommandsConstant.CREATE_APP + " " + nomeProjeto + " 2.4.0");
        
        File projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
		
        String dominios[] = new String[]{"cliente", "endereco", "turma"};
        
        CreateDomainCommand command = new CreateDomainCommand(projectFolder);
		
        for(String dominio : dominios){
        	command.execute(CommandsConstant.CREATE_DOMAIN + " " + dominio);
        }
        
        List<String> list = DomainCompleter.list(projectFolder);
        
        for(String dominio : dominios){
        	String str = nomeProjeto.toLowerCase() + ".domain." + WordUtils.capitalize(dominio);
        	assertTrue(list.contains(str));
        }
        
		
	}

}
