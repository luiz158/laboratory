package br.gov.serpro.jpa_jdbc_sample.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.jpa_jdbc_sample.domain.Bookmark;
import br.gov.serpro.jpa_jdbc_sample.domain.Log;
import br.gov.serpro.jpa_jdbc_sample.persistence.LogDAO;

@BusinessController
public class LogBC implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LogDAO logDAO;

	public List<Log> findAll() throws Exception {
		return logDAO.findAll();
	}
	
	public void insert(Bookmark bookmark, String operacao){
		logDAO.insert(bookmark, operacao);
	}

}
