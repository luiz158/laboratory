package org.demoiselle.drails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.demoiselle.drails.constants.ConfigConstant;

public class Config {

    private static Properties properties;
    
    private String demoisellePath;
    private String drailsPath;
    private String nameApp = "";
    private String version;
    private String packageApp;
    private File projectPath;
    
    private static Config instance;
    
    private Config(){
    	//TODO Create Path for windows
    	this.demoisellePath = File.separator + "opt" + File.separator + "demoiselle";
    	this.drailsPath = demoisellePath + File.separator + "tool" + File.separator + "drails" + File.separator; 
    }
    
    public static Config getInstance(File projectPath){
    	if(instance == null){
    		instance = new Config();
    	}
    	instance.setProjectPath(projectPath);
    	
    	return instance;
    }

    public void load() {

        properties = new Properties();
        FileInputStream fis = null;
        File file = new File(projectPath.getAbsolutePath() + File.separator + ConfigConstant.APPLICATION_FILE_NAME);
        
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                
                properties.load(fis);
                nameApp = properties.getProperty(ConfigConstant.APP_NAME);
                packageApp = properties.getProperty(ConfigConstant.APP_GROUP_ID);
                version = properties.getProperty(ConfigConstant.DEMOISELLE_VERSION);
                
            } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private String getProjectPath(){
    	
    	String path = new StringBuilder()
						.append(projectPath)
						.append(File.separator)
						.append("src")
						.append(File.separator)
						.append("main")
						.append(File.separator)
						.append("java")
						.append(File.separator)
						.append(getPackageAppWithNameApp().replaceAll("\\.", File.separator).toLowerCase())
						.append(File.separator)		
						.toString();
    	
    	return path;
    }
    
    public String getNameApp() {
		return nameApp;
	}

	public void setNameApp(String nameApp) {
		this.nameApp = nameApp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPackageApp() {
		return packageApp.isEmpty() ? nameApp.toLowerCase() : packageApp;
	}
	
	public String getPackageAppWithNameApp() {
		
		if(!packageApp.isEmpty() && packageApp.equalsIgnoreCase(nameApp)){
			return packageApp;
		}
		
		return packageApp.isEmpty() ? nameApp.toLowerCase() : packageApp + "." + nameApp.toLowerCase();
	}

	public void setPackageApp(String packageApp) {
		this.packageApp = packageApp;
	}

	public void setProjectPath(File projectPath) {
		this.projectPath = projectPath;
	}
    
	public String getPathDomain() {
        return new StringBuilder().append(getProjectPath()).append("domain").append(File.separator).toString();
    }
	
	public String getPathBusiness() {
        return new StringBuilder().append(getProjectPath()).append("business").append(File.separator).toString();
    }
	
	public String getPathDrailsTemplates() {
        return new StringBuilder().append(drailsPath).append(version).append(File.separator).append("templates").append(File.separator).toString();
    }
	
	public String getPathProjectTemplates() {
		return new StringBuilder().append(projectPath).append(File.separator).append("templates").append(File.separator).toString();
	}

	public String getPathPersistence() {
		return new StringBuilder().append(getProjectPath()).append("persistence").append(File.separator).toString();
	}
	
	public String getPathView() {
		return new StringBuilder().append(getProjectPath()).append("view").append(File.separator).toString();
	}

	public String getPathPersistenceXML() {
		
    	String path = new StringBuilder()
						.append(projectPath)
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
    	
    	
		return path;
	}

	public String getDemoisellePath() {
		return demoisellePath;
	}

	public String getPathViewRootXHTML() {
		
		String path = new StringBuilder()
				.append(projectPath)
				.append(File.separator)
				.append("src")
				.append(File.separator)
				.append("main")
				.append(File.separator)
				.append("webapp")
				.append(File.separator)		
				.toString();

		return path;
	}

	public String getPathMenuXHTML() {
		
		String viewPath = getPathViewRootXHTML();
		viewPath += "menu.xhtml";
		
		return viewPath;
	}

	public String getMessagePath() {

		String path = new StringBuilder()
				.append(projectPath)
				.append(File.separator)
				.append("src")
				.append(File.separator)
				.append("main")
				.append(File.separator)
				.append("resources")
				.append(File.separator)		
				.append("messages.properties")
				.append(File.separator)
				.toString();

		return path;
	}

	public String getPathConverters() {
		return getProjectPath() + File.separator + "converters" + File.separator;		
	}


}
