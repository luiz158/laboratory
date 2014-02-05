package br.gov.frameworkdemoiselle.business;

import br.gov.frameworkdemoiselle.domain.Usuario;
import br.gov.frameworkdemoiselle.persistence.UsuarioDAO;
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
public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO> {

	private static final long serialVersionUID = 1L;

}
