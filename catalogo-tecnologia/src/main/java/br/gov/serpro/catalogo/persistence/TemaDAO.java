package br.gov.serpro.catalogo.persistence;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Tema;

@PersistenceController
public class TemaDAO extends JPACrud<Tema, Long> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public List<Tema> findByJPQL(String jpql) {
		return super.findByJPQL(jpql);
	}
}
