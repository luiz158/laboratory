package br.gov.serpro.catalogo.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Subcategoria;

@PersistenceController
public class SubcategoriaDAO extends JPACrud<Subcategoria, Long> {

	private static final long serialVersionUID = 1L;
	
}
