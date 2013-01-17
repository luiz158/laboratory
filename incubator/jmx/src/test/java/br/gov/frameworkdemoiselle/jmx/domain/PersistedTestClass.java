package br.gov.frameworkdemoiselle.jmx.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Classe de teste para testar persistencia sendo invocada
 * por classes gerenciadas anotadas com Managed
 * 
 * @author SERPRO
 *
 */
@Entity
public class PersistedTestClass {
	
	private Long id;

	@Id
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
