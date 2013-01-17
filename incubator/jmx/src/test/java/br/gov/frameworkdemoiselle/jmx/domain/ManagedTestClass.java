package br.gov.frameworkdemoiselle.jmx.domain;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.jmx.annotation.Managed;
import br.gov.frameworkdemoiselle.jmx.annotation.Operation;
import br.gov.frameworkdemoiselle.jmx.annotation.OperationParameter;
import br.gov.frameworkdemoiselle.jmx.annotation.OperationType;
import br.gov.frameworkdemoiselle.jmx.annotation.Property;
import br.gov.frameworkdemoiselle.jmx.dao.PersistedTestClassDAO;
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
	public void operacao(@OperationParameter(name="parametro") String parametro){
		System.out.println("Operação foi chamada");
		
		PersistedTestClass test = new PersistedTestClass();
		test.setId((long)Math.random());
		dao.insert(test);
		
	}
	
	@Transactional
	@Operation(type=OperationType.ACTION,description="Salva uma classe persistida")
	public void operacao2(@OperationParameter(name="persistedClassID",description="Classe persistida") Long idClassePersistida){
		PersistedTestClass test = new PersistedTestClass();
		test.setId(idClassePersistida);
		dao.insert(test);
	}
	
	
}
