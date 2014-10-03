package org.demoiselle.drails.transform;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.demoiselle.drails.Config;

public class VelocityTransform {
	
	private File project;

	public VelocityTransform(File project){
		this.project = project;
		
	}
	
	public File transform(VelocityContext context, String templateFile, String targetPath, String domainName, String suffix) throws Exception{
		
		VelocityEngine ve = new VelocityEngine();
        Properties p = new Properties();
        p.setProperty("file.resource.loader.path", Config.getInstance(project).getPathProjectTemplates());
        p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
        ve.init(p);
        
        Template template = ve.getTemplate(templateFile);

        String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[0]);
        
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        String name = nameCamelCase + suffix;
        
        File file = new File(targetPath + name);
        
        FileWriter fw = new FileWriter(file);
        fw.write(writer.toString());
        fw.close();
        
        return file;
        
	}
	
	public String transformToString(VelocityContext context, String templateFile) throws Exception{
		
		VelocityEngine ve = new VelocityEngine();
        Properties p = new Properties();
        p.setProperty("file.resource.loader.path", Config.getInstance(project).getPathProjectTemplates());
        p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
        ve.init(p);
        
        Template template = ve.getTemplate(templateFile);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        return writer.toString();
        
	}

}
