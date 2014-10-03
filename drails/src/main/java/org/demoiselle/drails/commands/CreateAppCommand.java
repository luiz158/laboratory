package org.demoiselle.drails.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
