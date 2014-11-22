package br.gov.frameworkdemoiselle.view;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.business.SecurityBC;
import br.gov.frameworkdemoiselle.business.UsuarioBC;
import br.gov.frameworkdemoiselle.domain.Usuario;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.CatalogoAuthenticator;
import br.gov.frameworkdemoiselle.security.Roles;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import com.sun.corba.se.impl.util.RepositoryId;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author escritorio
 */
@ViewController
@PreviousView("./usuario_list.jsf")
public class UsuarioEditMB extends AbstractEditPageBean<Usuario, Long> {

    private static final long serialVersionUID = 1L;

    @Inject
    private MessageContext messageContext;

    @Inject
    private UsuarioBC usuarioBC;

    @Inject
    private SecurityBC securityBC;

    @Inject
    private CatalogoAuthenticator catalogoAuthenticator;

    /**
     *
     * @return
     */
    @Override
    @Transactional
    public String delete() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    @Transactional
    public String ativar() {
        getBean().setIsAtivo(Boolean.TRUE);
        this.usuarioBC.update(getBean());

        messageContext.add("{usuario-ativo-ok}");

        return getPreviousView();
    }

    /**
     *
     * @return
     */
    @Transactional
    public String inativar() {
        getBean().setIsAtivo(Boolean.FALSE);
        this.usuarioBC.update(getBean());

        messageContext.add("{usuario-inativo-ok}");

        return getPreviousView();
    }

    /**
     *
     * @return
     */
    @Override
    @Transactional
    public String insert() {
        getBean().setSenha("123");
        getBean().setAminesia("123");
        getBean().setIsAtivo(Boolean.FALSE);

        this.usuarioBC.insert(getBean());

        messageContext.add("{usuario-insert-ok}", getBean().getUsuario());

        try {
            securityBC.enviarMensagemLembrandoSenha(getBean());
        } catch (Exception e) {
            messageContext.add("{email.exception.generico}", e.getMessage());
        }

        return getPreviousView();
    }

    /**
     *
     * @return
     */
    @Override
    @Transactional
    public String update() {
        getBean().setSenha("123");
        getBean().setAminesia("123");
        getBean().setIsAtivo(Boolean.FALSE);

        this.usuarioBC.update(getBean());

        messageContext.add("{usuario-update-ok}", getBean().getUsuario());

        try {
            securityBC.enviarMensagemLembrandoSenha(getBean());
        } catch (Exception e) {
            messageContext.add("{email.exception.generico}", e.getMessage());
        }

        return getPreviousView();
    }

    /**
     *
     * @return
     */
    public HashMap<Short, String> getPapeis() {
        return Roles.getRolesListAsMap();
    }

    @ExceptionHandler
    private void tratarExcecao(Exception e) {
        messageContext.add("{guddi.erro.generico}", SeverityType.ERROR);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    protected Usuario handleLoad(Long id) {
        return this.usuarioBC.load(getId());
    }

    public void login() {
        try {
            catalogoAuthenticator.login();
        } catch (Exception e) {
            messageContext.add("Login: ", e.getMessage());
        }

    }

    public void logout() {
        try {
             catalogoAuthenticator.logout();
        } catch (Exception e) {
            messageContext.add("Login: ", e.getMessage());
        }

    }

}
