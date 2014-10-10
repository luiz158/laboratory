package org.demoiselle.drails.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.ProcessingInstruction;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class CreateViewCommand implements Command {

	private File project;
	private Map<String, String> relationshipsFields;
	private List<String> relationshipsAnnotations;

	public CreateViewCommand(File project) {
		this.project = project;
	}

	@Override
	public void execute(String line) throws ValidationException {
		new CreateViewValidation(project, line).validate();
		
		String params[] = line.split(" ");
		String domainName = params[1].trim();
		
		relationshipsFields = new HashMap<>();
		
		relationshipsAnnotations = new ArrayList<>();
		relationshipsAnnotations.add("ManyToMany");
		relationshipsAnnotations.add("ManyToOne");
		relationshipsAnnotations.add("OneToMany");
		relationshipsAnnotations.add("OneToOne");
		
		App.out.println("=================================================");
		generateMB(domainName);
		generateXHTML(domainName);
		addDomainToMenu(domainName);
		App.out.println("=================================================");

	}

	private void addDomainToMenu(String domainName) {
		
		try{
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(Config.getInstance(project).getPathMenuXHTML());
	 
			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			
			Namespace nameSpaceH = Namespace.getNamespace("h", "http://java.sun.com/jsf/html");
			Namespace nameSpaceP = Namespace.getNamespace("p", "http://primefaces.org/ui");
			
			Element form = rootNode.getChild("form", nameSpaceH);
			Element menuBar = form.getChild("menubar", nameSpaceP);
			List<Element> subMenus = menuBar.getChildren("submenu", nameSpaceP);
			
			String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[domainName.split("\\.").length - 1]);
			
			Iterator<Element> it = subMenus.iterator();
			
			Boolean exists = Boolean.FALSE;
			
			while(it.hasNext()){
				
				Element subMenu = it.next();
				
				List<Element> menuItemList = subMenu.getChildren("menuitem", nameSpaceP);
				
				for(Element menuItem : menuItemList){
					if(menuItem.getAttribute("url").getValue().contains(nameCamelCase.toLowerCase() + "_edit.jsf") 
							|| menuItem.getAttribute("url").getValue().contains(nameCamelCase.toLowerCase() + "_list.jsf")){
						exists = Boolean.TRUE;
					}
				}
			}
			
			if(!exists){
				
				VelocityContext context = new VelocityContext();
				
				context.put("beanLower", nameCamelCase.toLowerCase());
				context.put("idName", "id");
				
				String templateFile = "xhtml" + File.separator + "menu.xhtml.fragment.vm";
				
				menuBar.addContent(new ProcessingInstruction(javax.xml.transform.Result.PI_DISABLE_OUTPUT_ESCAPING));
				menuBar.addContent(new VelocityTransform(project).transformToString(context, templateFile));
				menuBar.addContent(new ProcessingInstruction(javax.xml.transform.Result.PI_ENABLE_OUTPUT_ESCAPING));
				
			}
	 
			XMLOutputter xmlOutput = new XMLOutputter();
	 
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(Config.getInstance(project).getPathMenuXHTML()));
		}
		catch(Exception e){
			 Logger.getLogger(CreateViewCommand.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}

	private void generateXHTML(String domainName) {
		
		generateEditXHTML(domainName);
		generateListXHTML(domainName);
		
	}
	
	private void generateListXHTML(String domainName) {
		
		try {
			
			VelocityContext context = new VelocityContext();
			
			String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[domainName.split("\\.").length - 1]);
			
			context.put("beanLower", nameCamelCase.toLowerCase());
			context.put("idName", "id");
			context.put("body", generateBodyListXHTML(new File(Config.getInstance(project).getPathDomain() + File.separator + nameCamelCase + ".java"), nameCamelCase));
			
			String templateEditFile = "xhtml" + File.separator + "pojoListXHTML.vm";
			
			addMessage(nameCamelCase.toLowerCase() + ".list.table.title", nameCamelCase.toLowerCase());
			
			File viewListFile = new VelocityTransform(project).transform(context, templateEditFile, Config.getInstance(project).getPathViewRootXHTML(), nameCamelCase.toLowerCase(), "_list.xhtml");
            
            App.out.println("View " + viewListFile + " criado com sucesso !");
            
            
        } 
		catch (Exception ex) {
            Logger.getLogger(CreateViewCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
	}

	private String generateBodyListXHTML(File domain, String nameCamelCase) throws Exception {
		StringBuilder result = new StringBuilder();
		
		Map<String, String> atributos = ParserUtil.getAttributesFromClassFile(domain);
		
		VelocityContext context = new VelocityContext();
		
		context.put("beanLower", nameCamelCase.toLowerCase());
		context.put("idName", "id");
		
		String templateListFile = "";
		
		if(!atributos.isEmpty()){
			Iterator<Entry<String, String>> it = atributos.entrySet().iterator();
			
			while(it.hasNext()){
				Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
				
				String attrName = pairs.getKey();
				
				List<String> annotationsForAField = ParserUtil.getAnnotationsForField(domain, attrName);
				String hasRelationship = StringUtil.hasOneInList(annotationsForAField, relationshipsAnnotations);
				
				if(hasRelationship == null){
					String attrLow = attrName.substring(0,1).toLowerCase() + attrName.substring(1);
					
					context.put("attrLow", attrLow);
					
					if(ParserUtil.hasAnnotationForField(domain, attrName, "Id")) {	
						templateListFile = "xhtml" + File.separator + "pojoListXHTML_Id.vm";
						addMessage(nameCamelCase.toLowerCase() + ".label." + attrLow, attrLow);
					}
					else{
						templateListFile = "xhtml" + File.separator + "pojoListXHTML_None.vm";
						addMessage(nameCamelCase.toLowerCase() + ".label." + attrLow, attrLow);
					}
				}
			}
		}
		else{
			templateListFile = "xhtml" + File.separator + "pojoListXHTMLRelationShips.vm";
			
			addMessage(nameCamelCase.toLowerCase() + ".label.id", "Id");
			addMessage(nameCamelCase.toLowerCase() + ".label.text", "Text");
		}
		
		result.append(new VelocityTransform(project).transformToString(context, templateListFile));
		
		
		return result.toString();
	}

	private void generateEditXHTML(String domainName) {
		
		try {
			
			VelocityContext context = new VelocityContext();
			
			String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[domainName.split("\\.").length - 1]);
			
			context.put("beanLower", nameCamelCase.toLowerCase());
			context.put("body", generateBodyEditXHTML(new File(Config.getInstance(project).getPathDomain() + File.separator + nameCamelCase + ".java"), nameCamelCase));
			
			if(relationshipsFields != null && relationshipsFields.size() > 0){
				context.put("bodyRelationships", generateBodyEditRelationShipsXHTML(new File(Config.getInstance(project).getPathDomain() + File.separator + nameCamelCase + ".java"), nameCamelCase));
			}
			else{
				context.put("bodyRelationships", "");
			}
			
			String templateEditFile = "xhtml" + File.separator + "pojoEditXHTML.vm";
			
			File viewEditFile = new VelocityTransform(project).transform(context, templateEditFile, Config.getInstance(project).getPathViewRootXHTML(), nameCamelCase.toLowerCase(), "_edit.xhtml");
            
            App.out.println("View " + viewEditFile + " criado com sucesso !");
            
            
        } 
		catch (Exception ex) {
            Logger.getLogger(CreateViewCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
	}

	private String generateBodyEditRelationShipsXHTML(File domain, String nameCamelCase) throws Exception{
		
		StringBuilder result = new StringBuilder();
		
		Iterator<Entry<String, String>> it = relationshipsFields.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
			
			String varField = pairs.getKey();
			String varRelationship = pairs.getValue();
			
			if("OneToMany".equals(varRelationship)){
				
				String varListOfClassValue = ParserUtil.getFieldValue(domain, varField);
				String attrClassOfValue = StringUtil.getClassNameOfListOf(varListOfClassValue);
				String varBeanOnetoMany = attrClassOfValue + ".java";
				String attrClassOfValueLower = StringUtil.lowerCaseFirstLetter(attrClassOfValue);
				
				File varFileOneToMany = new File(domain.getParentFile().getAbsolutePath() + File.separator + varBeanOnetoMany);
				Map<String, String> varAttrListOneToMany = ParserUtil.getAttributesFromClassFile(varFileOneToMany);
				
				VelocityContext context = new VelocityContext();
				context.put("attrClassOfValueLower", attrClassOfValueLower);
				context.put("beanLower", nameCamelCase.toLowerCase());
				context.put("attrClassOfValue", attrClassOfValue);
				
				String templateEditFile = "";
				
				Iterator<Entry<String, String>> itListOneToMany = varAttrListOneToMany.entrySet().iterator();
				
				StringBuilder columns = new StringBuilder();
				
				while(itListOneToMany.hasNext()){
					Map.Entry<String, String> pairsOneToMany = (Map.Entry<String, String>) itListOneToMany.next();
					
					String varAttrName = pairsOneToMany.getKey();
					String varAttrValue = pairsOneToMany.getValue();
					
					List<String> annotationsForAField = ParserUtil.getAnnotationsForField(varFileOneToMany, varAttrName);
					String hasRelationship = StringUtil.hasOneInList(annotationsForAField, relationshipsAnnotations);
					
					if (hasRelationship == null){
						
						StringBuilder columnsInner = new StringBuilder();
						
						context.put("varAttrName", varAttrName);
						
						String templateEditFileColumns = "";
						
						if (ParserUtil.hasAnnotationForField(varFileOneToMany, varAttrName, "GeneratedValue")) {
							templateEditFileColumns = "xhtml" + File.separator + "pojoEditXHTMLRelationShips_None_GeneratedValue.vm";
						}
						else{
							if("Date".equalsIgnoreCase(varAttrValue)){ 
								templateEditFileColumns = "xhtml" + File.separator + "pojoEditXHTMLRelationShips_None_Date.vm";
								addMessage(attrClassOfValueLower + ".alt." + varAttrName, varAttrName);
							}
							else{
								if(ParserUtil.hasAnnotationForField(varFileOneToMany, varAttrName, "Enumerated")) {
									templateEditFileColumns = "xhtml" + File.separator + "pojoEditXHTMLRelationShips_None_Enumerated.vm";
								}
								else{
									templateEditFileColumns = "xhtml" + File.separator + "pojoEditXHTMLRelationShips_None_Other.vm";
									addMessage(attrClassOfValueLower + ".alt." + varAttrName, varAttrName);
								}
							}
						}
						
						columnsInner.append(new VelocityTransform(project).transformToString(context, templateEditFileColumns));
						
						templateEditFile = "xhtml" + File.separator + "pojoEditXHTMLRelationShips_None.vm";
						
						addMessage(attrClassOfValueLower + ".label." + varAttrName, varAttrName);
						
						context.put("columnInner", columnsInner.toString());
						
						columns.append(new VelocityTransform(project).transformToString(context, templateEditFileColumns));
						
					}
					
				}
				
				templateEditFile = "xhtml" + File.separator + "pojoEditXHTMLRelationShips_OneToMany.vm";
				
				addMessage(attrClassOfValueLower + ".label", attrClassOfValueLower);
				
				context.put("columns", columns.toString());
				
				result.append(new VelocityTransform(project).transformToString(context, templateEditFile));
				
			}
			else{
				if("ManyToMany".equals(varRelationship)){
					
					String varListOfClassValue = ParserUtil.getFieldValue(domain, varField);
					String attrClassOfValue = StringUtil.getClassNameOfListOf(varListOfClassValue);
					String varBeanManytoMany = attrClassOfValue + ".java";
					String attrClassOfValueLower = StringUtil.lowerCaseFirstLetter(attrClassOfValue);
					File varFileManyToMany = new File(domain.getParentFile().getAbsolutePath() + File.separator + varBeanManytoMany);
					String varId = ParserUtil.getFieldAnnotatedWith(varFileManyToMany, "Id");
					
					VelocityContext context = new VelocityContext();
					context.put("attrClassOfValueLower", attrClassOfValueLower);
					context.put("beanLower", nameCamelCase.toLowerCase());
					context.put("attrClassOfValue", attrClassOfValue);
					context.put("varId", varId);
					
					String templateEditFile = "xhtml" + File.separator + "pojoEditXHTMLRelationShips_ManyToMany.vm";
					
					addMessage(attrClassOfValueLower + ".label", attrClassOfValueLower);
					addMessage(nameCamelCase.toLowerCase() + ".label", nameCamelCase.toLowerCase());
					
					result.append(new VelocityTransform(project).transformToString(context, templateEditFile));
					
					generateConversor(domain, attrClassOfValue);
				}
			}
			
			it.remove();
		}
		
		
		return result.toString();
	}

	private void generateConversor(File domain, String conversorName) {
		
	/*	Criar pasta converters caso não exista
		Gerar Template
		
		
		packageName
		pojo
		idNameUpper
		idType
		beanLower*/
		
		
		
	}

	private String generateBodyEditXHTML(File domain, String nameCamelCase) throws Exception {
		
		StringBuilder result = new StringBuilder();
		
		Map<String, String> atributos = ParserUtil.getAttributesFromClassFile(domain);
		
		if(atributos.isEmpty()){
			
			VelocityContext context = new VelocityContext();
			context.put("beanLower", nameCamelCase.toLowerCase());
			
			String templateEditFile = "xhtml" + File.separator + "pojoEditXHTML_Empty.vm";
			
			result.append(new VelocityTransform(project).transformToString(context, templateEditFile));
			
			addMessage(nameCamelCase.toLowerCase() + ".label.id", "Id");
			addMessage(nameCamelCase.toLowerCase() + ".label.text", "Text"); 
			addMessage(nameCamelCase.toLowerCase() + ".alt.text", "Text");
			
		}
		else{
			
			Iterator<Entry<String, String>> it = atributos.entrySet().iterator();
			
			while(it.hasNext()){
				Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
				
				String key = pairs.getKey();
				String value = pairs.getValue();
				
				List<String> annotationsForAField = ParserUtil.getAnnotationsForField(domain, key);
				String varRelationship = StringUtil.hasOneInList(annotationsForAField, relationshipsAnnotations);
				
				if(!"serialVersionUID".equalsIgnoreCase(key)){
				
					if (varRelationship != null && !varRelationship.isEmpty() ){
						
						
						if("ManyToOne".equals(varRelationship) || "OneToOne".equals(varRelationship)) {
							
							File varFileBeanAnnotated = domain;
							String varId = ParserUtil.getFieldAnnotatedWith(varFileBeanAnnotated, "Id");
									
							VelocityContext context = new VelocityContext();
							context.put("attrName", key);
							context.put("attrValue", value);
							context.put("beanLower", nameCamelCase.toLowerCase());
							context.put("varId", varId);
							
							String templateEditFile = "xhtml" + File.separator + "pojoEditXHTML_ManyToOne_OneToOne.vm";
							
							result.append(new VelocityTransform(project).transformToString(context, templateEditFile));
							
							addMessage(key + ".label", key);
								
						}
						else{
							relationshipsFields.put(key.toString(),  varRelationship);
						}
						 
					}
					else{
						
						VelocityContext context = new VelocityContext();
						context.put("attrName", key);
						context.put("attrValue", value);
						context.put("beanLower", nameCamelCase.toLowerCase());
						
						String templateEditFile = "";
						
						if (ParserUtil.hasAnnotationForField(domain, key, "GeneratedValue")) {
							templateEditFile = "xhtml" + File.separator + "pojoEditXHTML_GeneratedValue.vm";
						}
						else{
							
							if ("Date".equals(value)){
								templateEditFile = "xhtml" + File.separator + "pojoEditXHTML_Date.vm";
								addMessage(nameCamelCase.toLowerCase() + ".alt." + key, key);
							}
							else{
								if (ParserUtil.hasAnnotationForField(domain, key, "Enumerated")) {
									templateEditFile = "xhtml" + File.separator + "pojoEditXHTML_Enumerated.vm";
								}
								else{
									templateEditFile = "xhtml" + File.separator + "pojoEditXHTML_Input.vm";
									addMessage(nameCamelCase.toLowerCase() + ".alt." + key, key);
								}
							}
							
						}
						context.put("body", new VelocityTransform(project).transformToString(context, templateEditFile));
						
						templateEditFile = "xhtml" + File.separator + "pojoEditXHTML_One.vm";
						
						result.append(new VelocityTransform(project).transformToString(context, templateEditFile));
						
					}
				}
				
				it.remove();
			}
		}
		
		
		return result.toString();
	}

	private void addMessage(String chave, String valor) {
		
		
        File file = new File(Config.getInstance(project).getMessagePath());
        
        if (file.exists()) {
            try {
            	
            	Properties properties = new Properties();
            	properties.load(new FileInputStream(file));
            	
            	FileOutputStream fileOut = new FileOutputStream(file);
            	
        		properties.setProperty(chave, valor);
        		properties.store(fileOut, "");
            	
            	fileOut.close();
                
            } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

		
		
	}

	private void generateMB(String domainName) {
		try {
			
			VelocityContext context = new VelocityContext();
			
			String nameCamelCase = WordUtils.capitalize(domainName.split("\\.")[domainName.split("\\.").length - 1]);
			
			context.put("packageName", Config.getInstance(project).getPackageAppWithNameApp());
			context.put("pojo", nameCamelCase);
			context.put("idType", "Long");
			context.put("beanLower", nameCamelCase.toLowerCase());
			context.put("body", generateBodyMB(new File(Config.getInstance(project).getPathDomain() + File.separator + nameCamelCase + ".java"), nameCamelCase));
			
			String templateEditFile = "mb" + File.separator + "pojoEditMB.vm";
			String templateListFile = "mb" + File.separator + "pojoListMB.vm";
			
			File viewEditFile = new VelocityTransform(project).transform(context, templateEditFile, Config.getInstance(project).getPathView(),  nameCamelCase, "EditMB.java");
			File viewListFile = new VelocityTransform(project).transform(context, templateListFile, Config.getInstance(project).getPathView(),  nameCamelCase, "ListMB.java");
            
            
            App.out.println("View " + viewEditFile + " criado com sucesso !");
            App.out.println("View " + viewListFile + " criado com sucesso !");
            
            
        } 
		catch (Exception ex) {
            Logger.getLogger(CreateViewCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	private String generateBodyMB(File domain, String nameCamelCase) throws Exception {
		
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
