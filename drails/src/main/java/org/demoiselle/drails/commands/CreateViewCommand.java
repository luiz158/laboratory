package org.demoiselle.drails.commands;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.VelocityContext;
import org.demoiselle.drails.App;
import org.demoiselle.drails.Config;
import org.demoiselle.drails.exceptions.ValidationException;
import org.demoiselle.drails.transform.VelocityTransform;
import org.demoiselle.drails.util.ParserUtil;
import org.demoiselle.drails.util.StringUtil;
import org.demoiselle.drails.validations.CreateViewValidation;

public class CreateViewCommand implements Command {

	private File project;

	public CreateViewCommand(File project) {
		this.project = project;
	}

	@Override
	public void execute(String line) throws ValidationException {
		new CreateViewValidation(project, line).validate();
		
		String params[] = line.split(" ");
		String domainName = params[1].trim();
		
		try {
			
			VelocityContext context = new VelocityContext();
			
			String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[domainName.split("\\.").length - 1]);
			
			context.put("packageName", Config.getInstance(project).getPackageAppWithNameApp());
			context.put("pojo", nameCamelCase);
			context.put("idType", "Long");
			context.put("beanLower", nameCamelCase.toLowerCase());
			context.put("body", generateBody(new File(Config.getInstance(project).getPathDomain() + File.separator + nameCamelCase + ".java"), nameCamelCase));
			
			String templateEditFile = "mb" + File.separator + "pojoEditMB.vm";
			String templateListFile = "mb" + File.separator + "pojoListMB.vm";
			
			File viewEditFile = new VelocityTransform(project).transform(context, templateEditFile, Config.getInstance(project).getPathView(),  nameCamelCase, "EditMB.java");
			File viewListFile = new VelocityTransform(project).transform(context, templateListFile, Config.getInstance(project).getPathView(),  nameCamelCase, "ListMB.java");
            
            App.out.println("=================================================");
            App.out.println("View " + viewEditFile + " criado com sucesso !");
            App.out.println("View " + viewListFile + " criado com sucesso !");
            App.out.println("=================================================");
            
        } 
		catch (Exception ex) {
            Logger.getLogger(CreateViewCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

	}

	private String generateBody(File domain, String nameCamelCase) throws Exception {
		
		StringBuilder result = new StringBuilder();
		
		Map<String, String> atributos = ParserUtil.getAttributesFromClassFile(domain);
		
		Iterator<Entry<String, String>> it = atributos.entrySet().iterator();
		
		while(it.hasNext()){
			
			Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
			
			String key = pairs.getKey();
			String value = pairs.getValue();
			
			List<String> varAnnotationsForField = ParserUtil.getAnnotationsForField(domain, key);
			
			for(String fieldAnnotated : varAnnotationsForField){
				
				if("OneToMany".equals(fieldAnnotated)){
					
					String attrClassOfValue = StringUtil.getClassNameOfListOf(value);
					String attrNameFirstUp = WordUtils.capitalize(key);
					String attrClassFirstLower =  StringUtil.lowerCaseFirstLetter(attrClassOfValue);
					
					VelocityContext context = new VelocityContext();
					context.put("attrClassOfValue", attrClassOfValue);
					context.put("attrNameFirstUp", attrNameFirstUp);
					context.put("attrClassFirstLower", attrClassFirstLower);
					
					String templateEditFile = "mb" + File.separator + "pojoEditMB_OneToMany.vm";
					
					result.append(new VelocityTransform(project).transformToString(context, templateEditFile));
					
				}
				else{
					if("ManyToOne".equals(fieldAnnotated) || "OneToOne".equals(fieldAnnotated)){
						
						String varAttrValueFirstLower = StringUtil.lowerCaseFirstLetter(value);
								
						VelocityContext context = new VelocityContext();
						context.put("varAttrValueFirstLower", varAttrValueFirstLower);
						context.put("attrValue", value);
						
						String templateEditFile = "mb" + File.separator + "pojoEditMB_ManyToOne_OneToOne.vm";
						
						result.append(new VelocityTransform(project).transformToString(context, templateEditFile));
						
					}
					else{
						if("ManyToMany".equals(fieldAnnotated)){
							
							String attrClassOfValue = StringUtil.getClassNameOfListOf(value);
							String attrNameFirstUp = WordUtils.capitalize(key);
							String attrClassFirstLower =  StringUtil.lowerCaseFirstLetter(attrClassOfValue);

						
							VelocityContext context = new VelocityContext();
							context.put("attrClassOfValue", attrClassOfValue);
							context.put("attrNameFirstUp", attrNameFirstUp);
							context.put("attrClassFirstLower", attrClassFirstLower);
							
							String templateEditFile = "mb" + File.separator + "pojoEditMB_ManyToMany.vm";
							
							result.append(new VelocityTransform(project).transformToString(context, templateEditFile));
							
						}
						else{
							if("Enumerated".equals(fieldAnnotated)){
								
								String attrNameFirstUp =  WordUtils.capitalize(key);
								
								VelocityContext context = new VelocityContext();
								context.put("attrNameFirstUp", attrNameFirstUp);
								context.put("beanLower", nameCamelCase.toLowerCase());

								String templateEditFile = "mb" + File.separator + "pojoEnumerated.vm";
								
								result.append(new VelocityTransform(project).transformToString(context, templateEditFile));
								
							}
						}
					}
				}
				
			}
			
			it.remove();
		}
		
		return result.toString();
	}

}
