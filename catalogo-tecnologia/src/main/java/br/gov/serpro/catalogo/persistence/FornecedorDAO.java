package br.gov.serpro.catalogo.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Fornecedor;

@PersistenceController
public class FornecedorDAO extends JPACrud<Fornecedor, Long> {

	private static final long serialVersionUID = 1L;
	
}
