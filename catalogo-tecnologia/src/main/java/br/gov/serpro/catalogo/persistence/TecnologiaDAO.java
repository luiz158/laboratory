package br.gov.serpro.catalogo.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Tecnologia;

@PersistenceController
public class TecnologiaDAO extends JPACrud<Tecnologia, Long> {

	private static final long serialVersionUID = 1L;
	
}
