package org.demoiselle.drails.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.VelocityContext;
import org.demoiselle.drails.App;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.exceptions.ValidationException;
import org.demoiselle.drails.transform.VelocityTransform;
import org.demoiselle.drails.validations.CreatePersistenceValidation;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class CreatePersistenceCommand implements Command {

	private File project;

	public CreatePersistenceCommand(File project) {
		this.project = project;
	}

	@Override
	public void execute(String line) throws ValidationException {
		
		new CreatePersistenceValidation(project, line).validate();
		
		String params[] = line.split(" ");
		String domainName = params[1].trim();
		
		try {
			
			VelocityContext context = new VelocityContext();
			
			String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[domainName.split("\\.").length - 1]);
			
			context.put("packageName", Config.getInstance(project).getPackageAppWithNameApp());
			context.put("pojo", nameCamelCase);
			context.put("idType", "Long");
			context.put("serialVersionUID", System.currentTimeMillis());
			
			String templateFile = "dao" + File.separator + "pojoDAO.vm";
			
			File persistenceFile = new VelocityTransform(project).transform(context, templateFile, Config.getInstance(project).getPathPersistence(),  nameCamelCase, "DAO.java");
			
			appendClassPersistenceXML(nameCamelCase);
            
            App.out.println("=================================================");
            App.out.println("Persistence " + persistenceFile + " criado com sucesso !");
            App.out.println("=================================================");
            
        } 
		catch (Exception ex) {
            Logger.getLogger(CreateBusinessCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

	}

	private void appendClassPersistenceXML(String domain) throws JDOMException, IOException {
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(Config.getInstance(project).getPathPersistenceXML());
 
		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();
		
		Namespace nameSpace = Namespace.getNamespace("", "http://java.sun.com/xml/ns/persistence");
		List<Element> persistenceUnits = rootNode.getChildren("persistence-unit", nameSpace);
		for(Element persistenceUnit : persistenceUnits){
			
			String text = Config.getInstance(project).getPackageAppWithNameApp() + ".domain." + domain;
			
			Boolean exists = Boolean.FALSE;
			List<Element> classDomainList = persistenceUnit.getChildren("class", nameSpace);
			
			for(Element classElement : classDomainList){
				if(classElement.getTextTrim().equals(text)){
					exists = Boolean.TRUE;
				}
			}
			
			if(!exists){
			
				Element domainClass = new Element("class");
				domainClass.setNamespace(nameSpace);
				domainClass.setText(text);
				persistenceUnit.addContent(classDomainList.size() + 1, domainClass);
				
			}
		}
 
		XMLOutputter xmlOutput = new XMLOutputter();
 
		xmlOutput.setFormat(Format.getPrettyFormat());
		
		xmlOutput.output(doc, new FileWriter(Config.getInstance(project).getPathPersistenceXML()));
		
	}

}
