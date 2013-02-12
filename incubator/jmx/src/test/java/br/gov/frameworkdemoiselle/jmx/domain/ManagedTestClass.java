package br.gov.frameworkdemoiselle.jmx.domain;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.jmx.dao.PersistedTestClassDAO;
import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.management.annotation.Operation;
import br.gov.frameworkdemoiselle.management.annotation.OperationParameter;
import br.gov.frameworkdemoiselle.management.annotation.OperationType;
import br.gov.frameworkdemoiselle.management.annotation.Property;
import br.gov.frameworkdemoiselle.transaction.Transactional;

/**
 * Classe usada para testar se o registro de classes Managed
 * como MBeans está funcionando.
 * 
 * @author SERPRO
 *
 */
@Managed
public class ManagedTestClass {
	
	@Inject
	private PersistedTestClassDAO dao;

	@Property(description="Atributo de teste para testar registro de MBean")
	private String atributo;

	
	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	
	@Operation(type=OperationType.ACTION,description="Operação de teste")
	@Transactional
	public void operacao(@OperationParameter(name="parametro") String parametro){
		System.out.println("Operação foi chamada");
		
		PersistedTestClass test = new PersistedTestClass();
		//test.setId((long)Math.random());
		dao.insert(test);
		
	}
	
	@Operation(type=OperationType.ACTION_INFO,description="Lê uma classe")
	@Transactional
	public String operacao2( @OperationParameter(name="idTest",description="ID") Long idTest ){
		try{
			PersistedTestClass obj = dao.load(idTest);
			return obj.toString();
		}
		catch(Exception e){
			return "Deu erro!";
		}
	}
	

}
