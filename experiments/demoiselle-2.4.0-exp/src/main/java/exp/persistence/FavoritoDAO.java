package exp.persistence;

import javax.inject.Inject;

import org.slf4j.Logger;

import exp.entity.Favorito;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class FavoritoDAO extends JPACrud<Favorito, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	@SuppressWarnings("unused")
	private Logger logger;
	
}
