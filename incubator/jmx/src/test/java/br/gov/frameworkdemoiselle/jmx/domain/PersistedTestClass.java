package br.gov.frameworkdemoiselle.jmx.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
