package example.twophasecommit.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import example.twophasecommit.domain.Auditing;

@PersistenceController
public class AuditingDAO extends JPACrud<Auditing, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject @Name("database2-ds")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
