package br.gov.frameworkdemoiselle.persistence;

import br.gov.frameworkdemoiselle.domain.UsuarioRecurso;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import java.util.List;

/**
 *
 * @author Clovis Lemes Ferreira Junior
 */
@PersistenceController
public class UsuarioRecursoDAO extends JPACrud<UsuarioRecurso, Long> {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param idUsuario
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<UsuarioRecurso> findByUsuario(Long idUsuario){
    	return getEntityManager().createNamedQuery("UsuarioRecurso.findByIdUsuario").setParameter("idUsuario", idUsuario).getResultList();
    }
    
   
}
