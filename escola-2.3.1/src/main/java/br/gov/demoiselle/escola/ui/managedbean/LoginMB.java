package br.gov.demoiselle.escola.ui.managedbean;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.exception.EscolaException;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;

@ViewController
@NextView("/private/pages/index.htxml")
public class LoginMB extends AbstractPageBean{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;
	
	@Inject
	private SecurityContext securityCtx;
	
	@Inject
    private MessageContext messageContext;
	
	public String login(){
		
		try{
			securityCtx.login();
			if(!securityCtx.isLoggedIn()){
				throw new EscolaException(ErrorMessage.USUARIO_002);
			}
			return getNextView();
		}
		catch(Exception e){
			 messageContext.add(e.getMessage(), SeverityType.ERROR);
			 return "";
		}
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
