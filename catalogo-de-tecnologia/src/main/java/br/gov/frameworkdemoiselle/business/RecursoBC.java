package br.gov.frameworkdemoiselle.business;

import br.gov.frameworkdemoiselle.domain.Recurso;
import br.gov.frameworkdemoiselle.persistence.RecursoDAO;
import br.gov.frameworkdemoiselle.security.IRoles;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

/**
 *
 * @author escritorio
 */
@BusinessController
@RequiredRole(value = IRoles.ADMINISTRATOR)
public class RecursoBC extends DelegateCrud<Recurso, Long, RecursoDAO> {

	private static final long serialVersionUID = 1L;
}
