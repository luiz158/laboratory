package br.gov.serpro.catalogo.bussiness;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.mail.Mail;
import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.User;

@BusinessController
public class EmailBC {

	@Inject
	private Mail mail;

	public void enviarEmailUsuarioAdicionadoEquipe(User usuario, Fase fase) {
		if(usuario.getEmail()!=null && !usuario.getEmail().isEmpty()){
						
			mail
			.to(usuario.getEmail())
			.from("robson.ximenes@serpro.gov.br")
			.body().html("<strong>"+usuario.getName()+"</strong>, você foi adicionado a equipe de <a href='http://localhost:8080/catalogo-tecnologia/#"+fase.getFase().name().toLowerCase()+"/edit/"+fase.getId()+"'>"+fase.getFase().name()+" </a>" )
			.subject("Assunto")
			.importance().high()
			.send();
		}else{
			throw new ValidationException().addViolation(null, "Usuário com e-mail inválido");
		}
	}

}
