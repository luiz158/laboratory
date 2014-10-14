package org.demoiselle.drails.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import jline.internal.Log;

import org.apache.commons.io.FileUtils;
import org.demoiselle.drails.App;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.constants.ConfigConstant;
import org.demoiselle.drails.exceptions.ValidationException;
import org.demoiselle.drails.validations.CreateAppValidation;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author 05081364908
 */
public class CreateAppCommand implements Command{

    private File project;

	public CreateAppCommand(File novoProjeto) {
		this.project = novoProjeto;
	}

	@Override
    public void execute(String line) throws ValidationException {
        
        new CreateAppValidation(line).validate();
        
        String[] params = line.split(" ");
        
        String projectName = params[1].trim();
        String demoiselleVersion = params[2].trim();
        
        String groupId = "";

        if(projectName.split("\\.").length > 1){
            groupId = projectName.substring(0, projectName.lastIndexOf("."));
            projectName = projectName.substring(projectName.lastIndexOf(".") + 1, projectName.length());
        }

        Config.getInstance(project).setPackageApp(groupId.toLowerCase());
        Config.getInstance(project).setNameApp(projectName);
        Config.getInstance(project).setVersion(demoiselleVersion);

        createNewApp(projectName, demoiselleVersion);
    }
    
    private void createNewApp(String projectName, String demoiselleVersion) {
        try {

        	//TODO Path for Windows
        	//TODO Get M2_HOME
            File mavenDir = new File("/opt/demoiselle/tool/maven3/bin/");

            if (!mavenDir.exists()) {
                mavenDir = new File("/opt/demoiselle/tool/maven2/bin/");
                if (!mavenDir.exists()) {
                    App.out.println("Instale o demoiselle-maven !!!!");
                    return;
                }
            }
            
            File appDir = new File(project.getAbsolutePath() + File.separator + projectName);
            
            Log.info("Create project : " + appDir);
            
            if (!appDir.exists()) {
                appDir.mkdir();       
                
                File rootDir = appDir.getParentFile();

                Process proc = null;
                ProcessBuilder pb = new ProcessBuilder(
                		mavenDir + File.separator + "mvn", 
                		"-DarchetypeGroupId=br.gov.frameworkdemoiselle.archetypes",
                		"-DarchetypeArtifactId=demoiselle-jsf-jpa",
                		"-DarchetypeVersion=" + Config.getInstance(project).getVersion(),
                		"-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots",
                		"-DgroupId=" + Config.getInstance(project).getPackageApp(),
                		"-DartifactId=" + Config.getInstance(project).getNameApp(),
                		"-Dversion=1.0.0", 
                		"-Dpackage=" + Config.getInstance(project).getPackageAppWithNameApp(),
                		"-Dbasedir=" + rootDir.getAbsolutePath(),
                		"-Darchetype.interactive=false",
                		"--batch-mode",
                		"archetype:generate");
                
                pb = pb.directory(rootDir);
                
                proc = pb.start();
                		
                InputStream is = proc.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader buff = new BufferedReader(isr);

                String line;
                while((line = buff.readLine()) != null){
                    App.out.println(line);
                    App.out.flush();
                    App.reader.redrawLine();
                }
                
                generateProperties(projectName, demoiselleVersion);
                copyTemplates(projectName);
                applyBootstrapTheme(projectName);
                
                App.out.println("=================================================");
                App.out.println("Projeto " + Config.getInstance(project).getNameApp() + " criado com sucesso !");
                App.out.println("=================================================");
                
            } 
            else {
                App.out.println("=================================================");
                App.out.println("O Projeto " + Config.getInstance(project).getNameApp() + " já existe");
                App.out.println("=================================================");
            }

        } 
        catch (IOException ex) {
            Logger.getLogger(CreateAppCommand.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch(JDOMException e){
        	Logger.getLogger(CreateAppCommand.class.getName()).log(Level.SEVERE, null, e);
        }

    }

	private void applyBootstrapTheme(String projectName) throws JDOMException, IOException {
			
		applyBootstrapThemePOM(projectName);
		applyBootstrapThemeWEBXML(projectName);
		
	}

	private void applyBootstrapThemeWEBXML(String projectName) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		String path = project.getAbsolutePath() + File.separator + projectName + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "web.xml";
		File xmlFile = new File(path);
 
		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();
		
		Namespace nameSpace = Namespace.getNamespace("", "http://java.sun.com/xml/ns/javaee");
		
		Element contextParam = new Element("context-param");
		contextParam.setNamespace(nameSpace);
		
		contextParam.addContent(new Element("param-name", nameSpace).setText("primefaces.THEME"));
		contextParam.addContent(new Element("param-value", nameSpace).setText("bootstrap"));
		
		
		rootNode.addContent(contextParam);
 
		XMLOutputter xmlOutput = new XMLOutputter();
 
		xmlOutput.setFormat(Format.getPrettyFormat());
		
		xmlOutput.output(doc, new FileWriter(path));
		
	}

	private void applyBootstrapThemePOM(String projectName)
			throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		String path = project.getAbsolutePath() + File.separator + projectName + File.separator + "pom.xml";
		File xmlFile = new File(path);
 
		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();
		
		Namespace nameSpace = Namespace.getNamespace("", "http://maven.apache.org/POM/4.0.0");
		Element dependencies = rootNode.getChild("dependencies", nameSpace);
		Element theme = new Element("dependency");
		theme.setNamespace(nameSpace);
		
		theme.addContent(new Element("groupId", nameSpace).setText("org.primefaces.themes"));
		theme.addContent(new Element("artifactId", nameSpace).setText("bootstrap"));
		theme.addContent(new Element("version", nameSpace).setText("1.0.10"));
		
		dependencies.addContent(theme);
 
		XMLOutputter xmlOutput = new XMLOutputter();
 
		xmlOutput.setFormat(Format.getPrettyFormat());
		
		xmlOutput.output(doc, new FileWriter(path));
	}

	private void copyTemplates(String projectName) throws IOException {
		
		File sourceDir = new File(Config.getInstance(project).getPathDrailsTemplates());
		File destDir = new File(project.getAbsolutePath() + File.separator + projectName + File.separator + "templates");
		FileUtils.copyDirectory(sourceDir, destDir);
		
	}

	private void generateProperties(String projectName, String demoiselleVersion) throws FileNotFoundException, IOException {
		
		Properties prop = new Properties();
		prop.put(ConfigConstant.APP_NAME, Config.getInstance(project).getNameApp());
		prop.put(ConfigConstant.DEMOISELLE_VERSION, Config.getInstance(project).getVersion());
		prop.put(ConfigConstant.APP_GROUP_ID, Config.getInstance(project).getPackageApp());
		
		prop.store(new FileOutputStream(new File(project.getAbsolutePath() + File.separator + projectName + File.separator + ConfigConstant.APPLICATION_FILE_NAME)), "");
		
		
	}
    
}
