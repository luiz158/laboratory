/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unit;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.demoiselle.drails.commands.CreateAppCommand;
import org.demoiselle.drails.constants.CommandsConstant;
import org.demoiselle.drails.constants.ConfigConstant;
import org.demoiselle.drails.exceptions.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.InitConfigurationTest;

/**
 *
 * @author 05081364908
 */
public class CreateAppTest {
    
    String nomeProjeto;
    File novoProjeto = null;
    CreateAppCommand createApp;
    
    @Before
    public void setUp(){
    	new InitConfigurationTest();
        
        nomeProjeto = "testeTDD";
        novoProjeto = FileUtils.getTempDirectory();
        createApp = new CreateAppCommand(novoProjeto);
        
    }
    
    @After
    public void down() throws IOException{
    	File folder = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto);
        FileUtils.deleteDirectory(folder);
        createApp = null;
    }
    
    @Test
    public void novoProjetoDeveSerCriadoNaPastaCorrente() throws IOException, ValidationException{
        
        createApp.execute(CommandsConstant.CREATE_APP + " br.demoiselle." + nomeProjeto + " 2.4.0");
        
        File folder = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto);
                
        assertTrue(folder.isDirectory());
        
        String pomFile = folder.getAbsolutePath() + File.separator + "pom.xml";
        assertTrue(new File(pomFile).exists());
        
    }
    
    @Test
    public void novoProjetoPodeSerCriadoSemNomeDoPacote() throws ValidationException{
        createApp.execute(CommandsConstant.CREATE_APP + " " + nomeProjeto + " 2.4.0");
        File folder = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto);
        assertTrue(folder.isDirectory());
    }
    
    @Test(expected = ValidationException.class)
    public void novoProjetoDeveTerUmNome() throws ValidationException{
        createApp.execute(CommandsConstant.CREATE_APP + " 2.4.0");
    }
    
    @Test(expected = ValidationException.class)
    public void novoProjetoDeveTerAVersao() throws ValidationException{
        createApp.execute(CommandsConstant.CREATE_APP + " " + nomeProjeto);
    }
    
    @Test(expected = ValidationException.class)
    public void novoProjetoComVersaoSemTemplateNaoPodeSerCriada(){
    	createApp.execute(CommandsConstant.CREATE_APP + " " + nomeProjeto + " 2.3.0");
    }
    
    @Test
    public void novoProjetoDeveCriarUmArquivoProperties(){
    	
    	createApp.execute(CommandsConstant.CREATE_APP + " " + nomeProjeto + " 2.4.0");
    	File folder = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto);
    	File properties = new File(folder.getAbsolutePath() + File.separator + ConfigConstant.APPLICATION_FILE_NAME);
    	
    	assertTrue(properties.isFile());
    }
    
    @Test
    public void novoProjetoDeveCopiarTemplates(){
    	createApp.execute(CommandsConstant.CREATE_APP + " " + nomeProjeto + " 2.4.0");
    	File templateFolder = new File(novoProjeto.getAbsolutePath() + File.separator + nomeProjeto + File.separator + "templates");
    	assertTrue(templateFolder.isDirectory());
    	
    	File bcFolder = new File(templateFolder.getAbsolutePath() + File.separator + "bc");
    	File daoFolder = new File(templateFolder.getAbsolutePath() + File.separator + "dao");
    	File mbFolder = new File(templateFolder.getAbsolutePath() + File.separator + "mb");
    	File pojoFolder = new File(templateFolder.getAbsolutePath() + File.separator + "pojo");
    	File xhtmlFolder = new File(templateFolder.getAbsolutePath() + File.separator + "xhtml");
    	
    	assertTrue(bcFolder.isDirectory());
    	assertTrue(daoFolder.isDirectory());
    	assertTrue(mbFolder.isDirectory());
    	assertTrue(pojoFolder.isDirectory());
    	assertTrue(xhtmlFolder.isDirectory());
    }
}
