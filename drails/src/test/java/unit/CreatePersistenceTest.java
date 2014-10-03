package unit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.commands.CreateAppCommand;
import org.demoiselle.drails.commands.CreateDomainCommand;
import org.demoiselle.drails.commands.CreatePersistenceCommand;
import org.demoiselle.drails.constants.CommandsConstant;
import org.demoiselle.drails.constants.ConfigConstant;
import org.demoiselle.drails.exceptions.ValidationException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.InitConfigurationTest;

public class CreatePersistenceTest {
	
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
	public void criarUmPersistenceController(){
		
        new CreatePersistenceCommand(projectFolder).execute(CommandsConstant.CREATE_PERSISTENCE + " br.gov.testetdd.domain.Cliente");
        
        String persistencePath = new StringBuilder()
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
				.append("persistence")
				.append(File.separator)
				.append("ClienteDAO.java")
				.toString();
		
		assertTrue(new File(persistencePath).exists());
		
	}
	
	@Test(expected = ValidationException.class)
	public void projetoDeveSerCriadoEmDrails() {
		
		File properties = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto + File.separator + ConfigConstant.APPLICATION_FILE_NAME);
        FileUtils.deleteQuietly(properties);
		
        CreatePersistenceCommand command = new CreatePersistenceCommand(novoProjeto);
		command.execute(CommandsConstant.CREATE_PERSISTENCE + " cliente");
		
	}
	
	@Test(expected = ValidationException.class)
	public void nomeDoDominioDeveSerInformado(){
		
        File projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
		
		new CreatePersistenceCommand(projectFolder).execute(CommandsConstant.CREATE_PERSISTENCE);
		
	}
	
	@Test
	public void persistenceXMLDeveConterAClasseDeDominio() throws JDOMException, IOException{
		
		new CreatePersistenceCommand(projectFolder).execute(CommandsConstant.CREATE_PERSISTENCE + " br.gov.testetdd.domain.Cliente");
		
		String persistenceXMLPath = new StringBuilder()
				.append(novoProjeto.getAbsolutePath())
				.append(File.separator)
				.append(Config.getInstance(novoProjeto).getNameApp())
				.append(File.separator)
				.append("src")
				.append(File.separator)
				.append("main")
				.append(File.separator)
				.append("resources")
				.append(File.separator)	
				.append("META-INF")
				.append(File.separator)
				.append("persistence.xml")
				.toString();
		
		assertTrue(new File(persistenceXMLPath).exists());
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(Config.getInstance(projectFolder).getPathPersistenceXML());
 
		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();
		
		Namespace nameSpace = Namespace.getNamespace("", "http://java.sun.com/xml/ns/persistence");
		List<Element> persistenceUnits = rootNode.getChildren("persistence-unit", nameSpace);
		for(Element persistenceUnit : persistenceUnits){
			
			String text = Config.getInstance(projectFolder).getPackageAppWithNameApp() + ".domain.Cliente";
			Boolean encontrado = Boolean.FALSE;
			
			for(Element classElement : persistenceUnit.getChildren("class", nameSpace)){
				if(classElement.getTextTrim().equals(text)){
					encontrado = Boolean.TRUE;
				}
			}
			
			assertTrue(encontrado);
			
		}
	}
	
	@Test
	public void persistenceXMLNaoPodeConterRepeticao() throws JDOMException, IOException{

		new CreatePersistenceCommand(projectFolder).execute(CommandsConstant.CREATE_PERSISTENCE + " br.gov.testetdd.domain.Cliente");
		new CreatePersistenceCommand(projectFolder).execute(CommandsConstant.CREATE_PERSISTENCE + " br.gov.testetdd.domain.Cliente");
		
		String persistenceXMLPath = new StringBuilder()
				.append(novoProjeto.getAbsolutePath())
				.append(File.separator)
				.append(Config.getInstance(novoProjeto).getNameApp())
				.append(File.separator)
				.append("src")
				.append(File.separator)
				.append("main")
				.append(File.separator)
				.append("resources")
				.append(File.separator)	
				.append("META-INF")
				.append(File.separator)
				.append("persistence.xml")
				.toString();
		
		assertTrue(new File(persistenceXMLPath).exists());
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(Config.getInstance(projectFolder).getPathPersistenceXML());
 
		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();
		
		Namespace nameSpace = Namespace.getNamespace("", "http://java.sun.com/xml/ns/persistence");
		List<Element> persistenceUnits = rootNode.getChildren("persistence-unit", nameSpace);
		for(Element persistenceUnit : persistenceUnits){
			
			String text = Config.getInstance(projectFolder).getPackageAppWithNameApp() + ".domain.Cliente";
			int cont = 0; 
			
			for(Element classElement : persistenceUnit.getChildren("class", nameSpace)){
				if(classElement.getTextTrim().equals(text)){
					cont++;
				}
			}
			
			assertEquals(1, cont);
			
		}
	}
	
	@Test(expected = ValidationException.class)
	public void dominioInformadoDeveExistir(){
		File projectFolder = new File(novoProjeto.getAbsolutePath() + File.separator + Config.getInstance(novoProjeto).getNameApp());
		
		new CreateDomainCommand(projectFolder).execute(CommandsConstant.CREATE_DOMAIN + " empresa");
		
		new CreatePersistenceCommand(projectFolder).execute(CommandsConstant.CREATE_PERSISTENCE + " carro");
		
	}
}


