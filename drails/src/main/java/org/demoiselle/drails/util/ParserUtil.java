package org.demoiselle.drails.util;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserUtil {
	
	private static Map<String, String> fieldsList = new HashMap<>();

	public static void clearFieldsList(){
		if (fieldsList != null){
			if (!fieldsList.isEmpty()){
				fieldsList.clear();
			}			
		}	
	}
	
	
	public static Map<String, String> getAttributesFromClassFile(File domain){
		
		clearFieldsList();

		getAttributesFromClass(domain);

		String varPath = domain.getParent()+"/";

		List<String> extendedClasses = getExtendedClassesFiles(domain);
		
		for (String cls : extendedClasses){
			File varFile = new File (varPath+cls+".java");
			getAttributesFromClass(varFile);
		}
		return fieldsList;
	}
	
	/**
	 * Inspects a java source file to find declared attributes 
	 *
	 * @param file
	 * @return List of Attributes from class that was extended by gived file.
	 */
	static Map<?, ?> getAttributesFromClass(File domain){

		new FieldDeclarationVisitor().visit(getCompilationUnit(domain), null);

		fieldsList = new FieldDeclarationVisitor().getFields();

		return fieldsList;
	}
	
	/**
	 *  to Get a japa.parser.ast.CompilationUnit from a File Java Source 
	 * 
	 * @param fileI
	 * @return
	 */
	public static CompilationUnit getCompilationUnit(File domain){

		CompilationUnit compilationUnit;
		
		try {
			// parse the file
			compilationUnit = japa.parser.JavaParser.parse(domain);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

		return compilationUnit;
	}
	
	/**
	 * Inspects a java source file to find classes that are extended.
	 *   
	 * @param file
	 * @return List of classes that was extended by given file.
	 */
	static List<String> getExtendedClassesFiles(File domain){

		List<String> extendedClass = new ArrayList<>();

		new ClassOrInterfaceDeclarationVisitor().visit(getCompilationUnit(domain), null);

		List<ClassOrInterfaceType> extendsClasses = new ClassOrInterfaceDeclarationVisitor().getExtendClasses();  
		
		if(extendsClasses != null){
			for(ClassOrInterfaceType extend : extendsClasses){
				extendedClass.add(extend.toString());
			}
		}
		
		return extendedClass;
	}
	
	/**
	 * 
	 * Class to visit source and provide a method to get Field Declaration Attributes (name and type)
	 *
	 */
	private static class FieldDeclarationVisitor extends VoidVisitorAdapter<Object> {

		private static Map<String, String> fields = new HashMap<>();


		@Override
		public void visit(FieldDeclaration n, Object arg) {

			for (VariableDeclarator declarator : n.getVariables()) {

				if (declarator.getId().getName() != "serialVersionUID") {

					fields.put(declarator.getId().getName(), n.getType().toString());
				}
			}
		}

		/**
		 * 
		 * @return Map of Attibutes Names and Types founded on source file
		 */
		public Map<String, String> getFields(){
			return fields;
		}
	}
	
	/**
	 * 
	 * Class to visit source and provide a method to get Extended Class Name
	 *
	 */
	private static class ClassOrInterfaceDeclarationVisitor extends VoidVisitorAdapter<Object> {

		private static List<ClassOrInterfaceType> extendClasses = new ArrayList<>();

		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg) {
			extendClasses = n.getExtends();
		}

		/**
		 *  
		 * @return List of Extended Classes Names
		 */
		public List<ClassOrInterfaceType> getExtendClasses(){
			return extendClasses;
		}
	}
	
	
	/**
	 *  Inspects a java source file to find Annotations for an informed field
	 *   
	 * @param paramFile path of java source file
	 * @param paramField name of field 
	 * @return List of Annotations for field
	 */
	public static List<String> getAnnotationsForField(File paramFile, String paramField){
		
		new AnnotationDeclarationVisitor().clearFieldAndAnnotationsUtilList();
		new AnnotationDeclarationVisitor().visit(ParserUtil.getCompilationUnit(paramFile), null);
		
		String varPath = paramFile.getParent()+"/";
		List<String> extendedClasses = getExtendedClassesFiles(paramFile);
		for (String cls : extendedClasses){
			File varFile = new File (varPath+cls+".java");
			new AnnotationDeclarationVisitor().visit(ParserUtil.getCompilationUnit(varFile), null);
		}
		
		return new AnnotationDeclarationVisitor().getAnnotationsForField(paramField);
	}
	
	/**
	 *
	 * Class to visit source and get Name and Annotations for Fields  
	 *
	 */
	private static class AnnotationDeclarationVisitor extends VoidVisitorAdapter {

		private static List<FieldAndAnnotationsUtil> fieldAndAnnotationsUtilList = new ArrayList<FieldAndAnnotationsUtil>();
		
		@Override
		public void visit(FieldDeclaration n, Object arg) {
					
			List<String> fieldAnnotations = new ArrayList<>();
			String fieldName = "";
						
			int i = 0;
			fieldName = n.getVariables().get(0).toString();

			// check if is a complex or initialized field
			i = fieldName.indexOf(' ');			
			if (i > 0){
				fieldName = fieldName.substring(0,i);
			}
			
			if(n.getAnnotations() != null){
			
				for (AnnotationExpr annotation : n.getAnnotations()) {				
					fieldAnnotations.add(annotation.getName().getName());
				}
			}
			
			if (fieldAnnotations.size() > 0){				
				fieldAndAnnotationsUtilList.add(new FieldAndAnnotationsUtil(fieldName, fieldAnnotations));
			}
			
		}

		/**
		 * 
		 * @return List of FieldAndAnnotationsUtil for a source
		 * @see FieldAndAnnotationsUtil
		 */
		public static List<FieldAndAnnotationsUtil> getFieldAndAnnotationsUtilList(){
			return fieldAndAnnotationsUtilList;
		}
		
		/**
		 * to Clear the FieldAndAnnotationsUtilList
		 * Must to be executed after each file parser
		 */
		public void clearFieldAndAnnotationsUtilList(){
			if (fieldAndAnnotationsUtilList != null){
				this.fieldAndAnnotationsUtilList.clear();
			}
		}

		/**
		 * 
		 * @param varField Name of a field
		 * @param varAnnotation Annotation Name
		 * @return true if informed varField is annotated with varAnnotation 
		 */
		public boolean  hasAnnotationForField(String varField, String varAnnotation){
			for ( FieldAndAnnotationsUtil fieldAndAnnotationsUtil : fieldAndAnnotationsUtilList) {
				if (fieldAndAnnotationsUtil.getFieldName().equalsIgnoreCase(varField)){
					for (String annotated : fieldAndAnnotationsUtil.getFieldAnnotations()){
						if (annotated.equalsIgnoreCase(varAnnotation)){
							return true;
						}
					}
				}
			}
			return false;
		}
		
		/**
		 *
		 * @param varAnnotation Annotation Name
		 * @return the field name if it is annotated with varAnnotation
		 */
		public String  getFieldWithAnnotation(String varAnnotation){
			for ( FieldAndAnnotationsUtil fieldAndAnnotationsUtil : fieldAndAnnotationsUtilList) {
				for (String annotated : fieldAndAnnotationsUtil.getFieldAnnotations()){
						if (annotated.equalsIgnoreCase(varAnnotation)){
							return fieldAndAnnotationsUtil.getFieldName();
						}
					}				
			}
			return "";
		}
		
		/**
		 * 
		 * @param varField Name of a field
		 * @return List of Annotation for a informed varField
		 */
		public List<String> getAnnotationsForField(String varField){
			
			List<String> annotationsForField = new ArrayList<String>();
			
			for ( FieldAndAnnotationsUtil fieldAndAnnotationsUtil : fieldAndAnnotationsUtilList) {
				if (fieldAndAnnotationsUtil.getFieldName().equalsIgnoreCase(varField)){
					for (String annotated : fieldAndAnnotationsUtil.getFieldAnnotations()){
						annotationsForField.add(annotated);						
					}
				}
			}
			return annotationsForField;
		}
	}

}
