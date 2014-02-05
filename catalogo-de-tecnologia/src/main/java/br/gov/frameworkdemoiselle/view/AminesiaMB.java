package br.gov.frameworkdemoiselle.view;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.business.SecurityBC;
import br.gov.frameworkdemoiselle.domain.Usuario;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author escritorio
 */
@ViewController
@NextView("./index.jsf")
@PreviousView("./login.jsf")
public class AminesiaMB extends AbstractEditPageBean<Usuario, Long> {

    private static final long serialVersionUID = 1L;
    @Inject
    private SecurityBC securityBC;
    @Inject
    private MessageContext messageContext;
    private String email;
    private String aminesia;
    private String senhaatual;
    private String senhanova;
    private String senharepetida;

    /**
     *
     */
    public AminesiaMB() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        aminesia = req.getParameter("quem");
    }

    /**
     *
     * @return
     */
    @Override
    @Transactional
    public String insert() {
        return null;
    }

    /**
     *
     * @return
     */
    public String lembrar() {
        try {
            securityBC.enviarMensagemLembrandoSenha(email);
            messageContext.add("{aminesia.mensagem.lembrete.enviado}", SeverityType.INFO);
        } catch (Exception ex) {
            messageContext.add("{aminesia.lembrete.email.nao.cadastrado}", SeverityType.ERROR);
            return null;
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
        try {
            if (aminesia == null) {
                return getPreviousView();
            }
            if (senhanova.equals(senharepetida)) {
                this.securityBC.alteraSenha(aminesia, senhanova);
            } else {
                messageContext.add("{aminesia.nova.senha.diferente}", SeverityType.ERROR);
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(AminesiaMB.class.getName()).log(Level.SEVERE, null, ex);
            messageContext.add(ex.getMessage(), SeverityType.ERROR);
            return null;
        }
        return getNextView();
    }

    /**
     *
     * @return
     */
    @Override
    public String delete() {
        return null;
    }

    /**
     *
     * @return
     */
    public String getAminesia() {
        return aminesia;
    }

    /**
     *
     * @param aminesia
     */
    public void setAminesia(String aminesia) {
        this.aminesia = aminesia;
    }

    /**
     *
     * @return
     */
    public String getSenhaatual() {
        return senhaatual;
    }

    /**
     *
     * @param senhaatual
     */
    public void setSenhaatual(String senhaatual) {
        this.senhaatual = senhaatual;
    }

    /**
     *
     * @return
     */
    public String getSenhanova() {
        return senhanova;
    }

    /**
     *
     * @param senhanova
     */
    public void setSenhanova(String senhanova) {
        this.senhanova = senhanova;
    }

    /**
     *
     * @return
     */
    public String getSenharepetida() {
        return senharepetida;
    }

    /**
     *
     * @param senharepetida
     */
    public void setSenharepetida(String senharepetida) {
        this.senharepetida = senharepetida;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    protected Usuario handleLoad(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}