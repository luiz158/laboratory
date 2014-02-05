package br.gov.frameworkdemoiselle.persistence;

import br.gov.frameworkdemoiselle.domain.Usuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.util.CriptografiaUtil;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author escritorio
 */
@PersistenceController
public class UsuarioDAO extends JPACrud<Usuario, Long> {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private ResourceBundle rb;

    /**
     *
     * @param usuario
     * @return
     * @throws java.lang.Exception
     */
    public Usuario findByUserName(String usuario) throws Exception{
            return (Usuario) getEntityManager().createNamedQuery("Usuario.findByUsuario").setParameter("usuario", usuario).getSingleResult();
    }
    
    /**
     *
     * @param aminesia
     * @return
     * @throws Exception
     */
    public Usuario findByAminesia(String aminesia) throws Exception{
            return (Usuario) getEntityManager().createNamedQuery("Usuario.findByAminesia").setParameter("aminesia", aminesia).getSingleResult();
    }
    
    /**
     *
     * @param email
     * @return
     * @throws Exception
     */
    public Usuario findByEmail(String email) throws Exception{
            return (Usuario) getEntityManager().createNamedQuery("Usuario.findByEmail").setParameter("email", email).getSingleResult();
    }

    /**
     *
     * @param idUsuario
     * @param idRole
     * @return
     * @throws java.lang.Exception
     */
    public Boolean hasRole(Long idUsuario, Short idRole) throws Exception{
        return (Boolean) getEntityManager().createNativeQuery("SELECT COUNT(1) > 0 FROM guddi.usuario WHERE id = :idUser AND papel = :papel")
                .setParameter("idUser", idUsuario)
                .setParameter("papel", idRole)
                .getSingleResult();
    }

    /**
     *
     * @param idUsuario
     * @param idResource
     * @return
     * @throws java.lang.Exception
     */
    public Integer hasPermission(Long idUsuario, Long idResource) throws Exception{
        return (Integer) getEntityManager().createNativeQuery("SELECT operacao FROM usuario_recursos ur WHERE id_usuario = :idUser AND id_recursos = :resource")
                .setParameter("idUser", idUsuario)
                .setParameter("resource", idResource)
                .getSingleResult();

    }

    /**
     *
     * @param usuario
     * @param senhaNova
     * @return
     * @throws java.lang.Exception
     */
    public void updatePassWithAminesia(String aminesia, String senhaNova) throws Exception {
        try {
            Usuario usu = findByAminesia(aminesia);
            if (usu != null) {
                usu.setSenha(CriptografiaUtil.getCodigoMd5(senhaNova));
                update(usu); 
            }
            else{
                 throw new Exception(rb.getString("aminesia.senha.atual.invalida"));
            }
        } catch (NoResultException e) {
           throw e;
        }
    }
    
   
}
