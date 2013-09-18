package exp.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import exp.config.FavoritoConfig;
import exp.entity.Favorito;
import exp.persistence.FavoritoDAO;

@BusinessController
public class FavoritoBC extends DelegateCrud<Favorito, Long, FavoritoDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private FavoritoConfig config;

	@Startup
	@Transactional
	public void load() {
		// Para ativar essa configuração modifique o valor em favoritos.config -> general.loadInitialData =
		// true
		if (config.isLoadInitialData()) {
			if (findAll().isEmpty()) {
				insert(new Favorito("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br"));
				insert(new Favorito("Demoiselle SourceForge", "http://sf.net/projects/demoiselle"));
				insert(new Favorito("Twitter", "http://twitter.frameworkdemoiselle.gov.br"));
				insert(new Favorito("Blog", "http://blog.frameworkdemoiselle.gov.br"));
				insert(new Favorito("Wiki", "http://wiki.frameworkdemoiselle.gov.br"));
				insert(new Favorito("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br"));
				insert(new Favorito("Forum", "http://forum.frameworkdemoiselle.gov.br"));
				insert(new Favorito("SVN", "http://svn.frameworkdemoiselle.gov.br"));
				insert(new Favorito("Maven", "http://repository.frameworkdemoiselle.gov.br"));
				insert(new Favorito("Downloads", "http://download.frameworkdemoiselle.gov.br"));
				
				insert(new Favorito("Google", "http://google.com", "111.111.111-11"));
			}
		}
	}
}
