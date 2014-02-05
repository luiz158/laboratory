/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.business;

import br.gov.frameworkdemoiselle.domain.Recurso;
import br.gov.frameworkdemoiselle.domain.Usuario;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.mail.Mail;
import br.gov.frameworkdemoiselle.persistence.RecursoDAO;
import br.gov.frameworkdemoiselle.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.security.Resources;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.util.CriptografiaUtil;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class SecurityBC implements Serializable{

    @Inject
    private Mail mailer;

    @Inject
    private RecursoDAO recursoDAO;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private ResourceBundle rb;

    /**
     *
     */
    @Startup
    public void bootstrap() {
        try {

            recursoDAO.clear();
            List<String> listarec = Resources.getResourcesList();
            for (String recursos : listarec) {
                Recurso rec = recursoDAO.load(recursos);
                if (rec == null) {
                    rec = new Recurso();
                    rec.setId(Long.valueOf(Resources.getResource(recursos)));
                    rec.setNome(recursos);
                    recursoDAO.insert(rec);
                }

            }

        } catch (Exception e) {
            Logger.getLogger(SecurityBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *
     * @param aminesia
     * @param senhaatual
     * @param senhanova
     * @throws java.lang.Exception
     */
    public void alteraSenha(String aminesia, String senhanova) throws Exception {
        usuarioDAO.updatePassWithAminesia(aminesia, senhanova);
    }

    /**
     *
     * @param usuario
     * @throws Exception
     */
    public void enviarMensagemLembrandoSenha(Usuario usuario) throws Exception {
        enviarMensagemLembrandoSenha(usuario.getEmail());
    }

    /**
     *
     * @param destinatario
     * @throws Exception
     */
    public void enviarMensagemLembrandoSenha(String destinatario) throws Exception {
        Usuario usuario = usuarioDAO.findByEmail(destinatario);
        String senha = CriptografiaUtil.getCodigoMd5("" + System.currentTimeMillis());
        usuario.setAminesia(senha);
        usuario.setSenha(senha.substring(21, 27));
        usuarioDAO.update(usuario);

        mailer.to(destinatario)
                .from("Remetente")
                .body().text(rb.getString("email.aminesia.texto", usuario.getUsuario(), usuario.getSenha(), "url" + usuario.getAminesia()))
                .subject("Assunto")
                .send();

    }
}
