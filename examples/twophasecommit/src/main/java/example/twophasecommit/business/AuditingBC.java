package example.twophasecommit.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import example.twophasecommit.domain.Auditing;
import example.twophasecommit.persistence.AuditingDAO;

@BusinessController
public class AuditingBC extends DelegateCrud<Auditing, Long, AuditingDAO> {

	private static final long serialVersionUID = 1L;
	
}
